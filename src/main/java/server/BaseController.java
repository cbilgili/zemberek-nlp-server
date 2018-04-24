package server;

import static spark.Spark.after;
//import static spark.Spark.before;
//import static spark.Spark.exception;
//import static spark.Spark.halt;

//import spark.Request;

public class BaseController {
    public BaseController() {

        // -- Check the authentication
//        before((req, res) -> {
//            validateLogin(jsonConverter, req);
//        });

        // -- Set proper content-type to all responses
        after((req, res) -> {
            res.type("application/json;charset=UTF-8");
        });

        // -- Handle the exceptions
        // handleExceptions(jsonConverter);
    }

}
