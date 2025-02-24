package models;

import interfaces.TileAction;

public class LadderAction implements TileAction{
  private int destinationTileId;

  LadderAction(int destinationTileId, String description) {
    setDestinationTileId(destinationTileId);
  }

  @Override
  public void preform(Player player) {
    Tile destinationTile = player.getCurrentGame().getBoard().getTile(destinationTileId);
    // todo: implement getCurrentGame(), getBoard() and getTile()
    player.placeOnTile(destinationTile);
  }


  private int getDestinationTileId() {
    return destinationTileId;
  }

  public void setDestinationTileId(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }
}
