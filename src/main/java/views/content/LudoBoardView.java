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
import views.PlayerView;

public class LudoBoardView extends GridPane implements BoardView {

  private final int columns = 5;

  private final Map<Player, PlayerView> playerViews = new HashMap<>();

  @Override
  public void createBoardView(Board board) {
    Tile[] tiles = board.getTiles();
    for (int i = 1; i < tiles.length + 1; i++) {
      StackPane tileElement = createElement(board.getTile(i));
      int row = i / columns;
      int col = i % columns;
      this.add(tileElement, col, row);
    }
  }

  @Override
  public void registerPlayer(Player player) {
    PlayerView playerView = new PlayerView();
    playerViews.put(player, playerView);
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