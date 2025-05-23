package views.game.content;

import interfaces.Board;
import interfaces.BoardView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import models.Player;
import models.Tile;

/**
 * Represents the view of the Snakes and Ladders board.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnakesAndLaddersBoardView extends StackPane implements BoardView {

  private final GridPane gridPane = new GridPane();
  private final Pane imagePane = new Pane();
  private final Pane playersPane = new Pane();

  private final Map<Player, PlayerView> playerViews = new HashMap<>();

  public SnakesAndLaddersBoardView() {
    this.getChildren().addAll(gridPane, imagePane, playersPane);
  }

  public void addPlayerView(Player player, PlayerView playerView) {
    this.playerViews.put(player, playerView);
  }

  @Override
  public void drawBoardView(Board board) {
    Tile[] tiles = board.getTiles();
    int columns = 10;
    int rows = (int) Math.ceil((double) tiles.length / columns);

    for (Tile tile : tiles) {
      StackPane tileElement = createElement(tile);
      int row = rows - 1 - (tile.getTileId() - 1) / columns;
      int col = (tile.getTileId() - 1) % columns;

      if ((rows - 1 - row) % 2 == 1) {
        col = columns - 1 - col;
      }

      gridPane.add(tileElement, col, row);
    }
  }

  private StackPane createElement(Tile tile) {
    return new TileView(tile.getTileId(), 80);
  }

  @Override
  public void drawBoardImage(int boardNumber) {
    Image image;
    switch (boardNumber) {
      case 1 -> image = new Image("file:src/main/resources/images/SnL1.png");
      case 2 -> image = new Image("file:src/main/resources/images/SnL2.png");
      case 3 -> image = new Image("file:src/main/resources/images/SnL3.png");
      default -> throw new IllegalArgumentException("Invalid board number: " + boardNumber);
    }
    ImageView imageView = new ImageView(image);

    imageView.setFitWidth(800);
    imageView.setFitHeight(800);
    imageView.setPreserveRatio(true);
    imagePane.getChildren().clear();
    imagePane.getChildren().add(imageView);
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
        ((double) playerIndex / 2) * 15 - (totalPlayers > 2 ? 7.5 : 0); // Rows of 2 players

    // Position the PlayerView on the TileView with offset
    double tileX = tilePosition[0];
    double tileY = tilePosition[1];
    playerView.setLayoutX(tileX + tileView.getWidth() / 2 - playerView.getFitWidth() / 2 + offsetX);
    playerView.setLayoutY(
        tileY + tileView.getHeight() / 2 - playerView.getFitHeight() / 2 + offsetY);

    if (!playersPane.getChildren().contains(playerView)) {
      playersPane.getChildren().add(playerView);
    }
  }


  @Override
  public Pane getView() {
    return this;
  }
}
