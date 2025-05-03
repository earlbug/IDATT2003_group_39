package models;

import javafx.scene.paint.Color;
import models.validators.ArgumentValidator;

/**
 * Represents the players of the game. Contains a Tile class variable, which represents where the
 * player is standing on the board.
 *
 * @author Erlend Sundsdal
 * @author Tord Fosse
 * @version 0.1.0
 * @since 0.1.0
 */
public class Player {

  private int playerId;
  private String name;
  private Tile currentTile = new Tile(1);
  private final BoardGame game;
  private Color color;
  private int money;
  private int turnsToSkip;

  /**
   * Constructor for Player.
   *
   * @param name the name of the player
   * @param game the game the player is playing
   */
  public Player(String name, BoardGame game) {
    setName(name);
    this.game = game;
    setPlayerId(playerId);

  }

  /**
   * Sets the player ID.
   *
   * @param playerId the ID to set
   */
  public void setPlayerId(int playerId) {
    ArgumentValidator.playerSetId(playerId);
    this.playerId = playerId;
  }

  /**
   * Moves the player an amount of tiles forward.
   *
   * @param steps how many steps the player shall move forward
   */
  public void move(int steps) {
    Tile targetTile = currentTile;
    for (int i = 0; i < steps; i++) {
      if (targetTile.getNextTile() != null) {
        targetTile = targetTile.getNextTile();
      } else {
        break;
      }
    }
    this.currentTile = targetTile;
  }

  /**
   * Places the player on the specified tile.
   *
   * @param tile the tile to place the player on
   */
  public void placeOnTile(Tile tile) {
    setCurrentTile(tile);
  }

  /**
   * Returns the current tile of the player.
   *
   * @return the current tile of the player
   */
  public Tile getCurrentTile() {
    return currentTile;
  }

  /**
   * Returns the name of the player.
   *
   * @return the name of the player
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the amount of money the player currently has.
   *
   * @return the amount of mot\ney the player has.
   */
  public int getMoney() {
    return money;
  }

  /**
   * Gets how many rounds the player has to skip.
   * @return amount of rounds the player has to skip.
   */
  public int getTurnsToSkip() {
    return turnsToSkip;
  }

  /**
   * Sets the name of the player.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    ArgumentValidator.playerSetNameValidator(name);
    this.name = name;
  }

  /**
   * Sets the current tile of the player.
   *
   * @param currentTile the tile to set as current
   */
  public void setCurrentTile(Tile currentTile) {
    ArgumentValidator.playerSetCurrentTileValidator(currentTile);
    this.currentTile = currentTile;
  }

  /**
   * Sets the color of the player.
   *
   * @param color the color to set
   */
  public void setColor(Color color) {
    ArgumentValidator.playerSetColorValidator(color);
    this.color = color;
  }

  /**
   * Returns the color of the player.
   *
   * @return the color of the player
   */
  public Color getColor() {
    return color;
  }

  /**
   * Returns the game the player is playing.
   *
   * @return the game the player is playing
   */
  public int getPlayerId() {
    return playerId;
  }

  private void setMoney(int money) {
    ArgumentValidator.positiveIntValidator(money);
    this.money = money;
  }

  private void setTurnsToSkip(int turnsToSkip) {
    ArgumentValidator.positiveIntValidator(turnsToSkip);
  }

  /**
   * Increases the amount of money the player has. Use a negative number to subtract money.
   *
   * @param amount the amount of money to add to the player.
   */
  public void addMoney(int amount) {
    money += amount;
  }

  /**
   * Adds an amount of turns that the player has to skip.
   *
   * @param amount number of turns to be skipped. Adds to the already amount of turns if there are some.
   */
  public void addTurnsToSkip(int amount) {
    turnsToSkip += amount;
  }

}
