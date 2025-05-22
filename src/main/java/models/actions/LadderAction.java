package models.actions;

import interfaces.TileAction;
import models.BoardGame;
import models.Player;
import models.Tile;

/**
 * When a LadderAction is preformed on a player, the player gets placed on a tile specified
 * in the LadderActions constructor.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 */
public class LadderAction implements TileAction {
  private int destinationTileId;
  private String description;

  /**
   * Constructor sets the destination tile and a default description.
   *
   * @param destinationTileId the tile the player shall be moved to.
   */
  public LadderAction(int destinationTileId)  {
    setDestinationTileId(destinationTileId);
    setDescription("Player gets moved forward on the board.");
  }

  /**
   * Sets the player on a specified tile.
   *
   * @param player the player who landed on the tile
   */
  @Override
  public void perform(Player player) {
    Tile destinationTile = player.getGame().getBoard().getTile(destinationTileId);
    player.setCurrentTile(destinationTile);
  }

  /**
   * Sets the destination the player gets moved to when the action is preformed.
   *
   * @param destinationTileId the id of the destination tile.
   */
  public void setDestinationTileId(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }

  /**
   * Sets the description for the LadderAction.
   *
   * @param description the description of the action
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the description of the LadderAction.
   *
   * @return the description of the action
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the destination tile ID where the player will be moved.
   *
   * @return the destination tile ID
   */
  public int getDestinationTileId() {
    return destinationTileId;
  }
}
