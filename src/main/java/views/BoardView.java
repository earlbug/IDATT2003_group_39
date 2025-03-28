package views;

import controllers.Board;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.PointLight;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.Player;
import models.Tile;
import views.container.GameView;

/**
 * This class represents the view of the game board.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class BoardView extends GridPane {

  private GameView gameView;
  private TileView tileView;
  private final Board board;
  private Tile tile;
  private Player player;
  private PlayerView playerView;

  private final int columns = 9;

  private List<PlayerView> playerViews = new ArrayList<>();

  public BoardView(GameView gameView, Board board) {
    this.gameView = gameView;
    //this.player = new Player();
    this.board = board;
    this.tileView = new TileView(tile);
    initializePlayers();
  }

  private void initializePlayers() {
    //Player player1 = new Player();
    //Player player2 = new Player();
    //playerViews.add(new PlayerView(player1, Color.RED));
    //playerViews.add(new PlayerView(player2, Color.BLUE));
  }

  /**
   * Creates the game board based on rows and columns.
   */
  private void createBoard() {
    Tile[] tiles = board.getTiles();
    for (int i = 1; i < tiles.length + 1; i++) {
      StackPane tileElement = createElement(i);
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
  private StackPane createElement(int index) {
    TileView tileView = new TileView(tile);
    Rectangle tile = tileView.getView();

    Text text = new Text(String.valueOf(index));
    text.setFill(Color.BLACK);

    StackPane stack = new StackPane();
    stack.getChildren().setAll(tile, text);

    for (PlayerView playerView : playerViews) {
      if (index == 1) {
        stack.getChildren().add(playerView.getView());
      }
    }
    return stack;
  }

  public void updatePlayer(Player player) {
    int currentTileId = player.getCurrentTile().getTileId();

    this.getChildren().forEach(node -> {
      if (node instanceof StackPane stack) {
        stack.getChildren().removeIf(child -> child instanceof PlayerView);
        if (GridPane.getRowIndex(stack) * columns + GridPane.getColumnIndex(stack)
            == currentTileId) {
          stack.getChildren().add(new PlayerView(player, Color.BLUE).getView());
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
    createBoard();
    return this;
  }
}
