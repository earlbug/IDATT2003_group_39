package models;

import interfaces.TileAction;
import models.validators.ArgumentValidator;

/**
 * <h3>Represents a tile</h3>
 *
 * <p>A tile is given by a tileId, the next tile and a TileAction. A TileActions is stored as a
 * class variable, and decides what happens when a player lands on the Tile.
 *
 * @author Erlend Sundsdal
 * @author Tord Fosse
 * @version 0.1.0
 * @since 0.1.0
 */
public class Tile {

  private int tileId;
  private Tile nextTile;
  private TileAction landAction;

  /**
   * Constructs a new Tile.
   *
   * @param tileId the ID of the tile.
   */
  public Tile(int tileId) {
    setTileId(tileId);
  }

  /**
   * Gets the ID from tile given tile.
   *
   * @return the ID of the tile.
   */
  public int getTileId() {
    return tileId;
  }

  /**
   * Gets the next tile after the current tile.
   *
   * @return the next tile.
   */
  public Tile getNextTile() {
    return nextTile;
  }

  /**
   * Sets the ID of a tile.
   *
   * @param tileId the ID given to the tile.
   */
  public void setTileId(int tileId) {
    ArgumentValidator.tileSetTileIdValidator(tileId);
    this.tileId = tileId;
  }

  /**
   * Sets the next tile after the current tile.
   *
   * @param nextTile the next tile.
   */
  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  /**
   * Sets an action on the tile that activates when a player lands on the tile.
   *
   * @param landAction the action of the tile.
   */
  public void setLandAction(TileAction landAction) {
    ArgumentValidator.tileSetLandActionValidator(landAction);
    this.landAction = landAction;
  }

  /**
   * Gets the land action of the tile.
   *
   * @return action when a player lands on the tile.
   */
  public TileAction getLandAction() {
    return landAction;
  }
}
