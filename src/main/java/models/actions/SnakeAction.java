package models.actions;

import interfaces.TileAction;
import models.Player;
import models.Tile;

/**
 * When a SnakeAction is performed on a player, the player gets placed on a tile specified
 * in the SnakeActions constructor.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakeAction implements TileAction {

  private int destinationTileId;
  private String description;

  /**
   * Constructor sets the destination tile and a default description.
   *
   * @param destinationTileId the tile the player shall be moved to.
   */
  public SnakeAction(int destinationTileId) {
    setDestinationTileId(destinationTileId);
    setDescription("Player gets moved backwards on the board.");
  }


  public void setDestinationTileId(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void perform(Player player) {
    Tile destinationTile = player.getGame().getBoard().getTile(destinationTileId);
    player.setCurrentTile(destinationTile);
  }
}
