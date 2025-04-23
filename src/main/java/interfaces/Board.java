package interfaces;

import factory.GameType;
import models.boards.SnakesAndLaddersBoard;
import models.boards.LudoBoard;
import models.Tile;

/**
 * Represents a board in ladder game. It has the responsibility of managing the tiles of the game.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
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
