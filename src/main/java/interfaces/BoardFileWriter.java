package interfaces;

import java.io.IOException;

public interface BoardFileWriter {

  public void writeBoard(Board board, String fileName) throws IOException;

}
