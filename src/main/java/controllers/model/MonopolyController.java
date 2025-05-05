package controllers.model;

import interfaces.TileAction;
import java.util.ArrayList;
import java.util.List;
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
   * checks if any players has lost by being in debt,
   * and if so removes the player form the boardgame.
   */
  @Override
  public void handleLooseCheck() {
    logger.debug("Checking balance of each Player");

    List<Player> playersToCheck = new ArrayList<>(boardGame.getPlayers());
    for(Player player : playersToCheck) {
      if (player.getMoney() < 0) {
        boardGame.removePlayer(player);
        notifyPlayerLost(player);
        logger.debug("Player " + player.getName() + " has gone bankrupt and has been removed from the game.");
      }
    }
  }

  /**
   * An override of the super class method, to check if monopoly specific conditions are met for a
   * player to be considered victorious.
   * Specific conditions for monopoly is if there are only one player left.
   *
   * @return true if a winning condition is met, and false otherwise
   */
  @Override
  public boolean isWinConditionMet() {
    logger.debug("Checking win condition for Monopoly board");
    return boardGame.getPlayers().size() == 1;
  }

  @Override
  public Player getWinner() {
    return boardGame.getPlayers().getFirst();
  }

}
