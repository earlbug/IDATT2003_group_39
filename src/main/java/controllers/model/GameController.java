package controllers.model;

import interfaces.TileAction;
import models.BoardGame;
import models.Player;
import models.actions.WinAction;
import org.slf4j.Logger;

/**
 * Handles game logic operations and delegates notifications.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class GameController extends GameNotifier {

  final BoardGame boardGame;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(GameController.class);

  /**
   * Constructor for BoardGameHandler.
   *
   * @param boardGame The board game to handle
   */
  public GameController(BoardGame boardGame) {
    this.boardGame = boardGame;
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

  public void handlePlayerIds() {
    boardGame.setPlayerIds();
    logger.debug("Player IDs set");
  }

  /**
   * Handles player movement.
   *
   * @param steps Number of steps to move
   */
  public void handlePlayerMove(int steps) {
    Player currentPlayer = boardGame.getCurrentPlayer();
    currentPlayer.move(steps);
    logger.debug("Player {} moved {} steps", currentPlayer.getName(), steps);

    // Notify observers about the move
    notifyPlayerMoved(currentPlayer, steps);

    TileAction tileAction = boardGame.getBoard().getTile(currentPlayer.getCurrentTile().getTileId())
        .getLandAction();

    if (tileAction != null) {
      tileAction.perform(currentPlayer);
      logger.debug("Player {} performed action: {}", currentPlayer.getName(), tileAction);
    } else {
      logger.debug("No action for player {} on tile {}", currentPlayer.getName(),
          currentPlayer.getCurrentTile().getTileId());
    }

    // Check win condition
    if (isWinConditionMet(currentPlayer)) {
      notifyWinnerDetermined(currentPlayer);
    }
  }

  /**
   * Handles changing to next player.
   */
  public void handleNextPlayer() {
    boardGame.nextPlayer();
    notifyNextPlayer(boardGame.getCurrentPlayer());
    logger.debug("Next player: {}", boardGame.getCurrentPlayer().getName());
  }

  /**
   * Checks if win condition is met.
   *
   * @param player Player to check
   * @return True if player has won
   */
  public boolean isWinConditionMet(Player player) {
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

}

