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
        "C:\\Users\\Owner\\Desktop\\FGCU\\Spring_2020\\IntroToDataEngineering"
            + "\\WordSuggestionEngine_FinalProject_COP3710\\src\\messages.txt";

    MessageStream messages = new MessageStream(filePath);
    List<String> messageWords = messages.getMessageWords();
    AffinityWords analysis = new AffinityWords(messageWords);

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a word: ");

    String input = sc.nextLine();

    DisplayWords show = new DisplayWords(analysis, input);
    show.showWords();
  }
}
