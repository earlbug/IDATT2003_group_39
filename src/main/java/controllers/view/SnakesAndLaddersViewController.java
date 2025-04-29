package controllers.view;

import java.util.List;
import models.BoardGame;
import models.Player;
import views.container.GameView;
import views.content.PlayerView;
import views.content.SnakesAndLaddersBoardView;
import observers.BoardGameObserver;

/**
 * Handles the view logic for the Snakes and Ladders game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakesAndLaddersViewController implements BoardGameObserver {

  private final GameView gameView;

  /**
   * Constructor for SnakesAndLaddersViewController.
   *
   * @param gameView The game view to handle
   */
  public SnakesAndLaddersViewController(GameView gameView) {
    this.gameView = gameView;
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

  }

  /**
   * Handles the event when a new player is set as the current player.
   *
   * @param newPlayer The new current player
   */
  @Override
  public void onNextPlayer(Player newPlayer) {

  }

  /**
   * Handles the event when a player wins the game.
   *
   * @param winner The winning player
   */
  @Override
  public void onWinnerDetermined(Player winner) {

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
