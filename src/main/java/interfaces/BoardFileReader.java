package interfaces;

import exception.UnknownGameException;
import java.io.IOException;

public interface BoardFileReader {

  public Board getBoard(String fileName) throws IOException, UnknownGameException;

}
