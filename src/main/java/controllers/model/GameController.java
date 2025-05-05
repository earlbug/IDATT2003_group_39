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

  /**
   * Handles setting player IDs.
   */
  public void handlePlayerIds() {
    boardGame.setPlayerIds();
    logger.debug("Player IDs set");
  }

  /**
   * Handles the methods needed to take one turn in the game, like moving, and checking if anyone has
   * lost or won, and skipping to next player.
   */
  public void handleOneTurn() {
    int sum = handleRollDice();
    handlePlayerMove(sum);
    handleLooseCheck();
    handleWinCheck();
    handleNextPlayer();
  }

  /**
   * Handles player movement by moving the player,
   * notifying observers that the player has been moved,
   * and preforming the TileAction, if any.
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
  }

  /**
   * Notifies observers that a player has won, if the winning conditions is met.
   */
  public void handleWinCheck() {
    Player currentPlayer = boardGame.getCurrentPlayer();
    if (isWinConditionMet()) {
      notifyWinnerDetermined(getWinner());
    }
  }

  /**
   * checks if any players has lost.
   */
  public void handleLooseCheck() {

  }

  /**
   * Handles changing to next player.
   */
  public void handleNextPlayer() {
    logger.debug("Current player: {}", boardGame.getCurrentPlayer().getName());
    boardGame.nextPlayer();
    notifyNextPlayer(boardGame.getCurrentPlayer());
    logger.debug("Next player: {}", boardGame.getCurrentPlayer().getName());
  }

  /**
   * Checks if win condition is met by checking if any players are standing on a winning Tile.
   *
   * @return True if player has won
   */
  public boolean isWinConditionMet() {
    boolean isWinConditionMet = false;
    logger.debug("Checking win condition for every player.");
    for(Player player : boardGame.getPlayers()) {
      TileAction winningAction = boardGame.getBoard().getTile(player.getCurrentTile().getTileId())
          .getLandAction();
      if (winningAction instanceof WinAction) {
        isWinConditionMet = true;
      }
    }
    return isWinConditionMet ;
  }

  public Player getWinner() {
    Player victoriousPlayer = null;
    for(Player player : boardGame.getPlayers()) {
      TileAction winningAction = boardGame.getBoard().getTile(player.getCurrentTile().getTileId())
          .getLandAction();
      if (winningAction instanceof WinAction) {
        logger.debug("Player won: {}", player.getName());
        victoriousPlayer = player;
      }
    }
    return victoriousPlayer;
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

