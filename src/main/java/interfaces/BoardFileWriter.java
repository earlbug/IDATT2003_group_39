package interfaces;

import controllers.Board;
import java.io.IOException;

public interface BoardFileWriter {

  public void writeBoard(Board board, String fileName) throws IOException;

}
