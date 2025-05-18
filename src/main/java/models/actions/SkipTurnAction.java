package models.actions;

import interfaces.TileAction;
import models.Player;

/**
 * A TileAction where the player who lands on it has to skip a number of turns.
 */
public class SkipTurnAction implements TileAction {
  private String description;
  private int turnsToSkip;

  /**
   * Constructor which sets default amount of turns to skip, which is 1.
   */
  public SkipTurnAction() {
    setDescription("Skip the player next turn.");
    setTurnsToSkip(1);
  }

  /**
   * Increases the amount of turns the Player has to skip.
   *
   * @param player The player who landed on the tile.
   */
  @Override
  public void perform(Player player) {
    player.addTurnsToSkip(turnsToSkip);
  }

  /**
   * Sets the description for the SkipTurnAction.
   *
   * @param description describes what the Tile do.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the description for the SkipTurnAction.
   *
   * @return description of what the Tile does.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the amount of turns the player has to skip.
   *
   * @return turns to skip
   */
  public int getTurnsToSkip() {
    return turnsToSkip;
  }

  /**
   * Sets how many turns the player has to skip.
   *
   * @param turnsToSkip the number of turns to skip.
   */
  public void setTurnsToSkip(int turnsToSkip) {
    this.turnsToSkip = turnsToSkip;
  }
}
