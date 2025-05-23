package interfaces;

import exception.UnknownGameException;
import java.io.IOException;

/**
 * Interface for reading board configurations from files.
 *
 * @author Erlend Sundsdal
 */
public interface BoardFileReader {

  /**
   * Reads a board configuration from a file and returns the corresponding Board object.
   *
   * @param fileName The name of the file containing the board configuration
   * @throws IOException          If an error occurs while reading the file
   * @throws UnknownGameException If the game type is unknown
   */
  Board getBoard(String fileName) throws IOException, UnknownGameException;

}
