package views.game.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * <h3>Represents the view of the winner popup</h3>
 *
 * <p>Displays the winner of the game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 */
public class WinnerPopup extends VBox {

  /**
   * Default constructor for the WinnerPopup class.
   */
  public WinnerPopup() {
  }

  /**
   * Displays the winner of the game.
   *
   * @param winner the name of the winning player
   */
  public void show(String winner) {
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(20));
    this.setSpacing(20);

    Text winnerText = new Text(winner + " won!");
    winnerText.getStyleClass().add("player-title");

    this.getChildren().addAll(winnerText);
  }

}