package views.content;

import interfaces.Board;
import interfaces.BoardView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.Player;
import models.Tile;

/**
 * MonopolyBoardView is an implementation of the BoardView.
 * Creates the view of the Monopoly board. The Tiles in the board has to be an amount which can be
 * divided by four to create a proper board.
 * The first Tile is in the upper left corner and follows the edge with the direction of the clock.
 *
 */
public class MonopolyBoardView extends GridPane implements BoardView {

  private final Map<Player, PlayerView> playerViews = new HashMap<>();

  /**
   * Creates the layout of the Monopoly board.
   * The first Tile is in the upper left corner and follows the edge with the direction of the
   * clock.
   *
   * @param board The board instance to create the view for.
   */
  @Override
  public void createBoardView(Board board) {
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

      this.add(tileElement, col, row);
    }
  }

  /**
   * Add a player to a Playerview.
   *
   * @param player The player to add
   * @param playerView the playerview to add the player.
   */
  public void addPlayerView(Player player, PlayerView playerView) {
    this.playerViews.put(player, playerView);
  }

  /**
   * Updates the PlayerView by removing the playerView, and then add the player to the playerView.
   *
   * @param player The player instance to update.
   */
  @Override
  public void updatePlayerView(Player player) {
    PlayerView playerView = playerViews.get(player);
    Tile currentTile = player.getCurrentTile();

    // Remove the player view from all tiles
    this.getChildren().forEach(node -> {
      if (node instanceof TileView tileView) {
        tileView.removePlayerView(playerView);
      }
    });

    // Add the player view to the new tile
    TileView tileView = (TileView) this.getChildren().get(currentTile.getTileId() - 1);
    tileView.addPlayerView(playerView, player.getPlayerId());
  }

  /**
   * Creates a Tile Element.
   *
   * @param tile The Tile to be created into a Tile Element.
   * @return a TileView.
   */
  private StackPane createElement(Tile tile) {
    TileView tileView = new TileView();

    Text text = new Text(String.valueOf(tile.getTileId()));
    text.setFill(Color.BLACK);

    tileView.getChildren().add(text);

    return tileView;
  }

  @Override
  public Pane getView() {
    return this;
  }
}