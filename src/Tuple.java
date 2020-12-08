public class Tuple {

  private double confidence;
  private double support;
  private String targetWord;
  private String suggestedWord;

  public Tuple(String targetWord, String suggestedWord, double confidence, double support) {
    this.targetWord = targetWord;
    this.suggestedWord = suggestedWord;
    this.confidence = confidence;
    this.support = support;
  }

  protected double getSupport() {
    return support;
  }

  protected String getTargetWord() {
    return targetWord;
  }

  protected String getSuggestedWord() {
    return suggestedWord;
  }
}
