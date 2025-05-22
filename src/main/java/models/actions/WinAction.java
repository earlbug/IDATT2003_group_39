package models.actions;

import interfaces.TileAction;
import models.BoardGame;
import models.Player;

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
