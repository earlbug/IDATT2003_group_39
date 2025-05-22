package controllers.view;

import controllers.ButtonClickNotifier;
import controllers.model.GameNotifier;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.StackPane;
import models.GamePiece;
import models.Player;
import org.slf4j.Logger;

public abstract class ViewController extends GameNotifier {

  private final StackPane rootPane;

  /**
   * Constructor for ViewController.
   */
  public ViewController() {
    this.rootPane = new StackPane();
  }

  public StackPane getRootPane() {
    return rootPane;
  }

  public abstract void showBoardSelectMenu();

  public abstract void showPlayerSelectMenu();

  public abstract Map<String, GamePiece> getSelectedPlayers();

  public abstract void addPlayerViews(List<Player> players);

  public abstract void showGameView();

  public abstract void setButtonClickNotifier(ButtonClickNotifier notifier);

  public abstract void setUpView(String boardFileName, int boardNumber);

  public abstract void  setUpView(String boardFileName);
}
