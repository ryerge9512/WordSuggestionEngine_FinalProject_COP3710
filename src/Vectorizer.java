import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Vectorizer {

  private static Integer totalCount = 0;
  private Map<String, Integer> bag = new HashMap<>();
  private Map<Set<String>, Integer> bgrams = new HashMap<>();
  private Map<String, Integer> allPairs = new HashMap<>();
  private ArrayList<Tuple> suggestedWords = new ArrayList();

 Vectorizer() {
    System.out.println("Nothing to Vectorize here!!");
  }

 public Vectorizer(List<String> tokens) {
    createUnigram(tokens);
  }

  /**
   * Unigram of all occurring words is created for bag.
   *
   * @param tokens List to be put into bag.
   */
  private void createUnigram(List<String> tokens) {
    for (String word : tokens) {
      bag.merge(word, 1, Integer::sum);
    }
    createBigram(tokens);
  }

  /**
   * Bigram of all word pair occurrences is created.
   *
   * @param tokens List of words to be processed.
   */
  private void createBigram(List<String> tokens) {
    for (int i = 1; i < tokens.size(); i++) {
      bgrams.merge(
          new HashSet<>(Arrays.asList(tokens.get(i - 1), tokens.get(i))),
          1,
          Integer::sum);
    }
    bgrams.forEach((key, value) -> totalCount += 1);
    createPairMapping();
  }

  /** All pairs that contain words from bag are mapped. */
  private void createPairMapping() {
    for (String word : bag.keySet()) {
      for (Set<String> wordPairs : bgrams.keySet()) {
        List<String> pairings = wordPairs.stream().collect(Collectors.toList());

        if (pairings.get(1).equals(word)) {
          allPairs.merge(word, bgrams.get(wordPairs), Integer::sum);
        }
      }
    }
    doAffinityAnalysis();
  }

  /**
   * Confidence and Support levels are calculated for each targetWord and suggestedWord Tuple.
   */
  private void doAffinityAnalysis() {
    final int TARGET_WORD = 1;
    final int SUGGESTED_WORD = 0;
    final double TARGET_SUPPORT = 0.65;

    for (String word : allPairs.keySet()) {
      for (Set<String> wordPairs : bgrams.keySet()) {

        List<String> newTuples = wordPairs.stream().collect(Collectors.toList());

        double confidence = (double) bgrams.get(wordPairs) / allPairs.get(word);
        double support = (double) allPairs.get(word) / totalCount;

        if(support >= TARGET_SUPPORT) {
          suggestedWords.add(new Tuple(
                  newTuples.get(TARGET_WORD), newTuples.get(SUGGESTED_WORD), confidence, support));
        }
      }
    }
  }

  /**
   * Data structures of suggested words added to an ArrayList.
   *
   * @return Returns ArrayList of WordEngine objects containing suggested words.
   */
  public ArrayList<Tuple> getSuggestedWords() {
    return suggestedWords;
  }
}
