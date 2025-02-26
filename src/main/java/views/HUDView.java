package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HUDView {

  final int MAX_WITH = 300;

  VBox root = new VBox();
  VBox playerContainer = new VBox();
  VBox diceContainer = new VBox();

  Text playerTitle = new Text("Player");
  Text playerNumber = new Text(Integer.toString(1));

  Region spacer = new Region();

  Button rollDiceButton = new Button();
  Text diceNumber = new Text();

  public HUDView() {

    playerTitle.getStyleClass().add("player-title");
    playerTitle.setTextAlignment(TextAlignment.CENTER);

    playerNumber.getStyleClass().add("player-number");
    playerNumber.setTextAlignment(TextAlignment.CENTER);

    playerContainer.getStyleClass().add("player-container");
    playerContainer.setAlignment(Pos.CENTER);
    playerContainer.setMaxWidth(MAX_WITH);
    playerContainer.getChildren().addAll(playerTitle, playerNumber);

    spacer.setMinHeight(20);

    rollDiceButton.getStyleClass().add("roll-dice-button");
    rollDiceButton.setAlignment(Pos.CENTER);
    rollDiceButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        int diceResult = (int) (Math.random() * 6) + 1;
        diceNumber.setText(Integer.toString(diceResult));
      }
    });

    diceNumber.getStyleClass().add("dice-number");

    diceContainer.getStyleClass().add("dice-container");
    diceContainer.setAlignment(Pos.CENTER);
    diceContainer.setMaxWidth(MAX_WITH);
    diceContainer.getChildren().addAll(diceNumber,rollDiceButton);

    root.setPadding(new Insets(10));
    root.getChildren().addAll(playerContainer, spacer, diceContainer);
  }

  public VBox getView() {
    return root;
  }
}
