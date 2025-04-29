package controllers;

import controllers.model.GameController;
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
public class ButtonClickController implements ButtonClickObserver {

  private final GameController gameController;

  /**
   * Constructs a new handler to handle button clicks.
   *
   * @param gameController the handler handling boardGame changes.
   * @param gameView       the current gameView of the boardGame.
   */
  public ButtonClickController(GameController gameController, GameView gameView) {
    this.gameController = gameController;
    gameController.addObserver(gameView);
  }

  /**
   * Handles button clicks and performs actions based on the action type.
   *
   * @param action the action to perform
   */
  @Override
  public void update(String action) {
    System.out.println("Button was clicked " + action);

    switch (action) {
      case "play":
        int sum = gameController.handleRollDice();
        gameController.handlePlayerMove(sum);
        gameController.handleNextPlayer();
        break;
      default:
        System.out.println("Unknown action" + action);
    }
  }

}