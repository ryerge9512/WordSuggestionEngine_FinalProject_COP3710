import java.util.ArrayList;

public class DisplayWords {

  String[] connectors = {"the", "this", "of"};
  ArrayList<WordEngine> words;
  ArrayList<String> suggestWords = new ArrayList<>();

  /**
   * Constructor takes user input is compared to suggested words based on AA calculations.
   *
   * @param analysis WordEngine objects returned from AA.
   * @param input User input collected in console.
   */
  protected DisplayWords(AffinityWords analysis, String input) {
    words = analysis.getSuggestedWords();
    int i = 0;

    for (WordEngine aWord : words) {
      if (input.equals(aWord.getChosenWord()) && aWord.getSupport() >= 0.65) {
        if (suggestWords.size() < 3) {
          suggestWords.add(aWord.getSuggestedWord());
        }
      }
    }

    while (suggestWords.size() < 3) {
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
