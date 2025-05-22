package views.game.container;

import interfaces.BoardView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import views.game.content.DiceView;
import views.game.content.PlayerInfoView;

/**
 * <h3>Represents the view of the game</h3>
 *
 * <p>Contains the view of the board and the HUD.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class GameView extends HBox {

  private final HBox boardContainer;
  private final StackPane playerInfoContainer;
  private final StackPane diceContainer;

  private final BoardView boardView;
  private final PlayerInfoView playerInfoView;
  private final DiceView diceView;

  /**
   * Constructs a new view of the game. Creates containers to hold the board and HUD.
   */
  public GameView(BoardView boardView) {
    boardContainer = new HBox();
    playerInfoContainer = new StackPane();
    diceContainer = new StackPane();

    this.boardView = boardView;
    this.playerInfoView = new PlayerInfoView();
    this.diceView = new DiceView();
    this.setSpacing(20);

    initialize();
  }

  private void initialize() {
    boardContainer.getChildren().add(boardView.getView());
    playerInfoContainer.getChildren().add(playerInfoView);
    diceContainer.getChildren().add(diceView.getView());

    this.getChildren().setAll(playerInfoContainer,boardContainer, diceContainer);
  }

  /**
   * Gets the view of the game.
   *
   * @return the view as a Hbox.
   */
  public HBox getView() {
    return this;
  }

  /**
   * Gets the view of the board.
   *
   * @return the view of board.
   */
  public BoardView getBoardView() {
    return boardView;
  }

  /**
   * Gets the view of the HUD.
   *
   * @return the view of HUD.
   */
  public DiceView getDiceView() {
    return diceView;
  }

  public PlayerInfoView getPlayerInfoView() {
    return playerInfoView;
  }

}
