package factory;

import IO.BoardFileReaderJson;
import interfaces.Board;
import exception.UnknownGameException;
import java.io.IOException;
import models.boards.LudoBoard;
import models.boards.SnakesAndLaddersBoard;

/**
 * Factory class for creating board instances based on game type.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class BoardFactory {

  /**
   * Creates a board instance based on the provided game type.
   *
   * @param gameType The type of game
   * @return Board instance corresponding to the game type
   */
  public static Board get(GameType gameType) {
    return switch (gameType) {
      case SNAKES_AND_LADDERS -> new SnakesAndLaddersBoard();
      case LUDO -> new LudoBoard();
    };
  }

  /**
   * Creates a board instance from a file.
   *
   * @param filename The name of the file containing the board configuration
   * @return Board instance created from the file
   * @throws IOException          If an error occurs while reading the file
   * @throws UnknownGameException If the game type is unknown
   */
  public static Board getFromFile(String filename) throws IOException, UnknownGameException {
    BoardFileReaderJson boardFileReaderJson = new BoardFileReaderJson();
    return boardFileReaderJson.getBoard(filename);
  }


}
