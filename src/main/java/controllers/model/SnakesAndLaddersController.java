package controllers.model;

import interfaces.TileAction;
import java.util.List;
import models.BoardGame;
import models.Player;
import models.actions.WinAction;
import org.slf4j.Logger;

/**
 * Handles game logic operations and delegates notifications related to the Snakes and ladders game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakesAndLaddersController extends GameController {

  private final Logger logger = org.slf4j.LoggerFactory.getLogger(SnakesAndLaddersController.class);

  /**
   * Constructor for BoardGameHandler.
   *
   * @param boardGame The board game to handle
   */
  public SnakesAndLaddersController(BoardGame boardGame) {
    super(boardGame);
  }


  /**
   * An override of the super class method, to check if snakes and ladders
   * specific conditions are met for a player to be considered a looser.
   * Specific conditions for snakes and ladders are always false.
   *
   * @param player The player to check if the loose condition is met for.
   * @return if the player fill the requirements for loosing
   */
  @Override
  public boolean isLooseConditionMet(Player player) {
    return false;
  }

  /**
   * An override of the super class method, to check if snakes and ladders
   * specific conditions are met for a player to be considered victorious.
   * Specific conditions for snakes and ladders is if the player is standing on a winning Tile
   *
   * @param player Current player
   * @return true if a winning condition is met, and false otherwise
   */
  @Override
  public boolean isWinConditionMet(Player player) {
    TileAction winningAction = boardGame.getBoard().getTile(player.getCurrentTile().getTileId())
        .getLandAction();
    return winningAction instanceof WinAction;
  }
}
