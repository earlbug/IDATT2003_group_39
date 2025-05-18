package models.actions;

import interfaces.TileAction;
import javax.swing.Action;
import models.Player;

/**
 * A TileAction implementation, which when preforming deducts an amount of money form the player
 * which lands on the tile with this TileAction.
 */
public class TaxAction implements TileAction {

  private int moneyDeducted;
  private String description;

  /**
   * Constructor which sets how much money shall be deducted and a description.
   *
   * @param moneyDeducted How much money to deduct from the player.
   */
  public TaxAction(int moneyDeducted) {
    setMoneyDeducted(moneyDeducted);
    setDescription("Player looses money.");
  }

  /**
   * Deducts an amount of money from the player provided.
   *
   * @param player The player who landed on the tile, and shall lose money.
   */
  @Override
  public void perform(Player player) {
    player.deductMoney(moneyDeducted);
  }

  /**
   * Gets how much money gets deducted.
   *
   * @return amount of money to deduct.
   */
  public int getMoneyDeducted() {
    return moneyDeducted;
  }

  /**
   * Gets the description of the TileAction.
   *
   * @return the description of what the TileAction does.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets how much money this Tile should deduct.
   *
   * @param moneyDeducted amount of money to deduct.
   */
  public void setMoneyDeducted(int moneyDeducted) {
    this.moneyDeducted = moneyDeducted;
  }

  /**
   * sets the description for what the TileAction does.
   *
   * @param description describes what the TileAction does.
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
