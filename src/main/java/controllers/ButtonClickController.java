package controllers;

import controllers.model.GameController;
import controllers.view.ViewController;
import observers.ButtonClickObserver;
import views.container.GameView;

/**
 * <h3>Handles button clicks</h3>
 *
 * <p>Runs different actions based on type of button pressed by the user.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class ButtonClickController extends ButtonClickNotifier implements ButtonClickObserver {

  private final GameController gameController;

  /**
   * Constructs a new handler to handle button clicks.
   *
   * @param gameController the handler handling boardGame changes.
   * @param viewController       the current gameView of the boardGame.
   */
  public ButtonClickController(GameController gameController, ViewController viewController) {
    this.gameController = gameController;
    gameController.addObserver(viewController);
    this.addObserver(this);
  }

  @Override
  public void update(String action) {
    System.out.println("Button was clicked " + action);

    switch (action) {
      case "play":
        gameController.handleOneTurn();
        break;
      default:
        System.out.println("Unknown action" + action);
    }
  }
}
