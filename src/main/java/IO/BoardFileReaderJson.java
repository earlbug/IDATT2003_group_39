package IO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import exception.UnknownGameException;
import factory.BoardFactory;
import factory.GameType;
import interfaces.Board;
import interfaces.BoardFileReader;
import interfaces.TileAction;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import models.Tile;
import models.actions.ChanceAction;
import models.actions.LadderAction;
import models.actions.PropertyAction;
import models.actions.SnakeAction;
import models.actions.TaxAction;
import models.actions.WinAction;

/**
 * This class is an implementation of the BoardFileReader class. It provides methods for reading
 * Board objects from a Json file. The folder path where the files are written are already defined
 * in the class.
 */
public class BoardFileReaderJson implements BoardFileReader {
  String folder = "src/main/resources/data/board/";

  /**
   * Constructor for BoarFileReader.
   */
  public BoardFileReaderJson() {}

  /**
   * Returns a Board from the specified file.
   *
   * @param fileName name of the file to read from
   * @return a Board object
   * @throws IOException if the file cannot be read
   */
  @Override
  public Board getBoard(String fileName) throws IOException, UnknownGameException {
    return deserializeBoard(fileName);
  }

  /**
   * Returns a Board from the specified file.
   * Every Tile is deserialized and added to the Board
   *
   * @param boardFileName name of the file to read from.
   * @return a Board object
   * @throws IOException if the file cannot be read
   */
  public Board deserializeBoard(String boardFileName) throws IOException {
    JsonObject boardJson = readJsonFromFile(boardFileName);

    // Find difficulty
    GameType gameType = GameType.valueOf(boardJson.get("gameType").getAsString().toUpperCase());

    // Create board based on gameType
    Board board = BoardFactory.get(gameType);

    // Deserialize tiles and add them to the board
    JsonArray tileArrayJson = boardJson.getAsJsonArray("tiles");
    Tile[] tiles = deserializeTiles(tileArrayJson);
    for (Tile tile : tiles) {
      board.addTile(tile);
    }

    return board;
  }

  /**
   * Deserializes a JsonArray containing tiles into an array of Tile objects.
   * This method handles both the creation of individual tiles and establishes
   * the connections between them based on the next tile identifiers.
   * Tiles with no nextTile will have its nextTile set to null.
   *
   * @param tilesJson The JsonArray containing all tile information to be deserialized
   * @return An array of Tile objects sorted by their tile ID
   */
  public Tile[] deserializeTiles(JsonArray tilesJson) {
    //Creates a map with all Tiles and its next tile id
    // Tile are the key, and an int is the value
    Map<Tile, Integer> tilesMap = new HashMap<>();
    tilesJson.forEach(tileJson -> {
      Map<Tile, Integer> tileAndNextId = deserializeTile((JsonObject) tileJson);
      tilesMap.putAll(tileAndNextId);
    });

    // for all tiles, set its nextTile field based on the next tile id provided in the hashmap.
    for (Entry<Tile, Integer> entry : tilesMap.entrySet()) {
      // get the id to the next Tile
      int nextTileId = entry.getValue();
      // Get the next Tile as a Tile Object, or null if non-existent
      Tile nextTile = tilesMap.keySet().stream()
              .filter(tile -> tile.getTileId() == nextTileId)
              .findFirst()
              .orElse(null);
      // Set the next Tile. Set it to null if it does not exist.
      entry.getKey().setNextTile(nextTile);
    }

    // Return the Tiles as an Array
    Tile[] tileArray = tilesMap.keySet().toArray(new Tile[0]);
    Arrays.sort(tileArray, Comparator.comparingInt(Tile::getTileId));

    return tileArray;
  }

  /**
   * Deserializes a single tile from its JSON representation.
   * Creates a Tile object and extracts the ID of the next tile in the sequence.
   * If the tile has an associated action, it will be deserialized and attached.
   *
   * @param tileJson The JsonObject representing a single tile
   * @return A Map containing a single entry with the deserialized Tile as key and its next tile
   *      ID as value.
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
    int nextTileId;
    if (tileJson.has("nextTileId")) {
      nextTileId = tileJson.get("nextTileId").getAsInt();
    } else {
      // If the Tile has no nextTile, then set nextTileId to -1
      nextTileId = -1;
    }
    tileMap = new HashMap<>();
    tileMap.put(tile, nextTileId);

    return tileMap;
  }

  /**
   * Deserializes a tile action from its JSON representation.
   * Determines the type of action based on the "actionType" field and delegates to
   * the appropriate specialized deserialization method.
   *
   * @param actionTileJson The JsonObject containing the tile action data
   * @return A TileAction implementation (such as LadderAction)
   *      or null if the action type is not recognized
   */
  public TileAction deserializeActionTile(JsonObject actionTileJson) {
    String actionType = actionTileJson.get("actionType").getAsString();

    return switch (actionType) {
      case "chanceAction" -> deserializeChanceAction(actionTileJson);
      case "ladderAction" -> deserializeLadderAction(actionTileJson);
      case "propertyAction" -> deserializePropertyAction(actionTileJson);
      case "snakeAction" -> deserializeSnakeAction(actionTileJson);
      case "taxAction" -> deserializeTaxAction(actionTileJson);
      case "winAction" -> deserializeWinAction(actionTileJson);
      default -> null;
    };
  }

