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

  LadderAction(int destinationTileId, String description) {
    setDestinationTileId(destinationTileId);
  }

  /**
   * Puts Player on the Tile corresponding to the tile ID specified in the constructor
   *
   * @param player what player the action shall be preformed on
   */
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
