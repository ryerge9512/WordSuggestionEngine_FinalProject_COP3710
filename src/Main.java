import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

  /**
   * Messages.txt file is extracted and transformed into a Stream to be loaded into a List. List is
   * then sent to have AA peformed. User input is taken and suggested words are then generated in
   * the console.
   *
   * @param args default.
   */
  public static void main(String[] args) throws IOException {

    String filePath =
            "/Users/ryanyerge/Documents/FGCU/WordSuggestionEngine_FinalProject_COP3710/" +
                    "src/messages.txt";

//        "C:\\Users\\Owner\\Desktop\\FGCU\\Spring_2020\\IntroToDataEngineering"
//            + "\\WordSuggestionEngine_FinalProject_COP3710\\src\\messages.txt";

    Tokenizer messages = new Tokenizer(filePath);
    List<String> tokens = messages.getTokens();
    Vectorizer analysis = new Vectorizer(tokens);

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a word: ");

    String input = sc.nextLine();

    WordGenerator suggestedWords = new WordGenerator(analysis, input);
    suggestedWords.showWords();
  }
}
