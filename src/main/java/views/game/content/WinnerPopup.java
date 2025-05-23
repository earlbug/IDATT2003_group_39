package views.game.content;

import controllers.ButtonClickNotifier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WinnerPopup extends VBox{

  private ButtonClickNotifier notifier;

  public WinnerPopup() {
  }

  public void show(String winner) {
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(20));
    this.setSpacing(20);

    Text winnerText = new Text(winner + " won!");
    winnerText.getStyleClass().add("player-title");

    this.getChildren().addAll(winnerText);
  }

}