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

public class MonopolyBoardView extends GridPane implements BoardView {

  private final int columns = 5;

  private final Map<Player, PlayerView> playerViews = new HashMap<>();

  @Override
  public void createBoardView(Board board) {
    Tile[] tiles = board.getTiles();
    int boardLength = tiles.length / 4;

    for (int i = 1; i < tiles.length + 1; i++) {
      StackPane tileElement = createElement(board.getTile(i));
      int row, col;

      if (i <= boardLength) { // Top edge
        row = 0;
        col = i - 1;
      } else if (i <= boardLength * 2) { // Right edge
        row = i - boardLength - 1;
        col = columns - 1;
      } else if (i <= boardLength * 3) { // Bottom edge
        row = boardLength - 1;
        col = boardLength * 3 - i;
      } else { // Left edge
        row = boardLength * 4 - i;
        col = 0;
      }

      this.add(tileElement, col, row);
    }
  }

  /**
   * Updates the position of the player on the board.
   *
   * @param player the player to get updated.
   */
  public void updatePlayerView(Player player) {
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

  private StackPane createElement(Tile tile) {
    Rectangle tileBlock = new Rectangle(50, 50, Color.LIGHTGREEN);
    Text text = new Text(String.valueOf(tile.getTileId()));
    StackPane stack = new StackPane();
    stack.getChildren().addAll(tileBlock, text);
    return stack;
  }

  @Override
  public Pane getView() {
    return this;
  }
}