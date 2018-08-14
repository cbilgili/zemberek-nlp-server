package morphology;

import static spark.Spark.post;

import java.util.ArrayList;
import java.util.List;
import server.BaseController;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.SingleAnalysis;

import com.google.gson.Gson;

public class FindPOSController extends BaseController {

    private TurkishMorphology morphology;

    public FindPOSController(Gson jsonConverter, TurkishMorphology morphology) {
        super(jsonConverter);
        this.morphology = morphology;
        initializeController();
    }

    public void initializeController() {
        post("/find_pos", (req, res) -> {
            String sentence = req.queryParams("sentence");
            SentenceAnalysis analysis = morphology.analyzeAndDisambiguate(sentence);
            List<POSResult> results = new ArrayList<>();
            for (SingleAnalysis entry : analysis.bestAnalysis()) {
                POSResult item = new POSResult();
                item.analysis = entry.formatLexical();
                item.pos = entry.getPos().shortForm;
                results.add(item);
            }
            return jsonConverter.toJson(results);
        });
    }
}

class POSResult {
    public String pos;
    public String analysis;
}