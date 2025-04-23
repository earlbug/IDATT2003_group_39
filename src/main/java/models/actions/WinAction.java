package models.actions;

import interfaces.TileAction;
import models.Player;

public class WinAction implements TileAction {

  @Override
  public void perform(Player player) {
    System.out.println(player.getName() + " has won the game!");
  }
}
