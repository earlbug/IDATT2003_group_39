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
import views.content.PlayerView;
import views.content.TileView;

public class SnakesAndLaddersBoardView extends GridPane implements BoardView {

    private final int columns = 10;

    private final Map<Player, PlayerView> playerViews = new HashMap<>();

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
        Rectangle tileBlock = tileView.getView();

        Text text = new Text(String.valueOf(tile.getTileId()));
        text.setFill(Color.BLACK);

        StackPane stack = new StackPane();
        stack.getChildren().setAll(tileBlock, text);

        return stack;
    }


    @Override
    public void updatePlayerView(Player player) {
        int currentTileId = player.getCurrentTile().getTileId();

        PlayerView playerView = playerViews.get(player);

        this.getChildren().forEach(node -> {
            if (node instanceof StackPane stack) {
                stack.getChildren().removeIf(child -> child == playerView);

                Integer row = GridPane.getRowIndex(stack);
                Integer col = GridPane.getColumnIndex(stack);

                if (row != null && col != null) {
                    int calculatedTileId;

                    // Adjust column for "snakes and ladders" pattern
                    if ((this.getRowCount() - 1 - row) % 2 == 1) {
                        col = columns - 1 - col;
                    }

                    calculatedTileId = (this.getRowCount() - 1 - row) * columns + col + 1;

                    if (calculatedTileId == currentTileId) {
                        stack.getChildren().add(playerView);
                    }
                }
            }
        });
    }

    public void registerPlayer(Player player) {
        PlayerView playerView = new PlayerView();
        playerViews.put(player, playerView);
    }

    @Override
    public Pane getView() {
        return this;
    }
}