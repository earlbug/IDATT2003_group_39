package views.game.content;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * <h3>Represents the view of an action tile</h3>
 *
 * <p>Contains the view of the action tile.
 *
 * @author Erlend Sundsdal
 * @version 1.0.0
 */
public class ActionTileView extends Rectangle {

  /**
   * Constructs a new view of the action tile.
   *
   * <p>Creates a rectangle to hold the action tile.
   */
  public ActionTileView() {
    initialize();
  }

  /**
   * Initializes the view of the action tile.
   */
  private void initialize() {
    this.setHeight(60);
    this.setWidth(60);
  }

  /**
   * Sets the background of the action tile.
   *
   * @param fileName the name of the image file to be used as the background.
   */
  public void setBackground(String fileName) {
    Image image = new Image("resources/data/actionTileImages/" + fileName + ".png");
    ImagePattern pattern = new ImagePattern(image);
    this.setFill(pattern);
  }

  public Rectangle getView() {
    return this;
  }
}
