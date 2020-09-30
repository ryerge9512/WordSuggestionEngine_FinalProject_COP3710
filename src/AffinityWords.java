import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AffinityWords {

  private static Integer totalCount = 0;
  private Map<String, Integer> bag = new HashMap<>();
  private Map<Set<String>, Integer> bgrams = new HashMap<>();
  private Map<String, Integer> allPairs = new HashMap<>();
  private ArrayList<WordEngine> suggestedWords = new ArrayList();

  public AffinityWords(List<String> messageWords) {
    createUnigram(messageWords);
  }

  /**
   * Unigram of all occurring words is created for bag.
   *
   * @param messageWords List to be put into bag.
   */
  protected void createUnigram(List<String> messageWords) {
    for (String word : messageWords) {
      bag.merge(word, 1, Integer::sum);
    }
    createBigram(messageWords);
  }

  /**
   * Bigram of all word pair occurrences is created.
   *
   * @param messageWords List of words to be processed.
   */
  protected void createBigram(List<String> messageWords) {
    for (int i = 1; i < messageWords.size(); i++) {
      bgrams.merge(
          new HashSet<>(Arrays.asList(messageWords.get(i - 1), messageWords.get(i))),
          1,
          Integer::sum);
    }
    bgrams.forEach((key, value) -> totalCount += 1);
    createPairMapping();
  }

  /** All pairs that contain words from bag are mapped. */
  protected void createPairMapping() {
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
   * AA calculations are performed here and suggested words are added to a data structure
   * (WordEngine).
   */
  protected void doAffinityAnalysis() {
    for (String word : allPairs.keySet()) {
      for (Set<String> wordPairs : bgrams.keySet()) {

        List<String> pairings = wordPairs.stream().collect(Collectors.toList());

        double confidence = (double) bgrams.get(wordPairs) / allPairs.get(word);
        double support = (double) allPairs.get(word) / totalCount;

        suggestedWords.add(new WordEngine(pairings.get(1), pairings.get(0), confidence, support));
      }
    }
  }

  /**
   * Data structures of suggested words added to an ArrayList.
   *
   * @return Returns ArrayList of WordEngine objects containing suggested words.
   */
  protected ArrayList<WordEngine> getSuggestedWords() {
    return suggestedWords;
  }
}
