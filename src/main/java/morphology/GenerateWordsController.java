package morphology;

import com.google.gson.Gson;
import server.BaseController;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.generator.WordGenerator.Result;
import zemberek.morphology.lexicon.DictionaryItem;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.post;

public class GenerateWordsController extends BaseController {

    private TurkishMorphology morphology;

    public GenerateWordsController(Gson jsonConverter) {
        super(jsonConverter);
        initializeController();
    }

    public void initializeController() {
        post("/generate_words", (req, res) -> {
            String word = req.queryParams("word");
            String morphemes = req.queryParams("morphemes");
            TurkishMorphology morphology =
                    TurkishMorphology.builder().addDictionaryLines(word).disableCache().build();
            DictionaryItem item = morphology.getLexicon().getMatchingItems(word).get(0);
            List<GenerateWordResult> wordResults = new ArrayList<>();
            List<Result> results = morphology.getWordGenerator().generate(item, morphemes.split(","));
            for (Result generateResult : results) {
                GenerateWordResult wordResult = new GenerateWordResult();
                wordResult.word = word;
                wordResult.surface = generateResult.surface;
                wordResult.analysis = generateResult.analysis.formatLong();
                wordResults.add(wordResult);
            }
            return jsonConverter.toJson(wordResults);
        });
    }
}

class GenerateWordResult {
    public String word;
    public String surface;
    public String analysis;
}