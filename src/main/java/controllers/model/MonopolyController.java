package controllers.model;

import interfaces.TileAction;
import models.BoardGame;
import models.Player;
import models.actions.WinAction;
import org.slf4j.Logger;

/**
 * MonopolyController extends GameController and is responsible for controlling monopoly-specific game mechanics.
 * It provides methods for checking if a player has lost, and if the winning condition for monopoly is met.
 */
public class MonopolyController extends GameController {

  private final Logger logger = org.slf4j.LoggerFactory.getLogger(MonopolyController.class);

  /**
   * Initializes a Monopoly game controller.
   *
   * @param boardGame the BoardGame to be played and controlled.
   */
  public MonopolyController(BoardGame boardGame) {
    super(boardGame);
  }

  /**
   * checks if the current player has lost by being in debt,
   * and if so removes the player form the boardgame.
   */
  @Override
  public void handlePlayerLooseCheck() {
    Player currentPlayer = boardGame.getCurrentPlayer();
    logger.debug("Player {} has a balance of {}$.", currentPlayer.getName(), currentPlayer.getMoney());
    if (boardGame.getCurrentPlayer().getMoney() < 0) {
      boardGame.removePlayer(currentPlayer);
      logger.debug("Player " + currentPlayer.getName() + " has gone bankrupt and has been removed from the game.");
    }
  }

  /**
   * An override of the super class method, to check if monopoly specific conditions are met for a
   * player to be considered victorious.
   * Specific conditions for monopoly is if there are only one player left.
   *
   * @param player Current player
   * @return true if a winning condition is met, and false otherwise
   */
  @Override
  public boolean isWinConditionMet(Player player) {
    logger.debug("Checking win condition for player {} on tile {}", player.getName(),
        player.getCurrentTile().getTileId());
    return boardGame.getPlayers().size() == 1;
  }

}
