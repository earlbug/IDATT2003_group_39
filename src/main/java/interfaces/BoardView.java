package interfaces;

import javafx.scene.layout.Pane;
import models.Player;

/**
 * Interface for creating a view of a board in a game. This interface defines methods for creating
 * and updating the board view.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BoardView {

  /**
   * Creates the board view based on the provided board.
   *
   * @param board The board instance to create the view for
   */
  void drawBoardView(Board board);

  /**
   * Draws the image of the board.
   *
   * @param boardNumber The number of the board to draw
   */
  void drawBoardImage(int boardNumber);

  /**
   * Updates the player view on the board.
   *
   * @param player The player instance to update the view for
   */
  void drawPlayerView(Player player);

  /**
   * Gets the view of the board.
   *
   * @return The Pane representing the board view
   */
  Pane getView();
}