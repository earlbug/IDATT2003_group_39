package models;

import controllers.BoardGame;

public class Player {
  private String name;
  private Tile currentTile;
  private BoardGame currentGame;

  Player(String name, BoardGame game){
    setName(name);
    setCurrentGame(game);
  }

  public void move(int steps) {
    Tile tileMovedTo = currentTile;
    for (int i = 0; i < steps; i++) {
      tileMovedTo = tileMovedTo.getNextTile();
    }
    placeOnTile(tileMovedTo);
  }

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
