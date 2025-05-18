package models.actions;

import interfaces.TileAction;
import models.BoardGame;
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

  /**
   * Constructor which sets the default description and land price.
   *
   * @param landPrice the price which the player landing on the tile has to pay to the owner.
   */
  public PropertyAction(int landPrice) {
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
  public void perform(Player player) {
    if (owner == null) {
      setOwner(player);
    } else if (!owner.equals(player)) {
      player.deductMoney(landPrice);
      owner.deductMoney(-landPrice);
    }
  }

  @Override
  public void perform(Player player, BoardGame boardGame) {
    // Not used
  }

  /**
   * Sets the description of the property action.
   *
   * @param description The description of the property action.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the owner of the property.
   *
   * @param owner The player who becomes the owner of the property.
   */
  public void setOwner(Player owner) {
    this.owner = owner;
  }

  /**
   * Sets the buy price of the property.
   *
   * @param buyPrice The price to buy the property.
   */
  private void setBuyPrice(int buyPrice) {
    this.buyPrice = buyPrice;
  }

  /**
   * Sets the land price of the property.
   *
   * @param landPrice The price a player must pay when landing on the property.
   */
  public void setLandPrice(int landPrice) {
    this.landPrice = landPrice;
  }

  /**
   * Gets the description of the property action.
   *
   * @return The description of the property action.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the owner of the property.
   *
   * @return The player who owns the property.
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * Gets the buy price of the property.
   *
   * @return The price to buy the property.
   */
  private int getBuyPrice() {
    return buyPrice;
  }

  /**
   * Gets the land price of the property.
   *
   * @return The price a player must pay when landing on the property.
   */
  public int getLandPrice() {
    return landPrice;
  }
}
