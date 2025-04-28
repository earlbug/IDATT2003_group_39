package models.boards;

import interfaces.Board;
import java.util.HashMap;
import java.util.Map;
import models.Tile;

public class SnakesAndLaddersBoard implements Board {

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

  public Tile[] getTiles() {
    return tiles.values().toArray(new Tile[0]);
  }

  public int getNumberOfTiles(){
    return tiles.size();
  }
}
