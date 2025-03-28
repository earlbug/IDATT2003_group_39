package views.container;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import views.BoardView;
import views.HudView;

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
  private final StackPane hudContainer;

  private final BoardView boardView;
  private final HudView hudView;

  /**
   * Constructs a new view of the game. Creates containers to hold the board and HUD.
   */
  public GameView() {
    boardContainer = new HBox();
    hudContainer = new StackPane();

    this.boardView = new BoardView();
    this.hudView = new HudView();

    initialize();
  }

  private void initialize() {
    boardContainer.getChildren().add(boardView.getView());
    hudContainer.getChildren().add(hudView.getView());
    StackPane.setAlignment(hudView.getView(), Pos.CENTER);
    HBox.setHgrow(hudContainer, Priority.ALWAYS);

    this.getChildren().setAll(boardContainer, hudContainer);
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
  public HudView getHudView() {
    return hudView;
  }
}
