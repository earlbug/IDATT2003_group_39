package controllers.model;

import models.validators.ArgumentValidator;

public class GameManager {

  private final GameController snakesAndLaddersController;
  private final GameController monopolyController;

  private GameController currentGameController;

  public GameManager() {
    this.snakesAndLaddersController = new SnakesAndLaddersController();
    this.monopolyController = new MonopolyController();
  }

  public GameController getCurrentGameController() {
    return currentGameController;
  }

  private void switchToController(GameController gameController) {
    ArgumentValidator.managerSetGameController(gameController);
    currentGameController = gameController;
  }

  public void switchToSnakesAndLaddersController() {
    switchToController(snakesAndLaddersController);
  }

  public void switchToMonopolyController() {
    switchToController(monopolyController);
  }
}
