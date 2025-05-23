package models.actions;

import interfaces.TileAction;
import models.Player;

/**
 * A TileAction implementation, which when performed sets the player to have won the game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class WinAction implements TileAction {
  private String description;

  @Override
  public void perform(Player player) {
    player.setHasWon(true);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
