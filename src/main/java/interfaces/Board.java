package interfaces;

import models.Tile;

/**
 * Represents a board in ladder game. It has the responsibility of managing the tiles of the game.
 *
 * @author Erlend Sundsdal
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Board {

  /**
   * Gets a tile from the board.
   *
   * @param tileId the id of the tile to get
   * @return the tile with the given id
   */
  Tile getTile(int tileId);

  /**
   * Adds a tile to the board.
   *
   * @param tile the tile to add
   */
  void addTile(Tile tile);

  /**
   * Gets all tiles on the board.
   *
   * @return all tiles on the board
   */
  Tile[] getTiles();

  /**
   * Gets the number of tiles on the board.
   *
   * @return the number of tiles on the board
   */
  int getNumberOfTiles();
}
