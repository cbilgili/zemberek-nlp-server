package morphology;

import server.BaseController;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.morphology.analysis.tr.TurkishSentenceAnalyzer;
//
import java.io.IOException;
import static spark.Spark.*;

import java.util.*;
import com.google.gson.Gson;

public class FindPOSController extends BaseController {

    TurkishSentenceAnalyzer analyzer;
    TurkishMorphology morphology;


    public FindPOSController(Gson jsonConverter, TurkishMorphology morphology) throws IOException {
        super(jsonConverter);
        initializeController(jsonConverter, morphology);
    }

    public void initializeController(Gson jsonConverter, TurkishMorphology morphology) throws IOException {
        Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
        TurkishSentenceAnalyzer sentenceAnalyzer = new TurkishSentenceAnalyzer(
                morphology,
                disambiguator
        );

        this.analyzer = sentenceAnalyzer;
        this.morphology = morphology;
        post("/find_pos", (req, res) -> {
            String sentence = req.queryParams("sentence");
            SentenceAnalysis analysis = this.analyzer.analyze(sentence);
            this.analyzer.disambiguate(analysis);
            List<POSResult> results = new ArrayList<POSResult>();



            for (SentenceAnalysis.Entry entry : analysis) {
                WordAnalysis wa = entry.parses.get(0);
                POSResult item = new POSResult();
                item.analysis = wa;
                item.input = entry.input;
                results.add(item);
            }

            return jsonConverter.toJson(results);
        });
    }
}

class POSResult {
    public String input;
    public WordAnalysis analysis;
}