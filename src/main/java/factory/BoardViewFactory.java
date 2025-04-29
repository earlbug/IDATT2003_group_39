package factory;

import interfaces.Board;
import interfaces.BoardView;
import views.content.LudoBoardView;
import views.content.SnakesAndLaddersBoardView;

/**
 * Factory class for creating board view instances based on game type.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class BoardViewFactory {

  /**
   * Creates a board view instance based on the provided game type and board.
   *
   * @param gameType The type of game
   * @param board    The board instance
   * @return BoardView instance corresponding to the game type
   */
  public static BoardView createBoardView(GameType gameType, Board board) {
    return switch (gameType) {
      case SNAKES_AND_LADDERS -> {
        BoardView view = new SnakesAndLaddersBoardView();
        view.createBoardView(board);
        yield view;
      }
      case LUDO -> {
        BoardView view = new LudoBoardView();
        view.createBoardView(board);
        yield view;
      }
    };
  }
}