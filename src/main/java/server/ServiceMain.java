package server;

import com.google.gson.Gson;
import morphology.FindPOSController;

import java.io.IOException;

public class ServiceMain {
    public static void main(String[] args) throws IOException {
        Gson jsonConverter = new Gson();
        new FindPOSController(jsonConverter);
    }
}
