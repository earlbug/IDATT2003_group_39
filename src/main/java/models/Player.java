package models;

import controllers.BoardGame;
import models.validators.ArgumentValidator;

/**
 * Represents the players of the game. Contains a Tile class variable, which represents where the
 * player is standing on the board.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 */
public class Player {
  private String name;
  private Tile currentTile = new Tile(1);
  private final BoardGame game;
  private GamePiece gamePiece;

  public Player(String name, BoardGame game){
    setName(name);
    this.game = game;
  }

  /**
   * Moves the player an amount of tiles forward.
   *
   * @param steps how many steps the player shall move forward
   */
  public void move(int steps) {
    int newTileId = currentTile.getTileId() + steps;
    this.currentTile = game.getBoard().getTile(newTileId);
  }

  /**
   * Places the player on the specified tile.
   *
   * @param tile the tile to place the player on
   */
  public void placeOnTile(Tile tile) {
    setCurrentTile(tile);
  }


  public Tile getCurrentTile() {
    return currentTile;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    ArgumentValidator.playerSetNameValidator(name);
    this.name = name;
  }

  public void setCurrentTile(Tile currentTile) {
    ArgumentValidator.playerSetCurrentTileValidator(currentTile);
    this.currentTile = currentTile;
  }

  public void setGamePiece(GamePiece gamePiece){
    ArgumentValidator.playerSetGamePieceValidator(gamePiece);
    this.gamePiece = gamePiece;
  }

  public GamePiece getGamePiece(){
    return gamePiece;
  }

}
