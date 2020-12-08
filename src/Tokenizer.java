import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tokenizer {

    private List<String> tokens;

    Tokenizer() {
      System.out.println("Nothing to tokenize!");
    }

  /**
   * Constructor checks if path is valid and then sends to createStream().
   *
   * @param filePath pathname of file location.
   * @throws IOException thrown if path is invalid.
   */
  public Tokenizer(String filePath) throws IOException {

    Path messageFile;

    try {
      messageFile = Paths.get(filePath);
      createStream(messageFile);

    } catch (IOException io) {
      io.printStackTrace();
      System.out.println("Error parsing file.");
    }
  }

  /**
   * Whitespace from file is removed and stream is then sent to createList().
   *
   * @param messageFile Stream to be transformed into a List.
   * @throws IOException thrown if path is invalid.
   */
  private void createStream(Path messageFile) throws IOException {

    try {
        Stream<String> messageLines = Files.lines(messageFile).filter(curLine -> !curLine.isBlank());
      createTokens(messageLines);

    } catch (IOException io) {
      io.printStackTrace();
      System.out.println("Error creating stream.");
    }
  }

  /**
   * Tokens are generated from Stream and put into a List.
   *
   * @param messageLines incoming Stream to be put into a List.
   */
  private void createTokens(Stream<String> messageLines) {
    tokens =
        messageLines
            .map(String::toLowerCase)
            .map(curLine -> curLine.split("\\s+"))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());
  }

  /** For testing purposes, the tokens are viewed from this method. */
  protected void showTokens() {
    tokens.forEach(System.out::println);
  }

  /**
   * Returns generated list of preprocessed tokens.
   *
   * @return List of tokens to have AA performed.
   */
 public List<String> getTokens() {
    return tokens;
  }
}
