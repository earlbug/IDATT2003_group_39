package views;

import controllers.Board;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.Player;
import models.Tile;
import views.container.MainView;

/**
 * This class represents the view of the game board.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class BoardView extends GridPane{

  private MainView mainView;
  private TileView tileView;
  private Board board;
  private Tile tile;
  private Player player;
  private PlayerView playerView;

  private final double elementSizeX = 55;
  private final double elementSizeY = 59;

  private final int rows = 10;
  private final int columns = 9;

  private List<PlayerView> playerViews = new ArrayList<>();

  public BoardView(MainView mainView) {
    this.mainView = mainView;
    //this.player = new Player();
    this.board = new Board();
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
    this.getChildren().clear();
    int number = 1;
    for (int row = rows - 1; row >= 0; row--) {
      if (row % 2 == 0) {
        for (int col = columns - 1; col >= 0; col--) {
          board.addTile(new Tile(number));
          this.add(createElement(number++), col, row);
        }
      } else {
        for (int col = 0; col < columns; col++) {
          board.addTile(new Tile(number));
          this.add(createElement(number++), col, row);
        }
      }
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

  public void movePlayerToTile(int newIndex) {
    this.getChildren().forEach(node -> {
      if (node instanceof StackPane stack) {
        stack.getChildren().removeIf(child -> playerViews.stream()
            .anyMatch(playerView -> playerView.getView().equals(child)));
      }
    });

    this.getChildren().forEach(node -> {
      if (node instanceof StackPane stack) {
        if (tile.getTileId() == newIndex)
        stack.getChildren().add(playerView.getView());
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
