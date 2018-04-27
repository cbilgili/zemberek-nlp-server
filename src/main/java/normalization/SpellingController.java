package normalization;

import com.google.gson.Gson;
import server.BaseController;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.normalization.TurkishSpellChecker;

import java.io.IOException;
import java.util.List;

import static spark.Spark.post;

public class SpellingController extends BaseController {

    public SpellingController(Gson jsonConverter) throws IOException {
        super(jsonConverter);
        initializeController(jsonConverter);
    }

    public void initializeController(Gson jsonConverter) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        TurkishSpellChecker spellChecker = new TurkishSpellChecker(morphology);

        post("/spelling_check", (req, res) -> {
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            String word = req.queryParams("word");
            SpellingCheckResult result = new SpellingCheckResult();
            if (show_input.equals("1")) {
                result.input = word;
            }
            result.is_correct = spellChecker.check(word);
            return jsonConverter.toJson(result);
        });

        post("/spelling_suggestions", (req, res) -> {
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            String word = req.queryParams("word");
            SpellingSuggestionResult result = new SpellingSuggestionResult();
            if (show_input.equals("1")) {
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