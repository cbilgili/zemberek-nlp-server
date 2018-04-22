package morphology;

import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

import java.io.IOException;
import java.util.List;

public class StemmingAndLemmatization {
    TurkishMorphology morphology;

    public StemmingAndLemmatization(TurkishMorphology morphology) {
        this.morphology = morphology;
    }

    public void analyze(String word) {
        System.out.println("Word = " + word);

        System.out.println("Parses: ");
        List<WordAnalysis> results = morphology.analyze(word);
        for (WordAnalysis result : results) {
            System.out.println(result.formatLong());
            System.out.println("\tStems = " + result.getStems());
            System.out.println("\tLemmas = " + result.getLemmas());
        }
    }

    public static void main(String[] args) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        new StemmingAndLemmatization(morphology).analyze("kitabımızsa");
    }
}
