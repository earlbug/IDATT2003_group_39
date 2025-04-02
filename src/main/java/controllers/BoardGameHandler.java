package controllers;

import interfaces.TileAction;
import models.Player;
import models.actions.LadderAction;
import models.actions.WinAction;

/**
 * Handles game logic operations and delegates notifications.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class BoardGameHandler {

  private final BoardGame boardGame;
  private final BoardGameNotifier notifier;

  /**
   * Constructor for BoardGameHandler.
   *
   * @param boardGame The board game to handle
   * @param notifier  The notifier to use for observer notifications
   */
  public BoardGameHandler(BoardGame boardGame, BoardGameNotifier notifier) {
    this.boardGame = boardGame;
    this.notifier = notifier;
  }

  /**
   * Handles adding a player to the game.
   *
   * @param player Player to add
   */
  public void handleAddPlayer(Player player) {
    boardGame.addPlayer(player);
  }

  /**
   * Handles player movement.
   *
   * @param steps Number of steps to move
   */
  public void handlePlayerMove(int steps) {
    Player currentplayer = boardGame.getCurrentPlayer();
    currentplayer.move(steps);
    notifier.nofifyPlayerMoved(currentplayer, steps);

    // Check win condition
    if (isWinConditionMet(currentplayer)) {
      notifier.nofityWinnerDetermined(currentplayer);
    }
  }

  /**
   * Handles changing to next player.
   */
  public void handleNextPlayer() {
    boardGame.nextPlayer();
    notifier.notifyNextPlayer(boardGame.getCurrentPlayer());
  }

  /**
   * Checks if win condition is met.
   *
   * @param player Player to check
   * @return True if player has won
   */
  private boolean isWinConditionMet(Player player) {
    TileAction winningAction = boardGame.getBoard().getTile(player.getCurrentTile().getTileId())
        .getLandAction();
    return winningAction instanceof WinAction;
  }

  /**
   * Handles dice rolling.
   *
   * @return The result of the roll
   */
  public int handleRollDice() {
    return boardGame.getDice().rollAllDice();
  }

  public BoardGameNotifier getNotifier() {
    return notifier;
  }

}
