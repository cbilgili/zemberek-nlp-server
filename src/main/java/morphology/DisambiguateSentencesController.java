package morphology;

import com.google.gson.Gson;
import server.BaseController;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.morphology.analysis.tr.TurkishSentenceAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.post;

public class DisambiguateSentencesController extends BaseController {


    public DisambiguateSentencesController(Gson jsonConverter) throws IOException {
        super(jsonConverter);
        initializeController(jsonConverter);
    }

    public void initializeController(Gson jsonConverter) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
        TurkishSentenceAnalyzer sentenceAnalyzer = new TurkishSentenceAnalyzer(
                morphology,
                disambiguator
        );

        post("/analyze_sentence", (req, res) -> {
            String show_input = (req.queryParams("show_input") != null) ? req.queryParams("show_input") : "0";
            String disambiguate = (req.queryParams("disambiguate") != null) ? req.queryParams("disambiguate") : "1";
            String sentence = req.queryParams("sentence");
            SentenceResults sentence_result = new SentenceResults();
            if (show_input.equals("1")) {
                sentence_result.input = sentence;
            }
            SentenceAnalysis sentenceAnalysis = sentenceAnalyzer.analyze(sentence);
            if (disambiguate.equals("1")) {
                sentenceAnalyzer.disambiguate(sentenceAnalysis);
            }
            List<SentenceItem> sentence_item_list = new ArrayList<SentenceItem>();
            for (SentenceAnalysis.Entry entry : sentenceAnalysis) {
                SentenceItem sentence_item = new SentenceItem();
                sentence_item.input = entry.input;
                List<AnalyzeWordItem> analyze_list = new ArrayList<AnalyzeWordItem>();
                for (WordAnalysis analysis : entry.parses) {
                    AnalyzeWordItem analze_item = new AnalyzeWordItem();
                    analze_item.root = analysis.getRoot();
                    analze_item.dictionary_item_name = analysis.getDictionaryItem().lemma;
                    analze_item.no_surface = analysis.formatNoSurface();
                    analze_item.long_format = analysis.formatLong();
                    analze_item.no_empty = analysis.formatNoEmpty();
                    analze_item.lazer = analysis.formatOflazer();
                    analze_item.only_igs = analysis.formatOnlyIgs();
                    analyze_list.add(analze_item);
                }
                sentence_item.results = analyze_list;
                sentence_item_list.add(sentence_item);
            }
            sentence_result.results = sentence_item_list;
            return jsonConverter.toJson(sentence_result);
        });
    }
}

class SentenceResults {
    String input;
    List<SentenceItem> results;
}

class SentenceItem {
    String input;
    List<AnalyzeWordItem> results;
}