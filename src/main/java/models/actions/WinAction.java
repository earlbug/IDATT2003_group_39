package models.actions;

import interfaces.TileAction;
import models.Player;

public class WinAction implements TileAction {
  private String description;

  @Override
  public void perform(Player player) {
    player.setHasWon(true);
    setDescription("Player wins.");
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
