package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import morphology.FindPOSController;
import tokenization.SentenceBoundaryDetectionController;
import utils.ZemberekExclusionStrategy;

import java.io.IOException;

public class ServiceMain {
    public static void main(String[] args) throws IOException {
        Gson jsonConverter = new GsonBuilder()
                .addSerializationExclusionStrategy(new ZemberekExclusionStrategy())
                .disableInnerClassSerialization()
                .create();
        new FindPOSController(jsonConverter);
        new SentenceBoundaryDetectionController(jsonConverter);
    }
}
