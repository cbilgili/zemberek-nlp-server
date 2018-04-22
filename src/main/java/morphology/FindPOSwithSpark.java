package morphology;

import zemberek.core.logging.Log;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.morphology.analysis.tr.TurkishSentenceAnalyzer;
import zemberek.normalization.TurkishSpellChecker;
//
import java.io.IOException;
import static spark.Spark.*;

import java.util.*;
import com.google.gson.Gson;

public class FindPOSwithSpark {

    TurkishSentenceAnalyzer analyzer;
    TurkishMorphology morphology;
    TurkishSpellChecker spellChecker;

    //public AnalyzeWords(TurkishMorphology morphology) {
       // this.morphology = morphology;
    //}

    public FindPOSwithSpark(TurkishSentenceAnalyzer analyzer, TurkishMorphology morphology, TurkishSpellChecker spellChecker) {
        this.analyzer = analyzer;
        this.morphology = morphology;
        this.spellChecker = spellChecker;
    }

    private Albums test(String s) {
//        System.out.println("Sentence  = " + s);
//        SentenceAnalysis analysis = analyzer.analyze(s);
//        analyzer.disambiguate(analysis);
//
//
//        for (SentenceAnalysis.Entry entry : analysis) {
//            WordAnalysis wa = entry.parses.get(0);
//            Log.info("%s -> %s : %s ",
//                    entry.input,
//                    wa.dictionaryItem.primaryPos,
//                    wa.dictionaryItem.secondaryPos);
//        }

        Albums albums = new Albums();
        albums.title = s;
        SentenceAnalysis analysis = analyzer.analyze(albums.title);
        analyzer.disambiguate(analysis);


        for (SentenceAnalysis.Entry entry : analysis) {
            WordAnalysis wa = entry.parses.get(0);
            Dataset dataset = new Dataset();
            dataset.input = entry.input;
            dataset.primary_pos = wa.dictionaryItem.primaryPos.toString();
            dataset.secondary_pos = wa.dictionaryItem.secondaryPos.toString();
            List<WordAnalysis> results = morphology.analyze(entry.input);
            for (WordAnalysis result : results) {
                WordMorphology word_morphology = new WordMorphology();
                word_morphology.format_long = result.formatLong();
                word_morphology.format_no_empty = result.formatNoEmpty();
                word_morphology.format_of_laser = result.formatOflazer();
                word_morphology.format_only_igs = result.formatOnlyIgs();
                //word_morphology.stems = result.getStems();
                word_morphology.lemmas = result.getLemmas();
                //word_morphology.spells = spellChecker.suggestForWord(entry.input);
                word_morphology.correct_spelled = spellChecker.check(entry.input);
                dataset.morphologies.add(word_morphology);
            }
            albums.dataset.add(dataset);
//            Log.info("%s -> %s : %s ",
//                    entry.input,
//                    wa.dictionaryItem.primaryPos,
//                    wa.dictionaryItem.secondaryPos);
        }
        return albums;
    }

    public static void main(String[] args) throws IOException {
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
        TurkishSpellChecker spellChecker = new TurkishSpellChecker(morphology);
        TurkishSentenceAnalyzer sentenceAnalyzer = new TurkishSentenceAnalyzer(
                morphology,
                disambiguator
        );
//        new FindPOSwithSpark(sentenceAnalyzer)
//                .test("İstanban başlayan yolculuğumuz 2 gün sonra Afrodisias'ta tamamlanıyor.");
        get("/find_pos", (req, res) -> {
            //req.params(":id")
            //req.queryParams("name");
            //req.queryParams("email")
            Albums albums = new FindPOSwithSpark(sentenceAnalyzer, morphology, spellChecker)
                .test(req.queryParams("sentence"));

            return new Gson().toJson(albums);
        });
        after((req, res) -> {
            res.type("application/json");
        });

    }
}


class Albums {
    public String title;
    public String message;
    public List<String> errors = new ArrayList<String>();
    public String total;
    public int total_pages;
    public int page;
    public String limit;
    List<Dataset> dataset = new ArrayList<Dataset>();
}



class Dataset {
    public String input;
    public String primary_pos;
    public String secondary_pos;
    //@SerializedName("album_images")
    List<WordMorphology> morphologies = new ArrayList<WordMorphology>();
}


class WordMorphology {
    //public List<String> stems = new ArrayList<String>();
    public List<String> lemmas = new ArrayList<String>();
    //public List<String> spells = new ArrayList<String>();
    public Boolean correct_spelled;
    public String format_long;
    public String format_no_empty;
    public String format_of_laser;
    public String format_only_igs;
    //@SerializedName("album_images")
    //List<&ltAlbumImages&gt images = new ArrayList&ltAlbumImages&gt();
}