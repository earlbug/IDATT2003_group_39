package IO;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.Board;
import interfaces.TileAction;
import java.io.FileWriter;
import java.io.IOException;
import models.LadderAction;
import models.Tile;

public class BoardWriter {
  String path = "src/main/resources/data/board/";

  public void serializeBoard(Board board, String fileName) throws  IOException{
    JsonObject boardJson = new JsonObject();
    JsonArray tileArrayJson = new JsonArray();


    for(Tile tile : board.getTiles()) {
      JsonObject tileJson = serializeTile(tile);
      tileArrayJson.add(tileJson);
    }
    boardJson.add("tiles", tileArrayJson);
    try {
      writeJsonToFile(boardJson, fileName);
    } catch (IOException e) {
      throw new IOException("Could not write board to file: " + path + fileName);
    }
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
