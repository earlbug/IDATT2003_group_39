package models;

import interfaces.TileAction;

/**
 * When a LadderAction is preformed on a player, the player gets placed on a tile specified
 * in the LadderActions constructor.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 */
public class LadderAction implements TileAction{
  private int destinationTileId;

  public LadderAction(int destinationTileId, String description) {
    setDestinationTileId(destinationTileId);
  }

  /**
   * Moves the player to the destination Tile stored as class variable
   *
   * @param player what player the action shall be preformed on
   */
  @Override
  public void perform(Player player) {
    int currentTileId = player.getCurrentTile().getTileId();
    player.move(destinationTileId - currentTileId);
  }


  private int getDestinationTileId() {
    return destinationTileId;
  }

  public void setDestinationTileId(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }
}
