package models.actions;

import interfaces.TileAction;
import models.Player;

/**
 * This class represents a property like the ones in Monopoly,
 * with the difference that it does not cost money to buy, and houses cannot be added.
 * The constructor set how much money the property costs to land on.
 * If a player lands on this tile, it gets the amount deducted from its account,
 * and the owner gets this money added to their account.
 * The first player to land on this tile becomes the owner.
 */
public class PropertyAction implements TileAction {
  private String description;
  private Player owner;
  private int buyPrice;
  private int landPrice;

  public PropertyAction(int buyPrice) {
    setDescription("Removes money and adds it to the property's owner.");
    setLandPrice(landPrice);
  }

  /**
   * Deducts money form the player who lands on the Tile and adds it to the owner's money.
   * The first player to land on it becomes the owner.
   *
   * @param player The player who landed on the tile and either looses money or becomes the owner.
   */
  @Override
  public void perform (Player player) {
    if (owner == null) {
      setOwner(player);
    } else if (!owner.equals(player)) {
      player.deductMoney(landPrice);
      owner.deductMoney(-landPrice);
    }
  }

  private void setDescription(String description) {
    this.description = description;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  private void setBuyPrice(int buyPrice) {
    this.buyPrice = buyPrice;
  }

  public void setLandPrice(int landPrice) {
    this.landPrice = landPrice;
  }

  public String getDescription() {
    return description;
  }

  public Player getOwner() {
    return owner;
  }

  private int getBuyPrice() {
    return buyPrice;
  }

  public int getLandPrice() {
    return landPrice;
  }
}
