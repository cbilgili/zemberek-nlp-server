package morphology;

import com.google.gson.Gson;
import server.BaseController;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.post;

public class StemmingAndLemmatizationController extends BaseController {

    public StemmingAndLemmatizationController(Gson jsonConverter) throws IOException {
        super(jsonConverter);
        initializeController(jsonConverter);
    }

    public void initializeController(Gson jsonConverter) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();

        post("/stems", (req, res) -> {
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            String word = req.queryParams("word");
            StemmingAndLemmatizationResults stem_and_lemma_result = new StemmingAndLemmatizationResults();
            List<StemmingAndLemmatizationItem> stem_and_lemma_list = new ArrayList<StemmingAndLemmatizationItem>();
            if (show_input.equals("1")) {
                stem_and_lemma_result.input = word;
            }
            List<WordAnalysis> results = morphology.analyze(word);
            for (WordAnalysis result : results) {
                StemmingAndLemmatizationItem stem_and_lemma_item = new StemmingAndLemmatizationItem();
                stem_and_lemma_item.long_format = result.formatLong();
                stem_and_lemma_item.stems = result.getStems();
                stem_and_lemma_list.add(stem_and_lemma_item);
            }
            stem_and_lemma_result.results = stem_and_lemma_list;
            return jsonConverter.toJson(stem_and_lemma_result);
        });

        post("/lemmas", (req, res) -> {
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            String word = req.queryParams("word");
            StemmingAndLemmatizationResults stem_and_lemma_result = new StemmingAndLemmatizationResults();
            List<StemmingAndLemmatizationItem> stem_and_lemma_list = new ArrayList<StemmingAndLemmatizationItem>();
            if (show_input.equals("1")) {
                stem_and_lemma_result.input = word;
            }
            List<WordAnalysis> results = morphology.analyze(word);
            for (WordAnalysis result : results) {
                StemmingAndLemmatizationItem stem_and_lemma_item = new StemmingAndLemmatizationItem();
                stem_and_lemma_item.long_format = result.formatLong();
                stem_and_lemma_item.lemmas = result.getLemmas();
                stem_and_lemma_list.add(stem_and_lemma_item);
            }
            stem_and_lemma_result.results = stem_and_lemma_list;
            return jsonConverter.toJson(stem_and_lemma_result);
        });
    }
}

class StemmingAndLemmatizationResults {
    public String input;
    public List<StemmingAndLemmatizationItem> results;
}

class StemmingAndLemmatizationItem {
    public String long_format;
    public List<String> stems;
    public List<String> lemmas;
}