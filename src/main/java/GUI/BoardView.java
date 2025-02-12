package GUI;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class represents the view of the game board.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class BoardView {

  private final double elementSizeX = 55;
  private final double elementSizeY = 59;

  private GridPane gridPane = new GridPane();
  private Pane display = new Pane(gridPane);

  private final int rows = 10;
  private final int columns = 9;

  private PlayerView playerView;

  public BoardView() {
    createBoard();

    playerView = new PlayerView();
    display.getChildren().add(playerView.getPlayer());
    setPlayerPosition(10, 0);
  }

  /**
   * Returns the view of the game board.
   *
   * @return the view of the game board
   */
  public Pane getView() {
    return display;
  }

  /**
   * Creates the game board based on rows and columns.
   */
private void createBoard() {
    gridPane.getChildren().clear();
    int number = 1;
    for (int row = rows - 1; row >= 0; row--) {
        if (row % 2 == 0) {
            for (int col = columns -1; col >= 0; col--) {
                gridPane.add(createElement(number++), col, row);
            }
        } else {
            for (int col = 0; col < columns; col++) {
                gridPane.add(createElement(number++), col, row);
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
    Rectangle tile = new Rectangle(elementSizeX, elementSizeY);
    tile.setStroke(Color.BLACK);
    tile.setFill(Color.WHITE);

    Text text = new Text(String.valueOf(index));
    text.setFill(Color.BLACK);

    StackPane stack = new StackPane();
    stack.getChildren().addAll(tile, text);

    return stack;
  }

  public void setPlayerPosition(int row, int column) {
    double x = column * elementSizeX;
    double y = row * elementSizeY;
    playerView.setPosition(x, y);
  }
}
