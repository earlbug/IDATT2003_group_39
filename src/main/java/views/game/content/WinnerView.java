package views.game.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.Player;

public class WinnerView extends HBox {

  private final Player player;

  public WinnerView(Player player) {
    this.player = player;
    initialize();
  }

  private void initialize() {
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(20));

    Text text = new Text(player.getName() + " wins!");
    text.setFont(new Font(24));
    this.getChildren().setAll(text);
  }
}
