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
import java.util.Map;
import javafx.geometry.Pos;
import models.BoardGame;
import models.GamePiece;
import models.Player;
import models.validators.ArgumentValidator;
import observers.BoardGameObserver;
import org.slf4j.Logger;
import views.game.container.GameView;
import views.game.content.DiceView;
import views.game.content.PlayerInfoView;
import views.game.content.PlayerView;
import views.game.content.SnakesAndLaddersBoardView;
import views.game.content.WinnerPopup;

/**
 * Handles the view logic for the Snakes and Ladders game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakesAndLaddersViewController extends ViewController implements BoardGameObserver {

  private GameView gameView;
  private PlayerInfoView playerInfoView;
  private DiceView diceView;
  private SnakesAndLaddersBoardView boardView;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(
      SnakesAndLaddersViewController.class);

  /**
   * Constructor for SnakesAndLaddersViewController.
   */
  public SnakesAndLaddersViewController() {
    logger.debug("SnakesAndLaddersViewController initialized");
  }

  @Override
  public void showBoardSelectMenu() {
    // Not used
  }

  @Override
  public void showPlayerSelectMenu() {
    // Not used
  }

  @Override
  public void showGameSelectMenu() {
    // Not used
  }

  @Override
  public Map<String, GamePiece> getSelectedPlayers() {
    return Map.of();
  }


  /**
   * Sets the game view and initializes the dice and player info views.
   *
   * @param gameView The game view to set
   */
  public void setGameView(GameView gameView) {
    this.gameView = gameView;
    this.diceView = gameView.getDiceView();
    this.playerInfoView = gameView.getPlayerInfoView();
    this.boardView = (SnakesAndLaddersBoardView) gameView.getBoardView();
  }

  @Override
  public void setUpView(String boardFileName, int boardNumber) {
    try {
      Board board = BoardFactory.getFromFile(boardFileName);
      BoardView boardView = BoardViewFactory.createBoardView(GameType.SNAKES_AND_LADDERS, board);
      GameView gameView = new GameView(boardView);

      setGameView(gameView);
      boardView.drawBoardImage(boardNumber);
      gameView.setAlignment(Pos.CENTER);
      logger.debug("View set up for board file: {}", boardFileName);
    } catch (UnknownGameException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void setUpView(String boardFileName) {
    // Not used
  }

  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    ArgumentValidator.setButtonClickNotifier(notifier);
    diceView.setButtonClickNotifier(notifier);
  }

  @Override
  public void addPlayerViews(List<Player> players) {
    SnakesAndLaddersBoardView boardView = (SnakesAndLaddersBoardView) gameView.getBoardView();
    for (Player player : players) {
      PlayerView playerView = new PlayerView();
      playerView.setPlayerGamePiece(player.getGamePiece());
      boardView.addPlayerView(player, playerView);
      boardView.drawPlayerView(player);
      logger.debug("Added view for player {}", player.getName());
    }
  }

  @Override
  public void showView() {
    getRootPane().getChildren().clear();
    getRootPane().getChildren().add(gameView);
    logger.debug("SnL view displayed");
  }

  @Override
  public void onGameStarted(BoardGame game) {
    Player player = game.getCurrentPlayer();
    playerInfoView.setPlayerName("Player: " + player.getName());
    playerInfoView.setGamePiece("Piece: " + player.getGamePiece().toString());
    playerInfoView.setPlayerTile("Tile: " + player.getCurrentTile().getTileId());
    diceView.setPlayerName(player.getName());
    logger.debug("Game started with player: {}", player.getName());
  }

  @Override
  public void onPlayerMoved(Player player, int steps) {
    // Update dice display
    diceView.setDiceNumber(steps);
    // Update player position on the board
    boardView.drawPlayerView(player);
    logger.debug("PlayerView updated for {} ", player.getName());
  }

  @Override
  public void onNextPlayer(Player newPlayer) {
    diceView.setPlayerName(newPlayer.getName());
  }

  @Override
  public void onWinnerDetermined(Player winner) {
    diceView.disableRollButton();
    WinnerPopup winnerPopup = new WinnerPopup();
    winnerPopup.show(winner.getName());
    gameView.getChildren().add(winnerPopup);
  }

  @Override
  public void onPlayerLost(Player lostPlayer) {
    // Not used
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
}
