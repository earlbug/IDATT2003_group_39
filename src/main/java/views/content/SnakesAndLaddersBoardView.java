package views.content;

import interfaces.Board;
import interfaces.BoardView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.Player;
import models.Tile;

public class SnakesAndLaddersBoardView extends GridPane implements BoardView {

  private final int columns = 10;

  private final Map<Player, PlayerView> playerViews = new HashMap<>();

  public void addPlayerView(Player player, PlayerView playerView) {
    this.playerViews.put(player, playerView);
  }

  @Override
  public void createBoardView(Board board) {
    Tile[] tiles = board.getTiles();
    int rows = (int) Math.ceil((double) tiles.length / columns);

    for (Tile tile : tiles) {
      StackPane tileElement = createElement(tile);
      int row = rows - 1 - (tile.getTileId() - 1) / columns;
      int col = (tile.getTileId() - 1) % columns;

      if ((rows - 1 - row) % 2 == 1) {
        col = columns - 1 - col;
      }

      this.add(tileElement, col, row);
    }
  }

  private StackPane createElement(Tile tile) {
    TileView tileView = new TileView();

    Text text = new Text(String.valueOf(tile.getTileId()));
    text.setFill(Color.BLACK);

    tileView.getChildren().add(text);

    return tileView;
  }

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

  @Override
  public void registerPlayer(Player player) {
    PlayerView playerView = new PlayerView();
    playerViews.put(player, playerView);
  }


  @Override
  public Pane getView() {
    return this;
  }
}
