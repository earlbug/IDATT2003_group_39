package controllers.viewController;

import controllers.ButtonClickNotifier;
import controllers.modelController.GameNotifier;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.StackPane;
import models.GamePiece;
import models.Player;

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
  };

  public abstract void showBoardSelectMenu();

  public abstract void showPlayerSelectMenu();

  public abstract void showGameSelectMenu();

  public abstract Map<String, GamePiece> getSelectedPlayers();

  public abstract void addPlayerViews(List<Player> players);

  public abstract void showView();

  public abstract void setButtonClickNotifier(ButtonClickNotifier notifier);

  public abstract void setUpView(String boardFileName, int boardNumber);

  public abstract void setUpView(String boardFileName);

}
