package views;

import controllers.Board;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * This class represents the view of the game board.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class BoardView extends GridPane {

  private final int columns = 9;

  private final Map<Player, PlayerView> playerViews = new HashMap<>();

  /**
   * Creates the game board based on rows and columns.
   */
  public void createBoard(Board board) {
    Tile[] tiles = board.getTiles();
    for (int i = 1; i < tiles.length + 1; i++) {
      StackPane tileElement = createElement(board.getTile(i));
      int row = i / columns;
      int col = i % columns;
      this.add(tileElement, col, row);
    }
  }

  /**
   * Creates a single element of a tile on the game board.
   *
   * @return tile element
   */
  private StackPane createElement(Tile tile) {
    TileView tileView = new TileView();
    Rectangle tileBlock = tileView.getView();

    Text text = new Text(String.valueOf(tile.getTileId()));
    text.setFill(Color.BLACK);

    StackPane stack = new StackPane();
    stack.getChildren().setAll(tileBlock, text);

    return stack;
  }

  /**
   * Updates the position of the player on the board.
   *
   * @param player the player to get updated.
   */
  public void updatePlayer(Player player) {
    int currentTileId = player.getCurrentTile().getTileId();

    PlayerView playerView = playerViews.get(player);

    this.getChildren().forEach(node -> {
      if (node instanceof StackPane stack) {
        stack.getChildren().removeIf(child -> child == playerView);
        if (GridPane.getRowIndex(stack) * columns + GridPane.getColumnIndex(stack)
            == currentTileId) {
          stack.getChildren().add(playerView);
        }
      }
    });
  }

  /**
   * Returns the view of the game board.
   *
   * @return the view of the game board
   */
  public Pane getView() {
    return this;
  }

  public void registerPlayer(Player player) {
    PlayerView playerView = new PlayerView();
    playerViews.put(player, playerView);
  }
}
