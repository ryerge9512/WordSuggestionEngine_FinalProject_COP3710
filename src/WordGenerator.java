import java.util.ArrayList;

public class WordGenerator {

  private ArrayList<String> suggestWords = new ArrayList<>();

  /**
   * Constructor takes user input is compared to suggested words based on AA calculations.
   *
   * @param analysis WordEngine objects returned from AA.
   * @param input User input collected in console.
   */
  protected WordGenerator(Vectorizer analysis, String input) {
    final ArrayList<Tuple> words = analysis.getSuggestedWords();
    final int MAX_SUGGESTED_WORDS = 3;
    int i = 0;

    for (Tuple aWord : words) {
      if (input.equals(aWord.getTargetWord())) {
        if (suggestWords.size() < MAX_SUGGESTED_WORDS) {
          suggestWords.add(aWord.getSuggestedWord());
        }
      }
    }

    while (suggestWords.size() < MAX_SUGGESTED_WORDS) {
      String[] connectors = {"the", "this", "of"};
      suggestWords.add(connectors[i++]);
    }
  }

  /** Suggested words are then displayed in the console. */
  protected void showWords() {
    System.out.println("Your suggested words are: ");
    for (String word : suggestWords) {
      System.out.println(word);
    }
  }
}
