package models;

import controllers.BoardGame;

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
  private Tile currentTile;
  private BoardGame currentGame;

  Player(String name, BoardGame game){
    setName(name);
    setCurrentGame(game);
  }

  /**
   * Moves the player an amount of tiles forward.
   *
   * @param steps how many steps the player shall move forward
   */
  public void move(int steps) {
    Tile tileMovedTo = currentTile;
    for (int i = 0; i < steps; i++) {
      tileMovedTo = tileMovedTo.getNextTile();
    }
    placeOnTile(tileMovedTo);
  }

  /**
   * Places the player on the specified tile.
   *
   * @param tile the tile to place the player on
   */
  public void placeOnTile(Tile tile) {
    setCurrentTile(tile);
  }

  public BoardGame getCurrentGame() {
    return currentGame;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCurrentTile(Tile currentTile) {
    this.currentTile = currentTile;
  }

  public void setCurrentGame(BoardGame currentGame) {
    this.currentGame = currentGame;
  }
}
