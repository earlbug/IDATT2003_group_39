package observers;

import models.BoardGame;
import models.Player;

/**
 * Interface for observers that want to be notified about changes in BoardGame.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BoardGameObserver {

  void onGameStarted(BoardGame game);

  /**
   * Called when a player has moved.
   *
   * @param player The player who moved
   * @param steps  The number of steps the player moved
   */
  void onPlayerMoved(Player player, int steps);

  /**
   * Called when the current player changes.
   *
   * @param newPlayer The new current player
   */
  void onNextPlayer(Player newPlayer);

  /**
   * Called when a winner is determined.
   *
   * @param winner The winning player
   */
  void onWinnerDetermined(Player winner);

  /**
   * Called when a Player has lost.
   *
   * @param lostPlayer The Player which has lost.
   */
  void onPlayerLost(Player lostPlayer);

  void onTileActionPerformed(Player player);

  void onEndTurn(Player player);
}