package models;

import controllers.BoardGame;

public class Player {
  private String name;
  private Tile currentTile;

  Player(String name, BoardGame game){
    setName(name);
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

  public void setName(String name) {
    this.name = name;
  }

  public void setCurrentTile(Tile currentTile) {
    this.currentTile = currentTile;
  }
}
