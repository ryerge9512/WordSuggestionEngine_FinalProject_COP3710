public class WordEngine {

  private double confidence;
  private double support;
  private String chosenWord;
  private String suggestedWord;

  public WordEngine(String chosenWord, String suggestedWord, double confidence, double support) {
    this.chosenWord = chosenWord;
    this.suggestedWord = suggestedWord;
    this.confidence = confidence;
    this.support = support;
  }

  protected double getSupport() {
    return support;
  }

  protected String getChosenWord() {
    return chosenWord;
  }

  protected String getSuggestedWord() {
    return suggestedWord;
  }
}
