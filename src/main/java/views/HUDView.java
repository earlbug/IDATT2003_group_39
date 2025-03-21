package views;

import controllers.Dice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import models.Player;
import views.container.MainView;

public class HUDView extends VBox{

  private final BoardView boardView;
  private final Dice dice;

  final int MAX_WITH = 300;

  private final VBox playerContainer;
  private final VBox diceContainer;

  private Text playerTitle;
  private Text playerNumber;

  private Region spacer;

  private Button rollDiceButton;
  private Text diceNumber;

  private Button testPlayerPos;
  private Player player;

  public HUDView(MainView mainView, BoardView boardView) {
    this.boardView = boardView;
    this.dice = new Dice(1);
    this.playerContainer = new VBox();
    this.diceContainer = new VBox();

    this.playerTitle = new Text("Player");
    this.playerNumber = new Text(Integer.toString(1));

    this.spacer = new Region();
    this.rollDiceButton = new Button();
    this.diceNumber = new Text();

    this.testPlayerPos = new Button("TestPos");

    initialize();
  }

  private void initialize(){
    playerTitle.getStyleClass().add("player-title");
    playerTitle.setTextAlignment(TextAlignment.CENTER);

    playerNumber.getStyleClass().add("player-number");
    playerNumber.setTextAlignment(TextAlignment.CENTER);

    playerContainer.getStyleClass().add("player-container");
    playerContainer.setAlignment(Pos.CENTER);
    playerContainer.setMaxWidth(MAX_WITH);
    playerContainer.getChildren().setAll(playerTitle, playerNumber);

    spacer.setMinHeight(20);

    rollDiceButton.getStyleClass().add("roll-dice-button");
    rollDiceButton.setAlignment(Pos.CENTER);
    rollDiceButton.setOnAction(actionEvent -> {
      dice.rollAllDice();
    });

    testPlayerPos.setOnAction(actionEvent -> boardView.movePlayerToTile(5));

    diceNumber.getStyleClass().add("dice-number");

    diceContainer.getStyleClass().add("dice-container");
    diceContainer.setAlignment(Pos.CENTER);
    diceContainer.setMaxWidth(MAX_WITH);
    diceContainer.getChildren().setAll(diceNumber, rollDiceButton, testPlayerPos);

    this.setPadding(new Insets(10));
    this.getChildren().setAll(playerContainer, spacer, diceContainer);
  }

  public VBox getView() {
    return this;
  }
}
