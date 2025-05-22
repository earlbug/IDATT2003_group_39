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
import observers.BoardGameObserver;
import org.slf4j.Logger;
import views.game.container.GameView;
import views.game.content.DiceView;
import views.game.content.MonopolyBoardView;
import views.game.content.PlayerInfoView;
import views.game.content.PlayerView;
import views.game.content.WinnerView;

/**
 * Controller class responsible for managing the visual representation of a Monopoly game.
 * Acts as a bridge between the game model and its visual components, updating the UI
 * in response to game state changes.
 * This class extends ViewController and implements the observer pattern to react to
 * game events such as player movements, player turns, and game completion.
 */
public class MonopolyViewController extends ViewController implements BoardGameObserver {

  private GameView gameView;
  private PlayerInfoView playerInfoView;
  private DiceView diceView;
  private MonopolyBoardView boardView;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(MonopolyViewController.class);

  public MonopolyViewController() {
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
  public Map<String, GamePiece> getSelectedPlayers() {
    return Map.of();
  }

  public void setGameView(GameView gameView) {
    this.gameView = gameView;
    this.diceView = gameView.getDiceView();
    this.playerInfoView = gameView.getPlayerInfoView();
    this.boardView = (MonopolyBoardView) gameView.getBoardView();
    this.gameView.setAlignment(Pos.CENTER);
    this.getRootPane().getChildren().add(gameView);
  }

  @Override
  public void setUpView(String boardFileName) {
    try {
      Board board = BoardFactory.getFromFile(boardFileName);
      BoardView boardView = BoardViewFactory.createBoardView(GameType.MONOPOLY, board);
      GameView gameView = new GameView(boardView);

      setGameView(gameView);
      boardView.drawBoardImage(4);
      logger.debug("View set up for board file {}", boardFileName);
    } catch (UnknownGameException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void showGameView() {
    getRootPane().getChildren().clear();
    getRootPane().getChildren().add(gameView);
    logger.debug("Game view displayed");
  }


  public void setNotifiers(ButtonClickNotifier notifier) {
    diceView.setButtonClickNotifier(notifier);
  }

  public void addPlayerViews(List<Player> players) {
    // MonopolyBoardView boardView = (MonopolyBoardView) gameView.getBoardView();
    for (Player player : players) {
      PlayerView playerView = new PlayerView();
      playerView.setPlayerGamePiece(player.getGamePiece());
      boardView.addPlayerView(player, playerView);
    }
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
    gameView.getChildren().setAll(new WinnerView(winner));
  }

  @Override
  public void onPlayerLost(Player lostPlayer) {
    boardView.drawPlayerView(lostPlayer);
  }

  @Override
  public void onTileActionPerformed(Player player) {

  }

  @Override
  public void onEndTurn(Player player) {
    playerInfoView.setPlayerName("Player: " + player.getName());
    playerInfoView.setGamePiece("Piece: " + player.getGamePiece().toString());
    playerInfoView.setPlayerTile("Tile: " + player.getCurrentTile().getTileId());
    playerInfoView.setMoney("Money: " + player.getMoney());
    playerInfoView.setTurnsToSkip("Turns to skip: " + player.getTurnsToSkip());
    logger.debug("Player {} has ended its turn", player.getName());
  }

  @Override
  public void onGameStateChanged(BoardGame boardGame) {

  }

  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    diceView.setButtonClickNotifier(notifier);
  }

  @Override
  public void setUpView(String boardFileName, int boardNumber) {

  }

}
