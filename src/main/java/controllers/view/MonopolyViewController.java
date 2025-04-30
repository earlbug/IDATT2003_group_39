package controllers.view;

import javafx.scene.layout.GridPane;
import models.BoardGame;
import models.Player;
import org.slf4j.Logger;
import views.container.GameView;
import views.content.HudView;
import views.content.MonopolyBoardView;
import views.content.SnakesAndLaddersBoardView;

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
