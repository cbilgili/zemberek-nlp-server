package morphology;

import zemberek.core.logging.Log;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.morphology.analysis.tr.TurkishSentenceAnalyzer;

import java.io.IOException;

public class FindPOS {

    TurkishSentenceAnalyzer analyzer;

    public FindPOS(TurkishSentenceAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    private void test(String s) {
        System.out.println("Sentence  = " + s);
        SentenceAnalysis analysis = analyzer.analyze(s);
        analyzer.disambiguate(analysis);


        for (SentenceAnalysis.Entry entry : analysis) {
            WordAnalysis wa = entry.parses.get(0);
            Log.info("%s -> %s : %s ",
                    entry.input,
                    wa.dictionaryItem.primaryPos,
                    wa.dictionaryItem.secondaryPos);
        }
    }

    public static void main(String[] args) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
        TurkishSentenceAnalyzer sentenceAnalyzer = new TurkishSentenceAnalyzer(
                morphology,
                disambiguator
        );
        new FindPOS(sentenceAnalyzer)
                .test("İstanbul'dan başlayan yolculuğumuz 2 gün sonra Afrodisias'ta tamamlanıyor. Efes tam anlamıyla bir şaheserdir.");

    }
}
