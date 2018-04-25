package tokenization;

import server.BaseController;
import zemberek.tokenization.TurkishSentenceExtractor;

import java.util.List;

import java.io.IOException;
import static spark.Spark.*;

import com.google.gson.Gson;

public class SentenceBoundaryDetectionController extends BaseController {

    public SentenceBoundaryDetectionController(Gson jsonConverter) throws IOException {
        super(jsonConverter);
        initializeController(jsonConverter);
    }

    public void initializeController(Gson jsonConverter) throws IOException {
        post("/sentence_boundary_detection", (req, res) -> {
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            String sentence = req.queryParams("sentence");
            TurkishSentenceExtractor extractor = TurkishSentenceExtractor.DEFAULT;
            SentenceBoundaryDetectionResult result = new SentenceBoundaryDetectionResult();
            if (show_input.equals("1")) {
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
