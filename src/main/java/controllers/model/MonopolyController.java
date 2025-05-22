package controllers.model;

import controllers.view.MonopolyViewController;
import controllers.view.ViewManager;
import exception.UnknownGameException;
import factory.BoardFactory;
import interfaces.TileAction;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import models.BoardGame;
import models.GamePiece;
import models.Player;
import models.validators.ArgumentValidator;
import org.slf4j.Logger;

/**
 * MonopolyController extends GameController and is responsible for
 * controlling monopoly-specific game mechanics.
 * It provides methods for checking if a player has lost,
 * and if the winning condition for monopoly is met.
 */
public class MonopolyController extends GameController {

  private BoardGame boardGame;
  private ViewManager viewManager;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(MonopolyController.class);


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

  @Override
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

  @Override
  public void setUpGame() {
    boardGame.setPlayerIds();
    boardGame.addPlayersOnStartPos();
    boardGame.setCurrentPlayer(boardGame.getPlayers().getFirst());
    logger.debug("Game set up with players: {}", boardGame.getPlayers().toArray());
  }

  @Override
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

  @Override
  public void setViewManager(ViewManager viewManager) {
    this.viewManager = viewManager;
    logger.debug("View manager set in GameController");
  }

  @Override
  public void setBoardGame(BoardGame boardGame) {
    ArgumentValidator.controllerSetBoardGame(boardGame);
    this.boardGame = boardGame;
  }

  @Override
  public BoardGame getBoardGame() {
    return boardGame;
  }

  @Override
  public void setBoard(int boardNumber) {
    String boardFileName = "monopoly.json";
    try {
      boardGame = new BoardGame();
      setBoardGame(boardGame);
      boardGame.setBoard(BoardFactory.getFromFile(boardFileName));

      MonopolyViewController viewController = (MonopolyViewController) viewManager.getCurrentViewController();
      viewController.setUpView(boardFileName);
      addObserver(viewController);

      boardGame.createDice(2);

      logger.debug("Board set up: {}", boardFileName);
    } catch (UnknownGameException | IOException e) {
      logger.error("Error setting up game: {}", e.getMessage());
    }
  }

  @Override
  public void handlePlayerMove(int steps) {
    Player currentPlayer = boardGame.getCurrentPlayer();
    currentPlayer.move(steps);
    logger.debug("Player {} moved {} steps", currentPlayer.getName(), steps);

    // Notify observers about the move
    notifyPlayerMoved(currentPlayer, steps);
  }

  @Override
  public void handleOneTurn() {
    int sum = handleRollDice();
    handlePlayerMove(sum);
    handlePlayerTileAction();
    handlePlayerLooseCheck();
    handlePlayerWinCheck();
    handlePlayerEndTurn();
    handleNextPlayer();
  }

  private void handlePlayerTileAction() {
    Player currentPlayer = boardGame.getCurrentPlayer();
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

  private void handlePlayerEndTurn() {
    notifyTurnEnded(boardGame.getCurrentPlayer());
  }

  @Override
  public int handleRollDice() {
    logger.debug("Rolling dice for player {}", boardGame.getCurrentPlayer().getName());
    return boardGame.getDice().rollAllDice();
  }

  @Override
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

  @Override
  public void handlePlayerWinCheck() {
    for (Player player : boardGame.getPlayers()) {
      if (isWinConditionMet(player) && !player.hasLost()) {
        player.setHasWon(true);
        notifyWinnerDetermined(player);
        logger.debug("Player won: {}", player.getName());
      }
    }

  }

}
