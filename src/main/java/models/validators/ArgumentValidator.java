package models.validators;

import controllers.ButtonClickNotifier;
import controllers.model.GameController;
import interfaces.TileAction;
import models.BoardGame;
import models.GamePiece;
import models.Tile;

/**
 * <h3>Argument Validator</h3>
 *
 * <p>A utility class for validating arguments passed to constructors and methods. The class has
 * a private constructor to prevent instantiation.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class ArgumentValidator {

  /**
   * Checks if the given board game is null. If it is, an IllegalArgumentException is thrown.
   *
   * @param boardGame the board game to check
   */
  public static void controllerSetBoardGame(BoardGame boardGame) {
    if (boardGame == null) {
      throw new IllegalArgumentException("Board game cannot be null");
    }
  }

  /**
   * Checks if the given game controller is null. If it is, an IllegalArgumentException is thrown.
   *
   * @param gameController the game controller to check
   */
  public static void managerSetGameController(GameController gameController) {
    if (gameController == null) {
      throw new IllegalArgumentException("GameController cannot be null");
    }
  }

  /**
   * Checks if the given tile is null. If it is, an IllegalArgumentException is thrown.
   *
   * @param tile the tile to check
   */
  public static void playerSetCurrentTileValidator(Tile tile) {
    if (tile == null) {
      throw new IllegalArgumentException("Current tile cannot be null");
    }
  }

  /**
   * Checks if the given player name is empty. If it is, an IllegalArgumentException is thrown.
   *
   * @param name the player name to check
   */
  public static void playerSetNameValidator(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Player name cannot be empty");
    }
  }

  /**
   * Checks if the given tile ID is negative. If it is, an IllegalArgumentException is thrown.
   *
   * @param tileId the tile ID to check
   */
  public static void tileSetTileIdValidator(int tileId) {
    if (tileId < 0) {
      throw new IllegalArgumentException("Tile id cannot be a negative number");
    }
  }

  /**
   * Checks if the given tile action is null. If it is, an IllegalArgumentException is thrown.
   *
   * @param tileAction the tile action to check
   */
  public static void tileSetLandActionValidator(TileAction tileAction) {
    if (tileAction == null) {
      throw new IllegalArgumentException("TileAction cannot be null");
    }
  }

  /**
   * Checks if the given player ID is negative or greater than 3. If it is, an
   * IllegalArgumentException is thrown.
   *
   * @param playerId the player ID to check
   */
  public static void playerSetId(int playerId) {
    if (playerId < 0 || playerId > 3) {
      throw new IllegalArgumentException(
          "Player id cannot be a negative number and must be less than 4");
    }
  }

  /**
   * Checks if the given int is a positive number. If it is not, an IllegalArgumentException is
   * thrown.
   *
   * @param value the int to check
   */
  public static void positiveIntValidator(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Value cannot be a negative number");
    }
  }

  /**
   * Checks if the given game piece is null. If it is, an IllegalArgumentException is thrown.
   *
   * @param gamePiece the game piece to check
   */
  public static void playerSetGamePieceValidator(GamePiece gamePiece) {
    if (gamePiece == null) {
      throw new IllegalArgumentException("Game piece cannot be null");
    }
  }

  /**
   * Checks if the given button click notifier is null. If it is, an IllegalArgumentException is
   * thrown.
   *
   * @param notifier the button click notifier to check
   */
  public static void setButtonClickNotifier(ButtonClickNotifier notifier) {
    if (notifier == null) {
      throw new IllegalArgumentException("ButtonClickNotifier cannot be null");
    }
  }
}
