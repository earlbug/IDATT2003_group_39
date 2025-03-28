package views;

import controllers.BoardGame;
import controllers.Dice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HUDView extends VBox{

  private final BoardView boardView;
  private final Dice dice;

  final int MAX_WITH = 300;

  private final VBox playerContainer;
  private final VBox diceContainer;

  private final Text playerTitle;
  private final Text playerNumber;

  private final Region spacer;

  private final Button rollDiceButton;
  private final Text diceNumber;

  private final BoardGame boardGame;

  public HUDView(BoardView boardView, BoardGame boardGame) {
    this.boardView = boardView;
    this.boardGame = boardGame;
    this.dice = new Dice(1);
    this.playerContainer = new VBox();
    this.diceContainer = new VBox();

    this.playerTitle = new Text("Player");
    this.playerNumber = new Text(Integer.toString(1));

    this.spacer = new Region();
    this.rollDiceButton = new Button();
    this.diceNumber = new Text();

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
      boardGame.playOneTurn(dice.getSumOfAllDie());
      diceNumber.setText(Integer.toString(dice.getSumOfAllDie()));
      boardView.updatePlayer(boardGame.getCurrentPlayer());
    });

    diceNumber.getStyleClass().add("dice-number");

    diceContainer.getStyleClass().add("dice-container");
    diceContainer.setAlignment(Pos.CENTER);
    diceContainer.setMaxWidth(MAX_WITH);
    diceContainer.getChildren().setAll(diceNumber, rollDiceButton);

    this.setPadding(new Insets(10));
    this.getChildren().setAll(playerContainer, spacer, diceContainer);
  }

  public VBox getView() {
    return this;
  }
}
