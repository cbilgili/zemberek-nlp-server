package morphology;

import com.google.gson.Gson;
import server.BaseController;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.post;
import static utils.ParameterHelper.showInput;

public class StemmingAndLemmatizationController extends BaseController {

    public StemmingAndLemmatizationController(Gson jsonConverter, TurkishMorphology morphology) {
        super(jsonConverter);
        initializeController(morphology);
    }

    public void initializeController(TurkishMorphology morphology) {

        post("/stems", (req, res) -> {
            String word = req.queryParams("word");
            StemmingAndLemmatizationResults stem_and_lemma_result = new StemmingAndLemmatizationResults();
            List<StemmingAndLemmatizationItem> stem_and_lemma_list = new ArrayList();
            if (showInput(req)) {
                stem_and_lemma_result.input = word;
            }
            WordAnalysis results = morphology.analyze(word);
            for (SingleAnalysis result : results) {
                StemmingAndLemmatizationItem stem_and_lemma_item = new StemmingAndLemmatizationItem();
                stem_and_lemma_item.long_format = result.formatLong();
                stem_and_lemma_item.stems = result.getStems();
                stem_and_lemma_list.add(stem_and_lemma_item);
            }
            stem_and_lemma_result.results = stem_and_lemma_list;
            return jsonConverter.toJson(stem_and_lemma_result);
        });

        post("/lemmas", (req, res) -> {
            String word = req.queryParams("word");
            StemmingAndLemmatizationResults stem_and_lemma_result = new StemmingAndLemmatizationResults();
            List<StemmingAndLemmatizationItem> stem_and_lemma_list = new ArrayList();
            if (showInput(req)) {
                stem_and_lemma_result.input = word;
            }
            WordAnalysis results = morphology.analyze(word);
            for (SingleAnalysis result : results) {
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