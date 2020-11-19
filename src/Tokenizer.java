import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tokenizer {

    private List<String> messageWords;

  /**
   * Constructor checks if path is valid and then sends to createStream().
   *
   * @param filePath pathname of file location.
   * @throws IOException thrown if path is invalid.
   */
  protected Tokenizer(String filePath) throws IOException {

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
  protected void createStream(Path messageFile) throws IOException {

    try {
        Stream<String> messageLines = Files.lines(messageFile).filter(curLine -> !curLine.isBlank());
      createList(messageLines);

    } catch (IOException io) {
      io.printStackTrace();
      System.out.println("Error creating stream.");
    }
  }

  /**
   * List is generated from Stream.
   *
   * @param messageLines incoming Stream to be put into a List.
   */
  protected void createList(Stream<String> messageLines) {
    messageWords =
        messageLines
            .map(String::toLowerCase)
            .map(curLine -> curLine.split("\\s+"))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());
  }

  /** For testing purposes, the List is viewed from this method. */
  protected void showList() {
    messageWords.forEach(System.out::println);
  }

  /**
   * Returns generated list.
   *
   * @return List to have AA performed.
   */
  protected List<String> getMessageWords() {
    return messageWords;
  }
}