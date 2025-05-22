package controllers.view;

import controllers.ButtonClickNotifier;
import exception.UnknownGameException;
import factory.BoardFactory;
import factory.BoardViewFactory;
import factory.GameType;
import interfaces.Board;
import interfaces.BoardView;
import java.io.IOException;
import java.util.List;
import models.BoardGame;
import models.Player;
import observers.BoardGameObserver;
import org.slf4j.Logger;
import views.game.container.GameView;
import views.game.content.DiceView;
import views.game.content.PlayerView;
import views.game.content.SnakesAndLaddersBoardView;
import views.game.content.WinnerView;

/**
 * Handles the view logic for the Snakes and Ladders game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakesAndLaddersViewController extends ViewController implements BoardGameObserver {

  private GameView gameView;
  private DiceView diceView;
  private SnakesAndLaddersBoardView boardView;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(SnakesAndLaddersViewController.class);

  /**
   * Constructor for SnakesAndLaddersViewController.
   *
   */
  public SnakesAndLaddersViewController() {
    logger.debug("SnakesAndLaddersViewController initialized");
  }

  public void setGameView(GameView gameView) {
    this.gameView = gameView;
    this.diceView = gameView.getDiceView();
    this.boardView = (SnakesAndLaddersBoardView) gameView.getBoardView();
  }

  /**
   * Sets up the game view and board view based on the board file.
   *
   * @param boardFileName The name of the board file
   */
  @Override
  public void setUpView(String boardFileName, int boardNumber) {
    try {
      Board board = BoardFactory.getFromFile(boardFileName);
      BoardView boardView = BoardViewFactory.createBoardView(GameType.SNAKES_AND_LADDERS, board);
      GameView gameView = new GameView(boardView);

      setGameView(gameView);
      boardView.drawBoardImage(boardNumber);
      logger.debug("View set up for board file: {}", boardFileName);
    } catch (UnknownGameException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    super.setButtonClickNotifier(notifier);
    diceView.setButtonClickNotifier(notifier);
  }

  /**
   * Adds player views to the game view.
   *
   * @param players List of players to add
   */
  @Override
  public void addPlayerViews(List<Player> players) {
    SnakesAndLaddersBoardView boardView = (SnakesAndLaddersBoardView) gameView.getBoardView();
    for (Player player : players) {
      PlayerView playerView = new PlayerView();
      playerView.setPlayerGamePiece(player.getGamePiece());
      boardView.addPlayerView(player, playerView);
      boardView.drawPlayerView(player);
      logger.debug("Added view for player {}", player);
    }
  }

  @Override
  public void showGameView() {
    getRootPane().getChildren().clear();
    getRootPane().getChildren().add(gameView);
    logger.debug("Game view displayed");
  }

  /**
   * Handles the event when a player moves.
   *
   * @param player The player who moved
   * @param steps  The number of steps moved
   */
  @Override
  public void onPlayerMoved(Player player, int steps) {
    // Update dice display
    diceView.setDiceNumber(steps);
    // Update player position on the board
    boardView.drawPlayerView(player);
    logger.debug("PlayerView updated for {} ", player);
  }

  /**
   * Handles the event when a new player is set as the current player.
   *
   * @param newPlayer The new current player
   */
  @Override
  public void onNextPlayer(Player newPlayer) {
    diceView.setPlayerName(newPlayer.getName());
  }

  /**
   * Handles the event when a player wins the game.
   *
   * @param winner The winning player
   */
  @Override
  public void onWinnerDetermined(Player winner) {
    gameView.getChildren().setAll(new WinnerView(winner));
  }

  @Override
  public void onPlayerLost(Player lostPlayer) {

  }

  @Override
  public void onTileActionPerformed(Player player) {
    boardView.drawPlayerView(player);
  }

  @Override
  public void onEndTurn(Player player) {
    playerInfoView.setPlayerName("Player: " + player.getName());
    playerInfoView.setGamePiece("Piece: " + player.getGamePiece().toString());
    playerInfoView.setPlayerTile("Tile: " + player.getCurrentTile().getTileId());
    logger.debug("Player {} has ended its turn", player.getName());
  }

  /**
   * Handles the event when the game state changes.
   *
   * @param boardGame The current state of the board game
   */
  @Override
  public void onGameStateChanged(BoardGame boardGame) {
  }
}
