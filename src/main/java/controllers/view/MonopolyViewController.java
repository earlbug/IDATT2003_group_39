package controllers.view;

import controllers.ButtonClickNotifier;
import java.util.List;
import javafx.scene.layout.GridPane;
import models.BoardGame;
import models.Player;
import org.slf4j.Logger;
import views.container.GameView;
import views.content.HudView;
import views.content.MonopolyBoardView;
import views.content.PlayerView;
import views.content.SnakesAndLaddersBoardView;
import views.content.WinnerView;

/**
 * Controller class responsible for managing the visual representation of a Monopoly game.
 * Acts as a bridge between the game model and its visual components, updating the UI
 * in response to game state changes.
 * This class extends ViewController and implements the observer pattern to react to
 * game events such as player movements, player turns, and game completion.
 */
public class MonopolyViewController extends ViewController {

  private final GameView gameView;
  private final HudView hudView;
  private final MonopolyBoardView boardView;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(MonopolyViewController.class);

  public MonopolyViewController(GameView gameView) {
    this.gameView = gameView;
    this.hudView = gameView.getHudView();
    this.boardView = (MonopolyBoardView)  gameView.getBoardView();
  }


  public void setNotifiers(ButtonClickNotifier notifier) {
    hudView.setButtonClickNotifier(notifier);
  }

  public void addPlayerViews(List<Player> players) {
    MonopolyBoardView boardView = (MonopolyBoardView) gameView.getBoardView();
    for (Player player : players) {
      PlayerView playerView = new PlayerView();
      playerView.setPlayerColor(player.getColor());
      boardView.addPlayerView(player, playerView);
    }
  }


  @Override
  public void onPlayerMoved(Player player, int steps) {
    // Update dice display
    hudView.setDiceNumber(steps);
    // Update player position on the board
    boardView.updatePlayerView(player);
    logger.debug("PlayerView updated for {} ", player);

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
  public void onGameStateChanged(BoardGame boardGame) {

  }

}
