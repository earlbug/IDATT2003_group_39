package controllers.model;

import java.util.List;
import models.BoardGame;
import models.Player;
import org.slf4j.Logger;

/**
 * MonopolyController extends GameController and is responsible for
 * controlling monopoly-specific game mechanics.
 * It provides methods for checking if a player has lost,
 * and if the winning condition for monopoly is met.
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
   * An override of the super class method, to check if monopoly specific conditions are met for a
   * player to be considered victorious.
   * Specific conditions for monopoly is if there are only one player left,
   * and it is the provided Player.
   *
   * @param player Current player
   * @return true if a winning condition is met, and false otherwise
   */
  @Override
  public boolean isWinConditionMet(Player player) {
    List<Player> playersLeft = boardGame.getPlayers().stream()
        .filter(p -> !p.hasLost())
        .toList();
    return playersLeft.size() == 1 && playersLeft.getFirst().equals(player);
  }

  /**
   * An override of the super class method, to check if monopoly specific conditions are met for a
   * player to be considered a looser.
   * Specific conditions for monopoly is if The player is in debt.
   *
   * @param player The player to check if the loose condition is met for.
   * @return if the player is in debt
   */
  @Override
  public boolean isLooseConditionMet(Player player) {
    return player.getMoney() < 0;
  }

}
