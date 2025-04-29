package controllers.view;

import java.util.List;
import models.BoardGame;
import models.Player;
import views.container.GameView;
import views.content.PlayerView;
import views.content.SnakesAndLaddersBoardView;
import observers.BoardGameObserver;

public class SnakesAndLaddersViewController implements BoardGameObserver {

  private final GameView gameView;

  public SnakesAndLaddersViewController(GameView gameView) {
    this.gameView = gameView;
  }

  public void addPlayerViews(List<Player> players) {
    SnakesAndLaddersBoardView boardView = (SnakesAndLaddersBoardView) gameView.getBoardView();
    for (Player player : players) {
      PlayerView playerView = new PlayerView();
      playerView.setPlayerColor(player.getColor());
      boardView.addPlayerView(player, playerView);
    }
  }

  @Override
  public void onPlayerMoved(Player player, int steps) {

  }

  @Override
  public void onNextPlayer(Player newPlayer) {

  }

  @Override
  public void onWinnerDetermined(Player winner) {

  }

  @Override
  public void onGameStateChanged(BoardGame boardGame) {

  }
}
