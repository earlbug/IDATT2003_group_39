package models.actions;

import interfaces.TileAction;
import models.BoardGame;
import models.Player;
import models.Tile;

public class SnakeAction implements TileAction {

  private int destinationTileId;
  private String description;

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
  }

  @Override
  public void perform(Player player, BoardGame boardGame) {
    Tile destinationTile = boardGame.getBoard().getTile(destinationTileId);
    player.setCurrentTile(destinationTile);
  }
}
