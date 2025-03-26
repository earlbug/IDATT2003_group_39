package controllers;

import java.util.HashMap;
import java.util.Map;
import models.Tile;

/**
 * Represents a board in ladder game. It has the responsibility of managing the tiles of the game.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 */
public class Board {
  private final Map<Integer, Tile> tiles = new HashMap<>();

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



}
