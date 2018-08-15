package normalization;

import com.google.gson.Gson;
import server.BaseController;
import spark.Request;
import zemberek.morphology.TurkishMorphology;
import zemberek.normalization.TurkishSpellChecker;

import java.io.IOException;
import java.util.List;

import static spark.Spark.post;
import static utils.ParameterHelper.showInput;

public class SpellingController extends BaseController {

    public SpellingController(Gson jsonConverter, TurkishMorphology morphology) throws IOException {
        super(jsonConverter);
        initializeController(morphology);
    }

    public void initializeController(TurkishMorphology morphology) throws IOException {
        TurkishSpellChecker spellChecker = new TurkishSpellChecker(morphology);

        post("/spelling_check", (req, res) -> {
            String word = req.queryParams("word");
            SpellingCheckResult result = new SpellingCheckResult();
            if (showInput(req)) {
                result.input = word;
            }
            result.is_correct = spellChecker.check(word);
            return jsonConverter.toJson(result);
        });

        post("/spelling_suggestions", (req, res) -> {
            String word = req.queryParams("word");
            SpellingSuggestionResult result = new SpellingSuggestionResult();
            if (showInput(req)) {
                result.input = word;
            }
            result.suggestions = spellChecker.suggestForWord(word);
            return jsonConverter.toJson(result);
        });
    }


}

class SpellingCheckResult {
    public String input;
    public Boolean is_correct;
}

class SpellingSuggestionResult {
    public String input;
    public List<String> suggestions;
}