package controllers;

import controllers.model.GameController;
import controllers.view.ViewManager;
import exception.UnknownButtonActionException;
import observers.ButtonClickObserver;

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
  private final ViewManager viewManager;

  /**
   * Constructs a new handler to handle button clicks.
   *
   * @param gameController The game controller to use
   * @param viewManager    The view manager to use
   */
  public ButtonClickController(GameController gameController, ViewManager viewManager) {
    this.viewManager = viewManager;
    this.gameController = gameController;
    gameController.setViewManager(viewManager);
    this.addObserver(this);
  }

  @Override
  public void update(String action) {
    System.out.println("Button was clicked " + action);

    switch (action) {
      case "SnakesAndLadders":
        viewManager.getCurrentViewController().showBoardSelectMenu();
        break;
      case "Board1":
        viewManager.switchToSnakesAndLaddersView();
        gameController.setBoard(1);
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "Board2":
        viewManager.switchToSnakesAndLaddersView();
        gameController.setBoard(2);
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "Board3":
        viewManager.switchToSnakesAndLaddersView();
        gameController.setBoard(3);
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "Start":
        gameController.setPlayers(viewManager.getCurrentViewController().getSelectedPlayers());
        if (gameController.isSnakesAndLaddersGame()) {
          viewManager.switchToSnakesAndLaddersView();
        } else if (gameController.isMonopolyGame()) {
          viewManager.switchToMonopolyView();
        }
        viewManager.getCurrentViewController().showGameView();
        viewManager.getCurrentViewController().setButtonClickNotifier(this);
        gameController.setUpGame();
        viewManager.getCurrentViewController().addPlayerViews(gameController.getBoardGame().getPlayers());
        break;
      case "Monopoly":
        viewManager.switchToMonopolyView();
        gameController.setMonopolyBoard();
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "play":
        gameController.handleOneTurn();
        break;
      default:
        throw new UnknownButtonActionException("Unknown button action: " + action);
    }
  }
}
