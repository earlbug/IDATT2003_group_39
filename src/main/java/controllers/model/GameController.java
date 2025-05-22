package controllers.model;

import controllers.view.SnakesAndLaddersViewController;
import controllers.view.ViewManager;
import exception.UnknownGameException;
import factory.BoardFactory;
import interfaces.TileAction;
import java.io.IOException;
import java.util.Map;
import models.BoardGame;
import models.GamePiece;
import models.Player;
import models.boards.MonopolyBoard;
import models.boards.SnakesAndLaddersBoard;
import org.slf4j.Logger;

/**
 * Handles game logic operations and delegates notifications.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class GameController extends GameNotifier {

  BoardGame boardGame;
  private ViewManager viewManager;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(GameController.class);

  /**
   * Constructor for BoardGameHandler.
   */
  public GameController() {
  }

  /**
   * Sets the board game.
   *
   * @param boardGame The board game to set
   */
  public void setBoardGame(BoardGame boardGame) {
    this.boardGame = boardGame;
  }

  /**
   * Gets the board game.
   *
   * @return The board game
   */
  public BoardGame getBoardGame() {
    return boardGame;
  }

  public void setBoard(int boardNumber) {
    String boardFileName = switch (boardNumber) {
      case 1 -> "SnL1.json";
      case 2 -> "SnL2.json";
      case 3 -> "SnL3.json";
      default -> throw new IllegalArgumentException("Invalid board number: " + boardNumber);
    };

    try {
      boardGame = new BoardGame();
      setBoardGame(boardGame);
      boardGame.setBoard(BoardFactory.getFromFile(boardFileName));

      SnakesAndLaddersViewController viewController = (SnakesAndLaddersViewController) viewManager.getCurrentViewController();
      viewController.setUpView(boardFileName, boardNumber);
      addObserver(viewController);

      boardGame.createDice(1);

      logger.debug("Board set up: {}", boardFileName);
    } catch (UnknownGameException | IOException e) {
      logger.error("Error setting up game: {}", e.getMessage());
    }
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
   * Handles player movement by moving the player, notifying observers that the player has been
   * moved, and preforming the TileAction, if any.
   *
   * @param steps Number of steps to move
   */
  public void handlePlayerMove(int steps) {
    Player currentPlayer = boardGame.getCurrentPlayer();
    currentPlayer.move(steps);
    logger.debug("Player {} moved {} steps", currentPlayer.getName(), steps);

    // Notify observers about the move
    notifyPlayerMoved(currentPlayer, steps);

    TileAction tileAction = currentPlayer.getCurrentTile().getLandAction();
    if (tileAction != null) {
      tileAction.perform(currentPlayer);
      logger.debug("Player {} performed action: {}", currentPlayer.getName(), tileAction);
    } else {
      logger.debug("No action for player {} on tile {}", currentPlayer.getName(),
          currentPlayer.getCurrentTile().getTileId());
    }
    notifyTileActionPerformed(currentPlayer);
  }

  /**
   * Handles the methods needed to take one turn in the game, like moving, and checking if anyone
   * has lost or won, and skipping to next player.
   */
  public void handleOneTurn() {
    int sum = handleRollDice();
    handlePlayerMove(sum);
    handlePlayerLooseCheck();
    handlePlayerWinCheck();
    handleNextPlayer();
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

  /**
   * Checks if a new player has lost, and notifies observers if so.
   */
  public void handlePlayerLooseCheck() {
    for (Player player : boardGame.getPlayers()) {
      if (isLooseConditionMet(player) && !player.hasLost()) {
        player.setHasLost(true);
        notifyPlayerLost(player);
        logger.debug("Player lost: {}", player.getName());
      }
    }
  }

  /**
   * checks if the loose condition is met.
   *
   * @param player The player to check if the loose condition is met for.
   * @return if lose condition is met.
   */
  public boolean isLooseConditionMet(Player player) {
    return false;
  }

  /**
   * Notifies observers that a player has won, if the winning conditions is met and the player has
   * not already lost. Observers are notified if conditions are met.
   */
  public void handlePlayerWinCheck() {
    for (Player player : boardGame.getPlayers()) {
      if (isWinConditionMet(player) && !player.hasLost()) {
        player.setHasWon(true);
        notifyWinnerDetermined(player);
        logger.debug("Player won: {}", player.getName());
      }
    }
  }

  /**
   * Checks if win condition is met.
   *
   * @param player Player to check
   * @return True if player has won
   */
  public boolean isWinConditionMet(Player player) {
    return player.hasWon();
  }

  /**
   * Handles changing to next player. If the next Player has lost or have to skip a round, then the
   * player is skipped. If the player had turns to skip, then the amount of turns to skip s reduced
   * by one round.
   */
  public void handleNextPlayer() {
    boardGame.nextPlayer();
    Player currentPlayer = boardGame.getCurrentPlayer();
    while (currentPlayer.hasLost() || currentPlayer.getTurnsToSkip() > 0) {
      if (currentPlayer.getTurnsToSkip() > 0) {
        currentPlayer.addTurnsToSkip(-1);
      }
      boardGame.nextPlayer();
      currentPlayer = boardGame.getCurrentPlayer();
    }

    notifyNextPlayer(currentPlayer);
    logger.debug("Next player: {}", currentPlayer.getName());
  }


  public void setUpGame() {
    boardGame.setPlayerIds();
    boardGame.addPlayersOnStartPos();
    boardGame.setCurrentPlayer(boardGame.getPlayers().getFirst());
    logger.debug("Game set up with players: {}", boardGame.getPlayers().toArray());
  }

  public void setPlayers(Map<String, GamePiece> players) {
    for (Map.Entry<String, GamePiece> entry : players.entrySet()) {
      String playerName = entry.getKey();
      GamePiece gamePiece = entry.getValue();
      Player player = new Player(playerName, boardGame);
      player.setGamePiece(gamePiece);
      boardGame.addPlayer(player);
      logger.debug("Player {} set up with game piece {}", playerName, gamePiece);
    }
  }

  public boolean isSnakesAndLaddersGame() {
    return boardGame.getBoard() instanceof SnakesAndLaddersBoard;
  }

  public boolean isMonopolyGame() {
    return boardGame.getBoard() instanceof MonopolyBoard;
  }

  public void setViewManager(ViewManager viewManager) {
    this.viewManager = viewManager;
    logger.debug("View manager set in GameController");
  }
}

