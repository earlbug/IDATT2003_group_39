package controllers;

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
  private Map<Integer, Tile> tiles;

  public Board(){}

  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }

  public void addTile(Tile tile) {
    tiles.put(tiles.size(), tile);
  }



}
