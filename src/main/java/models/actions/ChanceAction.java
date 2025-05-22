package models.actions;

import interfaces.TileAction;
import java.util.Random;
import models.BoardGame;
import models.Player;
import models.Tile;

/**
 * ActionTile which either adds or deducts money from the Player landing on it.
 */
public class ChanceAction implements TileAction {

  Random rand = new Random();
  String description;
  int floor;
  int roof;

  /**
   * Constructor sets default min and max value of what the Player can gain/loose in terms of money.
   * Set default description.
   */
  public ChanceAction() {
    setDescription("Removes or adds a random amount of money.");
    setFloor(-50);
    setRoof(50);
  }

  /**
   * Either adds or removes money form the player landing on the tile.
   * Result is random.
   *
   * @param player The player who landed on the tile.
   */
  @Override
  public void perform(Player player) {
    int moneyToAdd = rand.nextInt(floor, roof + 1);
    player.addMoney(moneyToAdd);
  }

  /**
   * Gets the description of the ChanseAction.
   *
   * @return the description of the action
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the ChanseAction.
   *
   * @param description the description of the action
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the floor value for the ChanseAction.
   *
   * @return the floor value
   */
  public int getFloor() {
    return floor;
  }

  /**
   * Sets the floor value for the ChanseAction.
   *
   * @param floor the floor value to set
   */
  public void setFloor(int floor) {
    this.floor = floor;
  }

  /**
   * Gets the roof value for the ChanseAction.
   *
   * @return the roof value
   */
  public int getRoof() {
    return roof;
  }

  /**
   * Sets the roof value for the ChanseAction.
   *
   * @param roof the roof value to set
   */
  public void setRoof(int roof) {
    this.roof = roof;
  }




}
