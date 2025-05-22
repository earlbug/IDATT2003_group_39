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
import models.actions.WinAction;
import models.validators.ArgumentValidator;
import org.slf4j.Logger;

/**
 * Handles game logic operations and delegates notifications related to the Snakes and ladders
 * game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakesAndLaddersController extends GameController {

  private BoardGame boardGame;
  private ViewManager viewManager;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(SnakesAndLaddersController.class);


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
  handlePlayerLandAction();
  handlePlayerLooseCheck();
  handlePlayerWinCheck();
  handlePlayerTurnEnd();
  handleNextPlayer();
}

private void handlePlayerLandAction() {
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

private void handlePlayerTurnEnd() {
  notifyTurnEnded(boardGame.getCurrentPlayer());
}

@Override
public int handleRollDice() {
  logger.debug("Rolling dice for player {}", boardGame.getCurrentPlayer().getName());
  return boardGame.getDice().rollAllDice();
}

@Override
public void handlePlayerLooseCheck() {
// Not used
}

@Override
public boolean isLooseConditionMet(Player player) {
  // Not used
  return false;
}

@Override
public void handlePlayerWinCheck() {
  if (isWinConditionMet(boardGame.getCurrentPlayer())) {
    notifyWinnerDetermined(boardGame.getCurrentPlayer());
    logger.debug("Player {} has won!", boardGame.getCurrentPlayer());
  }
}

@Override
public boolean isWinConditionMet(Player player) {
  TileAction winningAction = boardGame.getBoard().getTile(player.getCurrentTile().getTileId())
      .getLandAction();
  return winningAction instanceof WinAction;
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
  }
