package IO;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.Board;
import interfaces.BoardFileWriter;
import interfaces.TileAction;
import java.io.FileWriter;
import java.io.IOException;
import models.actions.LadderAction;
import models.Tile;

public class BoardFileWriterJson implements BoardFileWriter {
  String path = "src/main/resources/data/board/";

  public BoardFileWriterJson(){}

  @Override
  public void writeBoard(Board board, String fileName) throws IOException{
    JsonObject boardJson = serializeBoard(board);

    try {
      writeJsonToFile(boardJson, fileName);
    } catch (IOException e) {
      throw new IOException("Could not write board to file: " + path + fileName);
    }

  }

  public JsonObject serializeBoard(Board board) throws  IOException{
    JsonObject boardJson = new JsonObject();
    JsonArray tileArrayJson = new JsonArray();


    for(Tile tile : board.getTiles()) {
      JsonObject tileJson = serializeTile(tile);
      tileArrayJson.add(tileJson);
    }
    boardJson.add("tiles", tileArrayJson);

    return boardJson;
  }

  private JsonObject serializeTile(Tile tile) {
    JsonObject tileJson = new JsonObject();
    tileJson.addProperty("tileId", tile.getTileId());


    JsonObject tileActionJson = serializeActionTile(tile.getLandAction());
    if (tileActionJson != null) {
      tileJson.add("type", tileActionJson);
    }
    return tileJson;
  }


  public JsonObject serializeActionTile(TileAction actionTile) {
    JsonObject actionTileJson = null;

    if (actionTile instanceof LadderAction) {
      actionTileJson = serializeLadderAction((LadderAction) actionTile);
    }
//    if (actionTile instanceof SnakeAction) {
//      actionTileJson = serializeLadderSnakeAction((SnakeAction) actionTile);
//    }

    return actionTileJson;
  }



  public JsonObject serializeLadderAction(LadderAction ladderAction) {
    JsonObject ladderActionJson = new JsonObject();
    ladderActionJson.addProperty("actionType", "ladderAction");
    ladderActionJson.addProperty("description", ladderAction.getDescription());
    ladderActionJson.addProperty("destinationTileId", ladderAction.getDestinationTileId());
    return ladderActionJson;
  }

  public void writeJsonToFile(JsonObject jsonBoard, String fileName) throws IOException {
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter(path + fileName)) {
      gson.toJson(jsonBoard, writer);
    }
  }



}
