package morphology;

import com.google.gson.Gson;
import server.BaseController;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.post;

public class AnalyzeWordController extends BaseController {

    public AnalyzeWordController(Gson jsonConverter) throws IOException {
        super(jsonConverter);
        initializeController(jsonConverter);
    }

    public void initializeController(Gson jsonConverter) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();

        post("/analyze_word", (req, res) -> {
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            String word = req.queryParams("word");
            AnalyzeWordResults analyze_result = new AnalyzeWordResults();
            if (show_input.equals("1")) {
                analyze_result.input = word;
            }
            List<WordAnalysis> results = morphology.analyze(word);
            List<AnalyzeWordItem> analyze_list = new ArrayList<AnalyzeWordItem>();
            for (WordAnalysis result : results) {
                AnalyzeWordItem analze_item = new AnalyzeWordItem();
                analze_item.root = result.getRoot();
                analze_item.dictionary_item_name = result.getDictionaryItem().lemma;
                analze_item.no_surface = result.formatNoSurface();
                analze_item.long_format = result.formatLong();
                analze_item.no_empty = result.formatNoEmpty();
                analze_item.lazer = result.formatOflazer();
                analze_item.only_igs = result.formatOnlyIgs();
                analyze_list.add(analze_item);
            }
            analyze_result.results = analyze_list;
            return jsonConverter.toJson(analyze_result);
        });
    }
}


class AnalyzeWordResults {
    public String input;
    public List<AnalyzeWordItem> results;
}

class AnalyzeWordItem {
    public String root;
    public String dictionary_item_name;
    public String long_format;
    public String no_surface;
    public String no_empty;
    public String lazer;
    public String only_igs;
}