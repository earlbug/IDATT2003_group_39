package controllers.view;

import controllers.ButtonClickNotifier;
import java.util.List;
import models.BoardGame;
import models.Player;
import observers.BoardGameObserver;
import org.slf4j.Logger;
import views.game.container.GameView;
import views.game.content.HudView;
import views.game.content.MonopolyBoardView;
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
  private HudView hudView;
  private MonopolyBoardView boardView;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(MonopolyViewController.class);

  public MonopolyViewController() {
  }

  public void setGameView(GameView gameView) {
    this.gameView = gameView;
    this.hudView = gameView.getHudView();
    this.boardView = (MonopolyBoardView) gameView.getBoardView();
    this.getRootPane().getChildren().add(gameView);
  }


  public void setNotifiers(ButtonClickNotifier notifier) {
    hudView.setButtonClickNotifier(notifier);
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
    hudView.setDiceNumber(steps);
    // Update player position on the board
    boardView.drawPlayerView(player);
    logger.debug("PlayerView updated for {} ", player.getName());

  }

  @Override
  public void onNextPlayer(Player newPlayer) {
    hudView.setPlayerName(newPlayer.getName());

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
  public void onGameStateChanged(BoardGame boardGame) {

  }

  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    super.setButtonClickNotifier(notifier);
    hudView.setButtonClickNotifier(notifier);
  }

}
