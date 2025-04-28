package factory;

import interfaces.Board;
import interfaces.BoardView;
import views.content.LudoBoardView;
import views.content.SnakesAndLaddersBoardView;

public class BoardViewFactory {

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