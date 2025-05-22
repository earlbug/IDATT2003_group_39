package controllers.model;

import controllers.view.ViewManager;
import java.util.Map;
import models.BoardGame;
import models.GamePiece;
import models.Player;

/**
 * Handles game logic operations and delegates notifications.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class GameController extends GameNotifier {

  /**
   * Sets the board game.
   *
   * @param boardGame The board game to set
   */
  public abstract void setBoardGame(BoardGame boardGame);

  /**
   * Gets the board game.
   *
   * @return The board game
   */
  public abstract BoardGame getBoardGame();

  public abstract void setBoard(int boardNumber);

  /**
   * Handles player movement by moving the player, notifying observers that the player has been
   * moved, and preforming the TileAction, if any.
   *
   * @param steps Number of steps to move
   */
  public abstract void handlePlayerMove(int steps);

  /**
   * Handles the methods needed to take one turn in the game, like moving, and checking if anyone
   * has lost or won, and skipping to next player.
   */
  public abstract void handleOneTurn();

  /**
   * Handles dice rolling.
   *
   * @return The result of the roll
   */
  public abstract int handleRollDice();

  /**
   * Checks if a new player has lost, and notifies observers if so.
   */
  public abstract void handlePlayerLooseCheck();

  /**
   * checks if the loose condition is met.
   *
   * @param player The player to check if the loose condition is met for.
   * @return if lose condition is met.
   */
  public abstract boolean isLooseConditionMet(Player player);

  /**
   * Notifies observers that a player has won, if the winning conditions is met and the player has
   * not already lost. Observers are notified if conditions are met.
   */
  public abstract void handlePlayerWinCheck();

  /**
   * Checks if win condition is met.
   *
   * @param player Player to check
   * @return True if player has won
   */
  public abstract boolean isWinConditionMet(Player player);

  /**
   * Handles changing to next player. If the next Player has lost or have to skip a round, then the
   * player is skipped. If the player had turns to skip, then the amount of turns to skip s reduced
   * by one round.
   */
  public abstract void handleNextPlayer();


  public abstract void setUpGame();

  public abstract void setPlayers(Map<String, GamePiece> players);

  public abstract void setViewManager(ViewManager viewManager);
}

