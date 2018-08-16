package utils;

import spark.Request;

// Helper class for request parameters.
public class ParameterHelper {

    public static boolean showInput(Request req) {
        return getBooleanParam(req, "show_input");
    }

    public static boolean getBooleanParam(Request req, String param) {
        if (param == null) {
            throw new NullPointerException("parameter is null");
        }
        String value = req.queryParams(param);
        return value != null && value.equals("1");
    }

}
