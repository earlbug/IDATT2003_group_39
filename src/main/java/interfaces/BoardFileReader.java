package interfaces;

import controllers.Board;
import java.io.IOException;

public interface BoardFileReader {

  public Board getBoard(String fileName) throws IOException;

}
