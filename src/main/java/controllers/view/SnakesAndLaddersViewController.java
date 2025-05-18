package controllers.view;

import controllers.ButtonClickNotifier;
import java.util.List;
import javafx.geometry.Pos;
import models.BoardGame;
import models.Player;
import org.slf4j.Logger;
import views.container.GameView;
import views.content.HudView;
import views.content.PlayerView;
import views.content.SnakesAndLaddersBoardView;
import views.content.WinnerView;

/**
 * Handles the view logic for the Snakes and Ladders game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakesAndLaddersViewController extends ViewController{

  private final GameView gameView;
  private final HudView hudView;
  private final SnakesAndLaddersBoardView boardView;
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(SnakesAndLaddersViewController.class);

  /**
   * Constructor for SnakesAndLaddersViewController.
   *
   * @param gameView The game view to handle
   */
  public SnakesAndLaddersViewController(GameView gameView) {
    this.gameView = gameView;
    this.hudView = gameView.getHudView();
    this.boardView = (SnakesAndLaddersBoardView)  gameView.getBoardView();
  }

  public void setNotifiers(ButtonClickNotifier notifier) {
    hudView.setButtonClickNotifier(notifier);
  }

  /**
   * Adds player views to the game view.
   *
   * @param players List of players to add
   */
  public void addPlayerViews(List<Player> players) {
    SnakesAndLaddersBoardView boardView = (SnakesAndLaddersBoardView) gameView.getBoardView();
    for (Player player : players) {
      PlayerView playerView = new PlayerView();
      playerView.setPlayerColor(player.getColor());
      boardView.addPlayerView(player, playerView);
    }
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
    hudView.setDiceNumber(steps);
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
    hudView.setPlayerName(newPlayer.getName());
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
  public void onTileActionPerformed(Player player) {
    boardView.drawPlayerView(player);
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
