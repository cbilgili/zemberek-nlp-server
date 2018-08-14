package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import morphology.AnalyzeWordController;
import morphology.AnalyzeSentenceController;
import morphology.FindPOSController;
import morphology.StemmingAndLemmatizationController;
import normalization.SpellingController;
import tokenization.SentenceBoundaryDetectionController;
import tokenization.TurkishTokenizationController;
import utils.ZemberekExclusionStrategy;
import zemberek.morphology.TurkishMorphology;

import java.io.IOException;

public class ServiceMain {
    public static void main(String[] args) throws IOException {
        // JSON converter
        Gson jsonConverter = new GsonBuilder()
                .addSerializationExclusionStrategy(new ZemberekExclusionStrategy())
                .disableInnerClassSerialization()
                .create();
        // Turkish defauly morphology
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();

        new FindPOSController(jsonConverter, morphology);
        new SentenceBoundaryDetectionController(jsonConverter);
        new TurkishTokenizationController(jsonConverter);
        new SpellingController(jsonConverter, morphology);
        new StemmingAndLemmatizationController(jsonConverter, morphology);
        new AnalyzeWordController(jsonConverter, morphology);
        new AnalyzeSentenceController(jsonConverter, morphology);
    }
}
