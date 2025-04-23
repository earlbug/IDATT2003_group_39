package factory;

import IO.BoardFileReaderJson;
import interfaces.Board;
import exception.UnknownGameException;
import java.io.IOException;
import models.boards.LudoBoard;
import models.boards.SnakesAndLaddersBoard;

public class BoardFactory {

  public static Board get(GameType gameType) {
    return switch (gameType) {
      case SNAKES_AND_LADDERS -> new SnakesAndLaddersBoard();
      case LUDO -> new LudoBoard();
    };
  }

  public static Board getFromFile(String filename) throws IOException, UnknownGameException {
    BoardFileReaderJson boardFileReaderJson = new BoardFileReaderJson();
    return boardFileReaderJson.getBoard(filename);
  }


}
