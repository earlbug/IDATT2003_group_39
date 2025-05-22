package views.game.content;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * <h3>Represents the view of a tile</h3>
 *
 * <p>Sets the size, color and outline of a single tile.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class TileView extends StackPane {

  private final int tileId;

  /**
   * Constructs a new TileView that initializes the form of a tile.
   */
  public TileView(int tileId, int size) {
    this.tileId = tileId;
    Rectangle rectangle = new Rectangle(size,  size);
    rectangle.setFill(Color.TRANSPARENT);

    Text text = new Text(String.valueOf(tileId));


    StackPane layeredPane = new StackPane(rectangle, text);
    this.getChildren().setAll(layeredPane);
  }

  /**
   * Gets the view of a tile.
   *
   * @return view of one tile
   */
  public StackPane getView() {
    return this;
  }

  /**
   * Gets the ID of the tile.
   *
   * @return the ID of the tile
   */
  public int getTileId() {
    return tileId;
  }
}