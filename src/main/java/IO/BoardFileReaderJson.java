package IO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.javafx.scene.control.inputmap.InputMap.Mapping;
import controllers.Board;
import interfaces.BoardFileReader;
import interfaces.TileAction;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import models.LadderAction;
import models.Tile;

/**
 * This class is an implementation of the BoardFileReader class. It provides methods for reading
 * Board objects from a Json file. The folder path where the files are written are already defined
 * in the class.
 */
public class BoardFileReaderJson implements BoardFileReader {
  String folder = "src/main/resources/data/board/";

  public BoardFileReaderJson() {}

  /**
   * Returns a Board from the specified file. The board is not completely finished when returned yet.
   * missing features are: nextTile.
   * @param fileName name of the file to read from
   * @return a Board object
   * @throws IOException if the file cannot be read
   */
  @Override
  public Board getBoard(String fileName) throws IOException{
    return deserializeBoard(fileName);
  }

  /**
   * Returns a Board from the specified file. The board is not completely finished when returned yet.
   * missing features are: nextTile.
   * Every Tile is deserialized and added to the Board
   * @param boardFileName name of the file to read from
   * @return a Board object
   * @throws IOException if the file cannot be read
   */
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

  /**
   * deserialized each Tile object and returns it as a Tile array.
   * @param tilesJson Tiles to be deserialized as a JsonArray
   * @return a Tile array
   */
  public Tile[] deserializeTiles(JsonArray tilesJson) {
    //Creates a map with all Tiles and its next tile id
    Map<Tile, Integer> tiles = new HashMap<>();
    tilesJson.forEach(tileJson -> {
      Map<Tile, Integer> tileAndNextId = deserializeTile((JsonObject) tileJson);
      tiles.putAll(tileAndNextId);
      });

    // for all tiles, set its nextTile field based on the next tile id provided in the hashmap.
    for(Entry<Tile, Integer> entry : tiles.entrySet()) {
      // get the id to the next Tile
      int nextTileId = entry.getValue();
      // Get he next Tile as a Tile Object, or null if non-existent
      Tile nextTile = tiles.keySet().stream()
              .filter(tile -> tile.getTileId() == nextTileId)
              .findFirst()
              .orElse(null);
      // Set the next Tile
      entry.getKey().setNextTile(nextTile);
    }

    // Return the Tiles as an Array
    Tile[] tileArray = tiles.keySet().toArray(new Tile[0]);
    Arrays.sort(tileArray, Comparator.comparingInt(Tile::getTileId));

    return tileArray;
  }

  /**
   * Deserializes the Tile end returns this Tile and the id to the next Tile.
   * @param tileJson
   * @return
   */
  public Map<Tile, Integer> deserializeTile(JsonObject tileJson) {
    Tile tile;
    int tileId = tileJson.get("tileId").getAsInt();
    tile = new Tile(tileId);

    if (tileJson.has("type")) {
      JsonObject tileActionJson = tileJson.getAsJsonObject("type");
      TileAction tileAction = deserializeActionTile(tileActionJson);
      tile.setLandAction(tileAction);
    }
    // Gets the next tile as an int
    Map<Tile, Integer> tileMap;
    Integer nextTileId = -1;
    try {
      nextTileId = tileJson.get("nextTileId").getAsInt();
    } catch (Exception e) {
      nextTileId = -1;
    } finally {
      tileMap = new HashMap<>();
      tileMap.put(tile, nextTileId);
    }

    return tileMap;
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
