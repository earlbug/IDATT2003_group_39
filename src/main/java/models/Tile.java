package models;

import interfaces.TileAction;

public class Tile {
  private int tileId;
  private Tile nextTile;
  private TileAction landAction = new LadderAction(5, "Test");


  public void landPlayer(Player player) {
    landAction.preform(player);
  }

  public int getTileId() {
    return tileId;
  }

  public Tile getNextTile() {
    return nextTile;
  }
}
