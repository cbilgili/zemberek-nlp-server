package server;

import static spark.Spark.after;
//import static spark.Spark.before;
import static spark.Spark.exception;
//import static spark.Spark.halt;
import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;
//import spark.Request;

public class BaseController {
    protected final Gson jsonConverter;

    public BaseController(final Gson jsonConverter) {
        this.jsonConverter = jsonConverter;

        // -- Check the authentication
//        before((req, res) -> {
//            validateLogin(jsonConverter, req);
//        });

        // -- Set proper content-type to all responses
        after((req, res) -> {
            res.type("application/json;charset=UTF-8");
        });

        // -- Handle the exceptions
        handleExceptions(jsonConverter);
    }

    protected void handleExceptions(final Gson jsonConverter) {
        exception(Exception.class, (ex, req, res) -> {
            res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            res.body(jsonConverter.toJson(ex.getMessage()));
        });
    }

}
