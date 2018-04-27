package tokenization;

import com.google.gson.Gson;
import org.antlr.v4.runtime.Token;
import server.BaseController;
import zemberek.tokenization.TurkishTokenizer;
import zemberek.tokenization.antlr.TurkishLexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.post;

public class TurkishTokenizationController extends BaseController {

    public TurkishTokenizationController(Gson jsonConverter) throws IOException {
        super(jsonConverter);
        initializeController(jsonConverter);
    }

    public void initializeController(Gson jsonConverter) throws IOException {
        post("/simple_tokenization", (req, res) -> {
            String sentence = (req.queryParams("sentence") != null) ? req.queryParams("sentence") : "";
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            // token_mode: default : all
            String token_mode = (req.queryParams("token_mode") != null) ? req.queryParams("token_mode") : "default";
            TurkishTokenizer tokenizer;
            if (token_mode.equals("all")) {
                tokenizer = TurkishTokenizer.ALL;
            } else {
                tokenizer = TurkishTokenizer.DEFAULT;
            }
            SimpleTokenizationResult result = new SimpleTokenizationResult();
            if (show_input.equals("1")) {
                result.input = sentence;
            }
            result.tokenizations = tokenizer.tokenizeToStrings(sentence);

            return jsonConverter.toJson(result);
        });

        post("/token_iterator", (req, res) -> {
            String sentence = (req.queryParams("sentence") != null) ? req.queryParams("sentence") : "";
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            // token_mode: default : all
            String token_mode = (req.queryParams("token_mode") != null) ? req.queryParams("token_mode") : "default";
            TurkishTokenizer tokenizer;
            if (token_mode.equals("all")) {
                tokenizer = TurkishTokenizer.ALL;
            } else {
                tokenizer = TurkishTokenizer.DEFAULT;
            }
//            TurkishTokenizer tokenizer = TurkishTokenizer.builder()
//                    .ignoreTypes(TurkishLexer.Punctuation, TurkishLexer.NewLine, TurkishLexer.SpaceTab)
//                    .build();
            List<Token> tokens = tokenizer.tokenize(sentence);
            TokenIteratorResult result_iterator = new TokenIteratorResult();
            List<TokenResult> token_results = new ArrayList<TokenResult>();

            if (show_input.equals("1")) {
                result_iterator.input = sentence;
            }

            for (Token token : tokens) {
                TokenResult token_result = new TokenResult();
                token_result.content = token.getText();
                token_result.type_name = TurkishLexer.VOCABULARY.getDisplayName(token.getType());
                token_result.type_id = token.getType();
                token_result.start = token.getStartIndex();
                token_result.stop = token.getStopIndex();
                token_results.add(token_result);
            }
            result_iterator.token_results = token_results;

            return jsonConverter.toJson(result_iterator);
        });
    }
}

class SimpleTokenizationResult {
    public String input;
    public List<String> tokenizations;
}

class TokenIteratorResult {
    public String input;
    public List<TokenResult> token_results;
}
class TokenResult {
    public String content;
    public String type_name;
    public Integer type_id;
    public Integer start;
    public Integer stop;
}