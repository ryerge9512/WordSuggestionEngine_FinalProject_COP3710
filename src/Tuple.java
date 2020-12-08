public class Tuple {

  private double confidence;
  private double support;
  private String targetWord;
  private String suggestedWord;

  Tuple(String targetWord, String suggestedWord, double confidence, double support) {
    this.targetWord = targetWord;
    this.suggestedWord = suggestedWord;
    this.confidence = confidence;
    this.support = support;
  }

  protected double getSupport() {
    return support;
  }

  String getTargetWord() {
    return targetWord;
  }

  String getSuggestedWord() {
    return suggestedWord;
  }
}
