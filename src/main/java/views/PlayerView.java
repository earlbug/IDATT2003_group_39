package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Player;

/**
 * <h3>Represents the view of a player</h3>
 *
 * <p>Sets the style of a single player.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class PlayerView extends Rectangle {

  private final Color color;

  /**
   * Constructs a new view of the player.
   *
   * @param player the given player to create
   * @param color  the color of the player
   */
  public PlayerView(Player player, Color color) {
    this.color = color;

    initialize();
  }

  private void initialize() {
    double playerSize = 40;
    this.setHeight(playerSize);
    this.setWidth(playerSize);
    this.setFill(color);
    this.getStyleClass().add("player-avatar");
  }

  /**
   * Get the players' avatar.
   *
   * @return a rectangle representing the player
   */
  public Rectangle getView() {
    return this;
  }
}
