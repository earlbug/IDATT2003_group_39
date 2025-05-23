package controllers.model;

import models.validators.ArgumentValidator;

/**
 * Manager for the different game controllers.
 *
 * @author Tord Fosse
 * @since 1.0.0
 */
public class GameManager {

  private final GameController snakesAndLaddersController;
  private final GameController monopolyController;

  private GameController currentGameController;

  /**
   * Constructs a new GameManager.
   */
  public GameManager() {
    this.snakesAndLaddersController = new SnakesAndLaddersController();
    this.monopolyController = new MonopolyController();
  }

  /**
   * Gets the current game controller.
   *
   * @return The current game controller
   */
  public GameController getCurrentGameController() {
    return currentGameController;
  }

  /**
   * Switches to the specified game controller.
   *
   * @param gameController The game controller to switch to
   */
  private void switchToController(GameController gameController) {
    ArgumentValidator.managerSetGameController(gameController);
    currentGameController = gameController;
  }

  /**
   * Switches to the Snakes and Ladders controller.
   */
  public void switchToSnakesAndLaddersController() {
    switchToController(snakesAndLaddersController);
  }

  /**
   * Switches to the Monopoly controller.
   */
  public void switchToMonopolyController() {
    switchToController(monopolyController);
  }
}