  /**
   * Deserializes a ChanceAction from its Json representation.
   * The optional values description, floor and roof are set if they are present in the ActionTile.
   *
   * @param chanceActionJson the Json to be deserialized
   * @return a ChanceAction object.
   */
  public ChanceAction deserializeChanceAction(JsonObject chanceActionJson) {

    ChanceAction chanceAction = new ChanceAction();

    if (chanceActionJson.has("description")) {
      chanceAction.setDescription(chanceActionJson.get("description").getAsString());
    }
    if (chanceActionJson.has("floor")) {
      chanceAction.setFloor(chanceActionJson.get("floor").getAsInt());
    }
    if (chanceActionJson.has("roof")) {
      chanceAction.setRoof(chanceActionJson.get("roof").getAsInt());
    }

    return chanceAction;
  }


  /**
   * Deserializes a JsonObject into a LadderAction object.
   * The returned LadderAction has a destinationTileId variable and a description variable.
   *
   * @param ladderActionJson the Json to be deserialized.
   * @return a LadderAction object.
   */
  public LadderAction deserializeLadderAction(JsonObject ladderActionJson) {
    int destinationTileId = ladderActionJson.get("destinationTileId").getAsInt();

    LadderAction ladderAction = new LadderAction(destinationTileId);

    if (ladderActionJson.has("description")) {
      ladderAction.setDescription(ladderActionJson.get("description").getAsString());
    }

    return ladderAction;
  }

  /**
   * Deserializes a JsonObject into a PropertyAction object.
   * The returned SnakeAction has a description variable,
   * which is set if the Json has this field.
   *
   * @param propertyActionJson the Json to be deserialized.
   * @return a Property object.
   */
  public PropertyAction deserializePropertyAction(JsonObject propertyActionJson) {
    int landPrice = propertyActionJson.get("landPrice").getAsInt();

    PropertyAction propertyAction = new PropertyAction(landPrice);

    if (propertyActionJson.has("description")) {
      propertyAction.setDescription(propertyActionJson.get("description").getAsString());
    }

    return propertyAction;
  }

  /**
   * Deserializes a JsonObject into a SnakeAction object.
   * The returned SnakeAction has a description,
   * which is set if the Json has this field.
   *
   * @param snakeActionJson the Json to be deserialized.
   * @return a SnakeAction object.
   */
  public SnakeAction deserializeSnakeAction(JsonObject snakeActionJson) {
    int moneyDeducted = snakeActionJson.get("moneyDeducted").getAsInt();

    SnakeAction snakeAction = new SnakeAction(moneyDeducted);

    if (snakeActionJson.has("description")) {
      snakeAction.setDescription(snakeActionJson.get("description").getAsString());
    }

    return snakeAction;
  }

  /**
   * Deserializes a JsonObject into a TaxAction object.
   * The returned TaxAction has a moneyDeducted variable and a description variable,
   * which is set if the Json has these fields.
   *
   * @param taxActionJson the Json to be deserialized.
   * @return a TaxAction object.
   */
  public TaxAction deserializeTaxAction(JsonObject taxActionJson) {
    int moneyDeducted = taxActionJson.get("moneyDeducted").getAsInt();

    TaxAction taxAction = new TaxAction(moneyDeducted);

    if (taxActionJson.has("description")) {
      taxAction.setDescription(taxActionJson.get("description").getAsString());
    }

    return taxAction;
  }

  /**
   * Deserializes a JsonObject into a WinAction object.
   * The Description is set if the Json has this field.
   *
   * @param winActionJson the Json to be deserialized.
   * @return a WinAction object.
   */
  public WinAction deserializeWinAction(JsonObject winActionJson) {

    WinAction winAction = new WinAction();

    if (winActionJson.has("description")) {
      winAction.setDescription(winActionJson.get("description").getAsString());
    }

    return winAction;
  }


  /**
   * Reads a Json file using a FileReader.
   * The method returns a Gson object if the file contains a Board object in a Json format.
   * If the file cannot be read, a IOException is thrown.
   * The path of the file is defined in the class,
   * while only the name of the file is required as parameter.
   *
   * @param fileName the name of the file to be read from.
   * @return the Board as a Gson object
   * @throws IOException if the file cannot be read
   */
  public JsonObject readJsonFromFile(String fileName) throws IOException {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(folder + fileName)) {
      return gson.fromJson(reader, JsonObject.class);
    }
  }

}
