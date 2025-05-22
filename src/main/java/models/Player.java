package models;

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
  private GamePiece gamePiece;
  private int money;
  private int turnsToSkip;
  private boolean hasLost;
  private boolean hasWon;

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
    setHasLost(false);
    setHasWon(false);
    setMoney(0);
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
   * Returns the game the player is playing.
   *
   * @return the game the player is playing
   */
  public BoardGame getGame() {
    return game;
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
   *
   * @return amount of rounds the player has to skip.
   */
  public int getTurnsToSkip() {
    return turnsToSkip;
  }

  /**
   * Returns if the player has lost.
   *
   * @return a boolean of if the player has lost.
   */
  public boolean hasLost() {
    return hasLost;
  }

  /**
   * returns true or false depending on if the player has won.
   *
   * @return boolean on if the player has won.
   */
  public boolean hasWon() {
    return hasWon;
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
   * Sets the game piece of the player.
   *
   * @param gamePiece the game piece to set
   */
  public void setGamePiece(GamePiece gamePiece) {
    ArgumentValidator.playerSetGamePieceValidator(gamePiece);
    this.gamePiece = gamePiece;
  }

  /**
   * Returns the game piece of the player.
   *
   * @return the game piece of the player
   */
  public GamePiece getGamePiece() {
    return gamePiece;
  }

  /**
   * Returns the game the player is playing.
   *
   * @return the game the player is playing
   */
  public int getPlayerId() {
    return playerId;
  }

  /**
   * Sets the amount of money the player should have.
   *
   * @param money the amount of money the player should have.
   */
  public void setMoney(int money) {
    ArgumentValidator.positiveIntValidator(money);
    this.money = money;
  }

  /**
   * Sets how many turns the player have to skip.
   *
   * @param turnsToSkip number of turns to skip.
   */
  private void setTurnsToSkip(int turnsToSkip) {
    ArgumentValidator.positiveIntValidator(turnsToSkip);
  }

  /**
   * Sets the has lost state of the Player.
   *
   * @param hasLost The state of lost the Player shall be set to.
   */
  public void setHasLost(boolean hasLost) {
    this.hasLost = hasLost;
  }

  /**
   * Sets if the player has lost or won.
   *
   * @param hasWon the booean of if the player ha won which the player shall be set to.
   */
  public void setHasWon(boolean hasWon) {
    this.hasWon = hasWon;
  }

  /**
   * Increases the amount of money the player has.
   *
   * @param amount the amount of money to increase by.
   */
  public void addMoney(int amount) {
    this.money += amount;
  }

  /**
   * Decrease the amount of money the player has.
   *
   * @param amount the amount of money to decrease by.
   */
  public void deductMoney(int amount) {
    this.money -= amount;
  }

  /**
   * Adds an amount of turns that the player has to skip.
   *
   * @param amount number of turns to be skipped. Adds to the already amount of turns if there are
   *               some.
   */
  public void addTurnsToSkip(int amount) {
    turnsToSkip += amount;
  }

}
