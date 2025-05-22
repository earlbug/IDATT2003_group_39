package views.game.content;

import interfaces.Board;
import interfaces.BoardView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import models.Player;
import models.Tile;

/**
 * MonopolyBoardView is an implementation of the BoardView.
 * Creates the view of the Monopoly board. The Tiles in the board has to be an amount which can be
 * divided by four to create a proper board.
 * The first Tile is in the upper left corner and follows the edge with the direction of the clock.
 *
 */
public class MonopolyBoardView extends StackPane implements BoardView {

  private final GridPane gridPane = new GridPane();
  private final Pane imagePane = new Pane();
  private final Pane playersPane = new Pane();

  private final Map<Player, PlayerView> playerViews = new HashMap<>();


  public MonopolyBoardView() {
    this.getChildren().addAll(gridPane, imagePane, playersPane);
  }

  public void addPlayerView(Player player, PlayerView playerView) {
    this.playerViews.put(player, playerView);
  }

  /**
   * Creates the layout of the Monopoly board.
   * The first Tile is in the upper left corner and follows the edge with the direction of the
   * clock.
   *
   * @param board The board instance to create the view for.
   */
  @Override
  public void drawBoardView(Board board) {
    Tile[] tiles = board.getTiles();
    int rowLength = tiles.length / 4;
    for (int i = 1; i < tiles.length + 1; i++) {
      StackPane tileElement = createElement(board.getTile(i)); // Create the element
      // Put the element in the right colum and row according to its id.
      int row, col;
      if (i <= rowLength) {            // Top edge
        row = 0;
        col = i - 1;
      } else if (i <= rowLength * 2) { // Right edge
        row = i - (rowLength + 1);
        col = rowLength;
      } else if (i <= rowLength * 3) { // Bottom edge
        row = rowLength;
        col = rowLength * 3 + 1 - i;
      } else {                        // Left edge
        row = rowLength * 4 + 1 - i;
        col = 0;
      }

      gridPane.add(tileElement, col, row);
    }
  }

  @Override
  public void drawBoardImage(int boardNumber) {
    if (boardNumber == 4) {
      Image image = new Image("file:src/main/resources/images/monopoly.png");
      ImageView imageView = new ImageView(image);

      imageView.setFitWidth(810);
      imageView.setFitHeight(810);
      imageView.setOpacity(0.5);
      imageView.setPreserveRatio(true);
      imageView.setEffect(new DropShadow(10, Color.BLACK));
      imagePane.getChildren().clear();
      imagePane.getChildren().add(imageView);
    }
  }

  /**
   * Gets the pixel coordinates of a TileView in the GridPane based on its tileId
   *
   * @param tileView The TileView to get the position of
   * @return Double array where [0] is x-coordinate and [1] is y-coordinate, or null if not found
   */
  private double[] getTileViewPosition(TileView tileView) {
    if (tileView == null) {
      return null;
    }

    // Make sure layout is complete
    gridPane.applyCss();
    gridPane.layout();

    // Get bounds relative to the imagePane
    javafx.geometry.Bounds boundsInScene = tileView.localToScene(tileView.getBoundsInLocal());
    javafx.geometry.Bounds boundsInImagePane = imagePane.sceneToLocal(boundsInScene);

    double tileX = boundsInImagePane.getMinX();
    double tileY = boundsInImagePane.getMinY();

    return new double[]{tileX, tileY};
  }


  private TileView findTileViewById(int tileId) {
    for (TileView tileView : gridPane.getChildren().stream()
        .filter(node -> node instanceof TileView).map(node -> (TileView) node).toList()) {
      if (tileView.getTileId() == tileId) {
        return tileView;
      }
    }
    return null;
  }

  @Override
  public void drawPlayerView(Player player) {
    PlayerView playerView = playerViews.get(player);
    Tile currentTile = player.getCurrentTile();

    // Find the TileView for the current tile
    TileView tileView = findTileViewById(currentTile.getTileId());

    // Get the position of the TileView
    double[] tilePosition = getTileViewPosition(tileView);

    int playerIndex = 0;
    int totalPlayers = playerViews.size();

    int i = 0;
    for (Player p : playerViews.keySet()) {
      if (p.equals(player)) {
        playerIndex = i;
        break;
      }
      i++;
    }

    // Calculate offset based on player index
    double offsetX = (playerIndex % 2) * 20 - 7.5; // Alternating left-right
    double offsetY =
        ((double) playerIndex / 2) * 20 - (totalPlayers > 2 ? 7.5 : 0); // Rows of 2 players

    // Position the PlayerView on the TileView with offset
    double tileX = tilePosition[0];
    double tileY = tilePosition[1];
    playerView.setLayoutX(tileX + tileView.getWidth() / 2 - playerView.getFitWidth() / 2 + offsetX);
    playerView.setLayoutY(tileY + tileView.getHeight() / 2 - playerView.getFitHeight() / 2 + offsetY);

    if (!playersPane.getChildren().contains(playerView)) {
      playersPane.getChildren().add(playerView);
    }
  }

  /**
   * Creates a Tile Element.
   *
   * @param tile The Tile to be created into a Tile Element.
   * @return a TileView.
   */
  private StackPane createElement(Tile tile) {
    return new TileView(tile.getTileId(), 90);
  }

  @Override
  public Pane getView() {
    return this;
  }
}