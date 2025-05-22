package views.game.content;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.GamePiece;

/**
 * <h3>Represents the view of a player</h3>
 *
 * <p>Sets the style of a single player.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class PlayerView extends ImageView {

  /**
   * Constructs a new view of the player.
   */
  public PlayerView() {
    initialize();
  }

  private void initialize() {
    double playerSize = 50;
    this.setFitHeight(playerSize);
    this.setFitWidth(playerSize);
  }

  /**
   * Get the players' avatar.
   *
   * @return a rectangle representing the player
   */
  public ImageView getView() {
    return this;
  }

  /**
   * Sets the game piece of the player.
   *
   * @param gamePiece the game piece of the player
   */
  public void setPlayerGamePiece(GamePiece gamePiece) {
    switch (gamePiece) {
      case CAR -> setImage(new Image("file:src/main/resources/images/pieces/car.png"));
      case BOAT -> setImage(new Image("file:src/main/resources/images/pieces/boat.png"));
      case PLANE -> setImage(new Image("file:src/main/resources/images/pieces/plane.png"));
      case TRAIN -> setImage(new Image("file:src/main/resources/images/pieces/train.png"));
    }
  }
}