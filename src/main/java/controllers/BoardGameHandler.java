package controllers;

import interfaces.TileAction;
import models.Player;
import models.actions.LadderAction;
import models.actions.WinAction;
import org.slf4j.Logger;

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
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(BoardGameHandler.class);

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
    logger.debug("Player added: {}", player.getName());
  }

  /**
   * Handles player movement.
   *
   * @param steps Number of steps to move
   */
  public void handlePlayerMove(int steps) {
    Player currentplayer = boardGame.getCurrentPlayer();
    currentplayer.move(steps);
    logger.debug("Player {} moved {} steps", currentplayer.getName(), steps);
    notifier.nofifyPlayerMoved(currentplayer, steps);

    TileAction tileAction = boardGame.getBoard().getTile(currentplayer.getCurrentTile().getTileId())
        .getLandAction();

    if (tileAction != null) {
      tileAction.perform(currentplayer, notifier);
      logger.debug("Player {} performed action: {}", currentplayer.getName(), tileAction);
    } else {
      logger.debug("No action for player {} on tile {}", currentplayer.getName(),
          currentplayer.getCurrentTile().getTileId());
    }

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
    logger.debug("Next player: {}", boardGame.getCurrentPlayer().getName());
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
    logger.debug("Checking win condition for player {} on tile {}",
        player.getName(), player.getCurrentTile().getTileId());
    return winningAction instanceof WinAction;
  }

  /**
   * Handles dice rolling.
   *
   * @return The result of the roll
   */
  public int handleRollDice() {
    logger.debug("Rolling dice for player {}", boardGame.getCurrentPlayer().getName());
    return boardGame.getDice().rollAllDice();
  }

  public BoardGameNotifier getNotifier() {
    return notifier;
  }

}
