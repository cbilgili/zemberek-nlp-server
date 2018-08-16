package morphology;

import com.google.gson.Gson;
import server.BaseController;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.AnalysisFormatters;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.post;
import static utils.ParameterHelper.showInput;

public class AnalyzeWordController extends BaseController {

    public AnalyzeWordController(Gson jsonConverter, TurkishMorphology morphology) {
        super(jsonConverter);
        initializeController(jsonConverter, morphology);
    }

    public void initializeController(Gson jsonConverter, TurkishMorphology morphology) {

        post("/analyze_word", (req, res) -> {
            String word = req.queryParams("word");
            AnalyzeWordResults analyze_result = new AnalyzeWordResults();
            if (showInput(req)) {
                analyze_result.input = word;
            }
            WordAnalysis results = morphology.analyze(word);
            List<AnalyzeWordItem> analyze_list = new ArrayList<>();
            for (SingleAnalysis result : results) {
                analyze_list.add(AnalyzeWordItem.fromSingleAnalysis(result));
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

    public static AnalyzeWordItem fromSingleAnalysis(SingleAnalysis singleAnalysis) {
        AnalyzeWordItem analze_item = new AnalyzeWordItem();
        analze_item.root = singleAnalysis.getDictionaryItem().root;
        analze_item.dictionary_item_name = singleAnalysis.getDictionaryItem().lemma;
        analze_item.no_surface = AnalysisFormatters.DEFAULT_LEXICAL_ONLY_MORPHEMES.format(singleAnalysis);
        analze_item.long_format = singleAnalysis.formatLong();
        analze_item.no_empty = "";
        analze_item.oflazer = AnalysisFormatters.OFLAZER_STYLE.format(singleAnalysis);
        analze_item.only_igs = "";
        return analze_item;
    }

    // TODO: Decide on which formats to expose.
    public String root;
    public String dictionary_item_name;
    public String long_format;
    public String no_surface;
    @Deprecated
    public String no_empty;
    public String oflazer;
    @Deprecated
    public String only_igs;

}