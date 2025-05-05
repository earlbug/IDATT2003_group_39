package views.content;


import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
public class TileView extends StackPane {

  private final GridPane playerGrid;

  /**
   * Constructs a new TileView that initializes the form of a tile.
   */
  public TileView() {
    Rectangle background = new Rectangle(60, 60);
    background.setFill(Color.TRANSPARENT);
    background.setStroke(Color.BLACK);

    playerGrid = new GridPane();
    playerGrid.setPrefSize(60, 60);
    playerGrid.setAlignment(Pos.CENTER);

    // Create and configure the 4 StackPane cells
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        StackPane cell = new StackPane();
        cell.setPrefSize(30, 30); // Each cell is 1/4th of the grid
        GridPane.setConstraints(cell, col, row);
        GridPane.setHalignment(cell, col == 0 ? HPos.LEFT : HPos.RIGHT);
        GridPane.setValignment(cell, row == 0 ? VPos.TOP : VPos.BOTTOM);
        playerGrid.getChildren().add(cell);
      }
    }

    StackPane layeredPane = new StackPane(background, playerGrid);
    this.getChildren().setAll(layeredPane);
  }

  /**
   * Adds a PlayerView to the tile in one of the 4 sections.
   *
   * @param playerView the PlayerView to add
   * @param playerId   the ID of the player.
   *                   Used to determine where in the Tile the PlayerView shall be placed.
   */
  public void addPlayerView(PlayerView playerView, int playerId) {
    if (playerView == null) {
      throw new IllegalArgumentException("PlayerView cannot be null");
    }

    // Calculate which cell to use (top-left, top-right, bottom-left, bottom-right)
    int col = playerId % 2; // 0 or 1
    int row = playerId / 2; // 0 or 1

    // Get the appropriate cell from the grid
    StackPane cell = (StackPane) playerGrid.getChildren().get(row * 2 + col);

    // Clear the cell and add the player view
    cell.getChildren().clear();
    cell.getChildren().add(playerView);
  }

  /**
   * Removes a PlayerView from the tile.
   *
   * @param playerView the PlayerView to remove
   */
  public void removePlayerView(PlayerView playerView) {
    playerGrid.getChildren().forEach(node -> {
      if (node instanceof StackPane cell) {
        cell.getChildren().remove(playerView);
      }
    });
  }

  /**
   * Gets the view of a tile.
   *
   * @return view of one tile
   */
  public StackPane getView() {
    return this;
  }

}