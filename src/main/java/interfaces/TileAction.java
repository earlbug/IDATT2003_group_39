package interfaces;

import models.BoardGame;
import models.Player;
import models.Tile;

/**
 * Interface for action classes. The perform method defines what will happen to a player when it
 * lands on a tile.
 *
 * @author Erlend Sundsdal
 * @version 0.1.0
 * @since 0.1.0
 */
public interface TileAction {

  /**
   * Performs the action associated with the tile.
   *
   * @param player The player who landed on the tile
   */
  void perform(Player player);

  void perform(Player player, BoardGame boardGame);
}
