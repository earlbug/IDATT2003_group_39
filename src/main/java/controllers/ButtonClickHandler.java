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
public class ButtonClickHandler implements ButtonClickObserver {

  private final BoardGameHandler boardGameHandler;

  /**
   * Constructs a new handler to handle button clicks.
   *
   * @param boardGameHandler the handler handling boardGame changes.
   * @param gameView  the current gameView of the boardGame.
   */
  public ButtonClickHandler(BoardGameHandler boardGameHandler, GameView gameView) {
    this.boardGameHandler = boardGameHandler;
    boardGameHandler.getNotifier().addObserver(gameView);
  }

  @Override
  public void update(String action) {
    System.out.println("Button was clicked " + action);

    switch (action) {
      case "play":
        int sum = boardGameHandler.handleRollDice();
        boardGameHandler.handlePlayerMove(sum);
        boardGameHandler.handleNextPlayer();
        break;
      default:
        System.out.println("Unknown action" + action);
    }
  }

}