package controllers.model;

import models.BoardGame;
import org.slf4j.Logger;

public class SnakesAndLaddersController extends GameController {
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(SnakesAndLaddersController.class);

  /**
   * Constructor for BoardGameHandler.
   *
   * @param boardGame The board game to handle
   */
  public SnakesAndLaddersController(BoardGame boardGame) {
    super(boardGame);
  }



}
