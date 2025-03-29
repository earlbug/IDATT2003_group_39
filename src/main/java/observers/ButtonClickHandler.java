package observers;

import controllers.BoardGame;
import views.container.GameView;

/**
 * <h3>Handles button clicks</h3>
 *
 * <p>Runs different actions based on type of button pressed by the user.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class ButtonClickHandler implements ButtonClickListener {

  private final BoardGame boardGame;
  private final GameView gameView;

  /**
   * Constructs a new handler to handle button clicks.
   *
   * @param boardGame the current boardGame being played.
   * @param gameView  the current gameView of the boardGame.
   */
  public ButtonClickHandler(BoardGame boardGame, GameView gameView) {
    this.boardGame = boardGame;
    this.gameView = gameView;
  }

  @Override
  public void update(String action) {
    System.out.println("Button was clicked " + action);

    switch (action) {
      case "play":
        boardGame.getDice().rollAllDice();
        int sum = boardGame.getDice().getSumOfAllDie();
        boardGame.playOneTurn(sum);
        gameView.getHudView().setDiceNumber(sum);
        gameView.getBoardView().updatePlayer(boardGame.getCurrentPlayer());
        boardGame.nextPlayer();
        break;
      default:
        System.out.println("Unknown action" + action);
    }
  }

}
