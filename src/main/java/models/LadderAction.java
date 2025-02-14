package models;

import interfaces.TileAction;

public class LadderAction implements TileAction{
  private int destinationTileId;

  LadderAction(int destinationTileId, String description) {
    setDestinationTileId(destinationTileId);
  }

  public void preform(Player player) {}



  private int getDestinationTileId() {
    return destinationTileId;
  }

  public void setDestinationTileId(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }
}
