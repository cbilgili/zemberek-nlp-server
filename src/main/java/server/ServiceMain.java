package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import morphology.AnalyzeWordController;
import morphology.DisambiguateSentencesController;
import morphology.FindPOSController;
import morphology.StemmingAndLemmatizationController;
import normalization.SpellingController;
import tokenization.SentenceBoundaryDetectionController;
import tokenization.TurkishTokenizationController;
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
        new TurkishTokenizationController(jsonConverter);
        new SpellingController(jsonConverter);
        new StemmingAndLemmatizationController(jsonConverter);
        new AnalyzeWordController(jsonConverter);
        new DisambiguateSentencesController(jsonConverter);
    }
}
