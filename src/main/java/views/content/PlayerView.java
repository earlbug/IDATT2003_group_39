package views.content;

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

  private Color playerColor = Color.BLACK;

  /**
   * Constructs a new view of the player.
   *
   */
  public PlayerView() {
    initialize();
  }

  private void initialize() {
    double playerSize = 40;
    this.setHeight(playerSize);
    this.setWidth(playerSize);
    this.setFill(playerColor);
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

  /**
   * Sets the color of the player.
   *
   * @param color The color to set
   */
  public void setPlayerColor(Color color) {
    this.playerColor = color;
  }
}