package controllers;

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

  private final BoardGameController boardGameController;

  /**
   * Constructs a new handler to handle button clicks.
   *
   * @param boardGameController the handler handling boardGame changes.
   * @param gameView  the current gameView of the boardGame.
   */
  public ButtonClickController(BoardGameController boardGameController, GameView gameView) {
    this.boardGameController = boardGameController;
    boardGameController.getNotifier().addObserver(gameView);
  }

  @Override
  public void update(String action) {
    System.out.println("Button was clicked " + action);

    switch (action) {
      case "play":
        int sum = boardGameController.handleRollDice();
        boardGameController.handlePlayerMove(sum);
        boardGameController.handleNextPlayer();
        break;
      default:
        System.out.println("Unknown action" + action);
    }
  }

}