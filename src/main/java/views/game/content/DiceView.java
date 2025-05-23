package views.game.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import controllers.ButtonClickNotifier;

/**
 * <h3>Represents the view of the HUD</h3>
 *
 * <p>The HUD gives the user a view of the current player, dice throw and dice button.
 * Implements the ButtonClickListener to handle button clicks.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class DiceView extends VBox {

  final int maxWith = 300;

  private final VBox playerContainer;
  private final VBox diceContainer;

  private final Text playerTitle;
  private final Text playerName = new Text();

  private final Region spacer;

  private final Button rollDiceButton;
  private final Text diceNumberText = new Text();
  private final Button exitButton = new Button("Quit");

  private ButtonClickNotifier buttonClickNotifier;

  /**
   * Constructs containers to give the user a simple view of the info. Initializes the view to add
   * the information in the containers.
   */
  public DiceView() {
    this.playerContainer = new VBox();
    this.diceContainer = new VBox();

    this.playerTitle = new Text("Player to roll");

    this.spacer = new Region();
    this.rollDiceButton = new Button();

    initialize();
  }

  private void initialize() {
    playerTitle.getStyleClass().add("player-title");
    playerTitle.setTextAlignment(TextAlignment.CENTER);

    playerName.getStyleClass().add("player-name");
    playerName.setTextAlignment(TextAlignment.CENTER);

    playerContainer.getStyleClass().add("player-container");
    playerContainer.setAlignment(Pos.CENTER);
    playerContainer.setMaxWidth(maxWith);
    playerContainer.getChildren().setAll(playerTitle, playerName);

    spacer.setMinHeight(20);

    rollDiceButton.getStyleClass().add("roll-dice-button");
    rollDiceButton.setAlignment(Pos.CENTER);
    rollDiceButton.setOnAction(actionEvent -> buttonClickNotifier.notifyObservers("Play"));

    diceNumberText.getStyleClass().add("dice-number");

    diceContainer.getStyleClass().add("dice-container");
    diceContainer.setAlignment(Pos.CENTER);
    diceContainer.setMaxWidth(maxWith);
    diceContainer.getChildren().setAll(diceNumberText, rollDiceButton);

    exitButton.getStyleClass().add("exit-button");
    exitButton.setOnAction(actionEvent -> buttonClickNotifier.notifyObservers("Menu"));

    VBox.setVgrow(spacer, Priority.ALWAYS);

    this.getStyleClass().add("game-info-container");
    this.setSpacing(20);
    this.setPadding(new Insets(10));
    this.getChildren().setAll(playerContainer, diceContainer, spacer, exitButton);
  }

  public void disableRollButton() {
    this.rollDiceButton.setDisable(true);
  }

  /**
   * Sets the rolled dice number displayed to the user.
   *
   * @param diceNumber sum of all dice rolled.
   */
  public void setDiceNumber(int diceNumber) {
    this.diceNumberText.setText(String.valueOf(diceNumber));
  }


  /**
   * Sets the name displayed.
   *
   * @param name The name to be displayed
   */
  public void setPlayerName(String name) {
    this.playerName.setText(name);
  }

  /**
   * Gets the view of the HUD.
   *
   * @return a view of the HUD as a VBox.
   */
  public VBox getView() {
    return this;
  }

  /**
   * Sets the button click notifier that will handle button clicks
   *
   * @param notifier The notifier to handle button clicks
   */
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    this.buttonClickNotifier = notifier;
  }
}