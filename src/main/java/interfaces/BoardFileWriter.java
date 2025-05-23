package interfaces;

import java.io.IOException;

/**
 * Interface for writing board configurations to files.
 *
 * @author Erlend Sundsdal
 */
public interface BoardFileWriter {

  /**
   * Writes a board configuration to a file.
   *
   * @param board    The board to write
   * @param fileName The name of the file to write to
   * @throws IOException If an error occurs while writing the file
   */
  void writeBoard(Board board, String fileName) throws IOException;

}
