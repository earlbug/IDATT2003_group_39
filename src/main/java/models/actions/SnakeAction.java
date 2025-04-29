package models.actions;

import interfaces.TileAction;
import models.Player;

public class SnakeAction implements TileAction {

  private int destinationTileId;
  private String description;

  public SnakeAction(int destinationTileId, String description) {
    setDestinationTileId(destinationTileId);
    setDescription(description);
  }


  public void setDestinationTileId(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void perform(Player player) {

  }
}
