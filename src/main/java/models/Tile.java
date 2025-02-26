package models;

import interfaces.TileAction;

/**
 * Represents a tile in the ladder game. A TileActions is stored as a class variable, and decides
 * what happens when a player lands on the Tile.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 */
public class Tile {
  private int tileId;
  private Tile nextTile;
  private TileAction landAction;

  public Tile(int tileId) {
    setTileId(tileId);
  }

  /**
   * Preforms the stored <code>TileAction</code> on the player parameter
   *
   * @param player the player whom the action shall be preformed on
   */
  public void landPlayer(Player player) {
    landAction.preform(player);
  }

  public void leavePlayer(Player player) {}


  public int getTileId() {
    return tileId;
  }



  public Tile getNextTile() {
    return nextTile;
  }

  public void setTileId(int tileId) {
    this.tileId = tileId;
  }

  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  public void setLandAction(TileAction landAction) {
    this.landAction = landAction;
  }
}
