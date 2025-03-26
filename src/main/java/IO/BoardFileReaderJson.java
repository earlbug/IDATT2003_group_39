package IO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.Board;
import interfaces.BoardFileReader;
import java.io.FileReader;
import java.io.IOException;

public class BoardFileReaderJson implements BoardFileReader {
  Board board;
  String folder = "src/main/resources/data/board/";


  @Override
  public Board getBoard(String fileName) throws IOException{
    return deserialize(fileName);
  }

  public Board deserialize(String boardFileName) throws IOException{
    JsonObject boardJson = readJsonFromFile(boardFileName);



  }



  public JsonObject readJsonFromFile(String fileName) throws IOException {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(folder + fileName)) {
      return gson.fromJson(reader, JsonObject.class);
    }
  }

}
