package IO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controllers.Board;
import interfaces.BoardFileReader;
import interfaces.TileAction;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import models.LadderAction;
import models.Tile;

public class BoardFileReaderJson implements BoardFileReader {
  String folder = "src/main/resources/data/board/";

  public BoardFileReaderJson() {}

  @Override
  public Board getBoard(String fileName) throws IOException{
    return deserializeBoard(fileName);
  }

  public Board deserializeBoard(String boardFileName) throws IOException{
    JsonObject boardJson = readJsonFromFile(boardFileName);
    JsonArray tileArrayJson = boardJson.getAsJsonArray("tiles");

    Tile[] tiles = deserializeTiles(tileArrayJson);

    Board board = new Board();
    for(Tile tile : tiles) {
      board.addTile(tile);
    }

    return board;
  }

  public Tile[] deserializeTiles(JsonArray tilesJson) {
    ArrayList<Tile> tiles = new ArrayList<>();
    tilesJson.forEach(tileJson -> tiles.add(deserializeTile((JsonObject) tileJson)));
    return tiles.toArray(new Tile[0]);
  }


  public Tile deserializeTile(JsonObject tileJson) {
    Tile tile;
    int tileId = tileJson.get("tileId").getAsInt();
    tile = new Tile(tileId);

    if (tileJson.has("type")){
      JsonObject tileActionJson = tileJson.getAsJsonObject("type");
      TileAction tileAction = deserializeActionTile(tileActionJson);
      tile.setLandAction(tileAction);
    }

    return tile;

  }

  public TileAction deserializeActionTile(JsonObject actionTileJson) {
    String actionType = actionTileJson.get("actionType").getAsString();
    TileAction tileAction;
    switch (actionType) {
      case "ladderAction": tileAction = deserializeLadderAction(actionTileJson);
      break;
      case null: tileAction = null;
      default: tileAction = null;
    }

    return tileAction;
  }

  public LadderAction deserializeLadderAction(JsonObject ladderActionJson) {
    int destinationTileId = ladderActionJson.get("destinationTileId").getAsInt();
    String description = ladderActionJson.get("description").getAsString();
    LadderAction ladderAction = new LadderAction(destinationTileId, description);
    return ladderAction;
  }



  public JsonObject readJsonFromFile(String fileName) throws IOException {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(folder + fileName)) {
      return gson.fromJson(reader, JsonObject.class);
    }
  }

}
