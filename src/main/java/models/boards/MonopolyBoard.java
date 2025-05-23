package models.boards;

import interfaces.Board;
import java.util.HashMap;
import java.util.Map;
import models.Tile;

/**
 * A class representing a Monopoly board. It contains a list of tiles and methods to manipulate
 * them.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class MonopolyBoard implements Board {

  private final Map<Integer, Tile> tiles = new HashMap<>();

  /**
   * Gets a tile from the list of tiles.
   *
   * @param tileId the id of the tile to get
   * @return the tile with the specified id
   */
  public Tile getTile(int tileId) {
    return tiles.get(tileId - 1);
  }

  /**
   * Adds Tile parameter to list of tiles. Its ID will be the number of tiles already existing.
   *
   * @param tile to be added in
   */
  public void addTile(Tile tile) {
    tiles.put(tiles.size(), tile);
  }

  public Tile[] getTiles() {
    return tiles.values().toArray(new Tile[0]);
  }

  public int getNumberOfTiles() {
    return tiles.size();
  }
}
