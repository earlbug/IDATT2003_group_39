package io;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import interfaces.Board;
import interfaces.BoardFileWriter;
import interfaces.TileAction;
import java.io.FileWriter;
import java.io.IOException;
import models.Tile;
import models.actions.LadderAction;

/**
 * This class implements the BoardFileWriter interface and provides JSON serialization functionality
 * for writing a game board to a file. It serializes board data, including tiles and tile actions,
 * into a JSON structure and writes the serialized data to a file in the specified directory.
 */
public class BoardFileWriterJson implements BoardFileWriter {

  String path = "src/main/resources/data/board/";

  /**
   * Default constructor for the BoardFileWriterJson class.
   */
  public BoardFileWriterJson() {
  }

  /**
   * Serializes the given board into a JSON format and writes it to the specified file.
   *
   * @param board    the Board object to be serialized and written to a file
   * @param fileName the name of the file where the serialized Board JSON should be written
   * @throws IOException if an error occurs during the file writing process
   */
  @Override
  public void writeBoard(Board board, String fileName) throws IOException {
    JsonObject boardJson = serializeBoard(board);

    try {
      writeJsonToFile(boardJson, fileName);
    } catch (IOException e) {
      throw new IOException("Could not write board to file: " + path + fileName);
    }

  }

  /**
   * Serializes a Board object into a JSON representation. The serialized JSON includes the tiles of
   * the board, along with their respective properties and actions.
   *
   * @param board the Board object to be serialized into a JSON format
   * @return a JsonObject representing the serialized board
   * @throws IOException if an error occurs during the serialization process
   */
  public JsonObject serializeBoard(Board board) throws IOException {
    JsonObject boardJson = new JsonObject();
    JsonArray tileArrayJson = new JsonArray();

    // Serialize each tile
    for (Tile tile : board.getTiles()) {
      JsonObject tileJson = serializeTile(tile);
      tileArrayJson.add(tileJson);
    }
    boardJson.add("tiles", tileArrayJson);

    return boardJson;
  }

  /**
   * Serializes a Tile object into a JSON representation. The serialized JSON includes the tile's
   * unique identifier and a TileAction, if it has one.
   *
   * @param tile the Tile object to be serialized
   * @return a JsonObject representing the serialized tile, including its ID and any associated
   * action
   */
  private JsonObject serializeTile(Tile tile) {
    JsonObject tileJson = new JsonObject();
    tileJson.addProperty("tileId", tile.getTileId());
    if (tile.getNextTile() != null) {
      tileJson.addProperty("nextTileId", tile.getNextTile().getTileId());
    }

    JsonObject tileActionJson = serializeTileAction(tile.getLandAction());
    if (tileActionJson != null) {
      tileJson.add("type", tileActionJson);
    }
    return tileJson;
  }

  /**
   * serializes a tileAction object by finding out what class it is, then applying the serialize
   * method that applies to that specific tileAction.
   *
   * @param tileAction the tileAction to serialize
   * @return the serialized tileAction
   */
  public JsonObject serializeTileAction(TileAction tileAction) {
    JsonObject tileActionJson = null;

    // LadderAction
    if (tileAction instanceof LadderAction) {
      tileActionJson = serializeLadderAction((LadderAction) tileAction);
    }
    //SnakeAction
    // if (tileAction instanceof SnakeAction) {
    // tileActionJson = serializeLadderSnakeAction((SnakeAction) tileAction);
    //    }

    return tileActionJson;
  }

  /**
   * Serializes a LadderAction object into a JSON representation. The serialized JSON includes the
   * action type, description, and destination tile ID.
   *
   * @param ladderAction the LadderAction object to be serialized
   * @return a JsonObject representing the serialized LadderAction, containing its type,
   * description, and destination tile ID
   */
  public JsonObject serializeLadderAction(LadderAction ladderAction) {
    JsonObject ladderActionJson = new JsonObject();
    ladderActionJson.addProperty("actionType", "ladderAction");
    ladderActionJson.addProperty("description", ladderAction.getDescription());
    ladderActionJson.addProperty("destinationTileId", ladderAction.getDestinationTileId());
    return ladderActionJson;
  }

  /**
   * Writes the given JSON representation of a board to a specified file. The output file will be
   * written at the path defined by the class field `path` concatenated with the provided file name.
   * The JSON is formatted with pretty printing for readability and includes null values.
   *
   * @param jsonBoard the JSON representation of the board to be written to a file
   * @param fileName  the name of the file where the JSON representation will be written
   * @throws IOException if an error occurs during the file writing process
   */
  public void writeJsonToFile(JsonObject jsonBoard, String fileName) throws IOException {

    try (FileWriter writer = new FileWriter(path + fileName)) {
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.setPrettyPrinting();
      gsonBuilder.serializeNulls();
      Gson gson = gsonBuilder.create();
      writer.write(gson.toJson(jsonBoard));
    }
  }


}
