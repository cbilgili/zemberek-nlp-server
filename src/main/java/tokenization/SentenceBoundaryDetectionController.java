package tokenization;

import server.BaseController;
import zemberek.tokenization.TurkishSentenceExtractor;

import java.util.List;

import java.io.IOException;
import static spark.Spark.*;
import static utils.ParameterHelper.showInput;

import com.google.gson.Gson;

public class SentenceBoundaryDetectionController extends BaseController {

    public SentenceBoundaryDetectionController(Gson jsonConverter) {
        super(jsonConverter);
        initializeController();
    }

    public void initializeController() {
        post("/sentence_boundary_detection", (req, res) -> {
            String sentence = req.queryParams("sentence");
            TurkishSentenceExtractor extractor = TurkishSentenceExtractor.DEFAULT;
            SentenceBoundaryDetectionResult result = new SentenceBoundaryDetectionResult();
            if (showInput(req)) {
                result.input = sentence;
            }
            result.sentences = extractor.fromParagraph(sentence);
            return jsonConverter.toJson(result);
        });
    }
}

class SentenceBoundaryDetectionResult {
    public String input;
    public List<String> sentences;
}
