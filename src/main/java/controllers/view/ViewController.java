package controllers.view;

import controllers.ButtonClickNotifier;
import controllers.model.GameNotifier;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.StackPane;
import models.GamePiece;
import models.Player;
import org.slf4j.Logger;

public class ViewController extends GameNotifier {

  private final Logger logger = org.slf4j.LoggerFactory.getLogger(ViewController.class);
  private final StackPane rootPane;

  private ButtonClickNotifier notifier;

  /**
   * Constructor for ViewController.
   */
  public ViewController() {
    this.rootPane = new StackPane();
  }

  public StackPane getRootPane() {
    return rootPane;
  }

  public void showBoardSelectMenu() {
  // Should be handled in the MenuViewController
}

  public void showPlayerSelectMenu() {
    // Should be handled in the MenuViewController
  }

  public Map<String, GamePiece> getSelectedPlayers() {
    // Should be handled in the MenuViewController
    return null;
  }

  public void addPlayerViews(List<Player> players) {
    // Should be handled in the GameViewController
  }

  public void showGameView() {
    // Should be handled in the GameViewController
  }

  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    this.notifier = notifier;
    logger.info("ButtonClickNotifier set in ViewController");
  }

  public ButtonClickNotifier getButtonClickNotifier() {
    return notifier;
  }

  public void setUpView(String boardFileName, int boardNumber) {
    // Should be handled in the GameViewController
  }

  public void setUpView(String boardFileName) {
      // Should be handled in the GameViewController
  }
}
