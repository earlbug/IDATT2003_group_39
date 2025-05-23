package controllers;

import controllers.modelController.GameManager;
import controllers.modelController.MonopolyController;
import controllers.modelController.SnakesAndLaddersController;
import controllers.viewController.ViewManager;
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

  private final GameManager gameManager;
  private final ViewManager viewManager;

  /**
   * Constructs a new handler to handle button clicks.
   *
   * @param gameManager The controller manager to use
   * @param viewManager    The view manager to use
   */
  public ButtonClickController(GameManager gameManager, ViewManager viewManager) {
    this.viewManager = viewManager;
    this.gameManager = gameManager;
    this.addObserver(this);
  }

  @Override
  public void update(String action) {
    System.out.println("Button was clicked " + action);

    switch (action) {
      case "SnakesAndLadders":
        gameManager.switchToSnakesAndLaddersController();
        viewManager.getCurrentViewController().showBoardSelectMenu();
        break;
      case "Board1":
        viewManager.switchToSnakesAndLaddersView();
        gameManager.getCurrentGameController().setViewManager(viewManager);
        gameManager.getCurrentGameController().setBoard(1);
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "Board2":
        viewManager.switchToSnakesAndLaddersView();
        gameManager.getCurrentGameController().setViewManager(viewManager);
        gameManager.getCurrentGameController().setBoard(2);
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "Board3":
        viewManager.switchToSnakesAndLaddersView();
        gameManager.getCurrentGameController().setViewManager(viewManager);
        gameManager.getCurrentGameController().setBoard(3);
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "Start":
        gameManager.getCurrentGameController().setPlayers(viewManager.getCurrentViewController().getSelectedPlayers());
        if (gameManager.getCurrentGameController() instanceof SnakesAndLaddersController) {
          viewManager.switchToSnakesAndLaddersView();
        } else if (gameManager.getCurrentGameController() instanceof MonopolyController) {
          viewManager.switchToMonopolyView();
        }
        viewManager.getCurrentViewController().showView();
        viewManager.getCurrentViewController().setButtonClickNotifier(this);
        viewManager.getCurrentViewController().addPlayerViews(gameManager.getCurrentGameController().getBoardGame().getPlayers());
        gameManager.getCurrentGameController().setUpGame();
        break;
      case "Monopoly":
        gameManager.switchToMonopolyController();
        viewManager.switchToMonopolyView();
        gameManager.getCurrentGameController().setViewManager(viewManager);
        gameManager.getCurrentGameController().setBoard(0);
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showPlayerSelectMenu();
        break;
      case "Menu":
        viewManager.switchToMenuView();
        viewManager.getCurrentViewController().showGameSelectMenu();
        break;
      case "Play":
        gameManager.getCurrentGameController().handleOneTurn();
        break;
      default:
        throw new UnknownButtonActionException("Unknown button action: " + action);
    }
  }
}
