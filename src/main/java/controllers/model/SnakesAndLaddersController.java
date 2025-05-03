package controllers.model;

import models.BoardGame;
import org.slf4j.Logger;

/**
 * Handles game logic operations and delegates notifications related to the Snakes and ladders game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
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
