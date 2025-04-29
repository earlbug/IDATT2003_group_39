package models;

import controllers.BoardGame;
import javax.xml.validation.Validator;
import models.validators.ArgumentValidator;

/**
 * Represents the players of the game. Contains a Tile class variable, which represents where the
 * player is standing on the board.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @author Tord Fosse
 * @since 0.1.0
 */
public class Player {

  private int playerId;
  private String name;
  private Tile currentTile = new Tile(1);
  private final BoardGame game;
  private GamePiece gamePiece;

  public Player(String name, BoardGame game){
    setName(name);
    this.game = game;
    setPlayerId(playerId);
  }

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

  public int getPlayerId() {
    return playerId;
  }

}
