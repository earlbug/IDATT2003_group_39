package views.game.content;

import controllers.ButtonClickNotifier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WinnerPopup extends VBox{

  private ButtonClickNotifier notifier;

  public WinnerPopup() {
  }

  public void show(String winner) {
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(20));
    this.setSpacing(20);

    Text winnerText = new Text(winner + "wins!");
    winnerText.setFont(new Font(24));

    Button backButton = new Button("MENU");
    backButton.setOnAction(actionEvent -> notifier.notifyObservers("Menu"));

    this.getChildren().addAll(winnerText, backButton);
  }

  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    this.notifier = notifier;
  }
}