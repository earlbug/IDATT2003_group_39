package views.content;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * <h3>Represents the view of a tile</h3>
 *
 * <p>Sets the size, color and outline of a single tile.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class TileView extends Rectangle {

  /**
   * Constructs a new TileView that initializes the form of a tile.
   */
  public TileView() {
    initialize();
  }

  private void initialize() {
    this.setWidth(60);
    this.setHeight(60);
    this.setFill(Color.ALICEBLUE);
    this.setStroke(Color.BLACK);
  }

  /**
   * Gets the view of a tile.
   *
   * @return view of one tile
   */
  public Rectangle getView() {
    return this;
  }

}