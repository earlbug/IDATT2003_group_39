package models.actions;

import interfaces.TileAction;
import javax.swing.Action;
import models.Player;

public class TaxAction implements TileAction {

  private int moneyDeducted;
  private String description;

  public TaxAction(int moneyDeducted, String description) {
    setMoneyDeducted(moneyDeducted);
    setDescription(description);
  }

  @Override
  public void perform(Player player) {
    player.changeMoney(- moneyDeducted);
  }

  public int getMoneyDeducted() {
    return moneyDeducted;
  }

  public String getDescription() {
    return description;
  }

  public void setMoneyDeducted(int moneyDeducted) {
    this.moneyDeducted = moneyDeducted;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
