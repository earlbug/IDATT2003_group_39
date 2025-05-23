package views.menu.content;

import controllers.ButtonClickNotifier;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.GamePiece;
import net.synedra.validatorfx.TooltipWrapper;
import net.synedra.validatorfx.Validator;
import views.menu.container.MenuView;

public class SelectPlayersView extends MenuView {

  private ButtonClickNotifier notifier;
  private Validator validator = new Validator();
  private final Button startButton = new Button("Start");
  private final HBox layout = new HBox();
  private final VBox textFields = new VBox();
  private TextField[] fields;

  /**
   * Constructs the view.
   */
  public SelectPlayersView() {
    initialize();
    setTextFields(1);
  }

  private void initialize() {
    TilePane container = new TilePane();
    Button player1 = new Button("1");
    Button player2 = new Button("2");
    Button player3 = new Button("3");
    Button player4 = new Button("4");

    player1.getStyleClass().add("menu-button");
    player2.getStyleClass().add("menu-button");
    player3.getStyleClass().add("menu-button");
    player4.getStyleClass().add("menu-button");

    player1.setPrefSize(100, 100);
    player2.setPrefSize(100, 100);
    player3.setPrefSize(100, 100);
    player4.setPrefSize(100, 100);

    player1.setOnAction(actionEvent -> setTextFields(1));
    player2.setOnAction(actionEvent -> setTextFields(2));
    player3.setOnAction(actionEvent -> setTextFields(3));
    player4.setOnAction(actionEvent -> setTextFields(4));

    // Start button
    startButton.getStyleClass().add("menu-button");
    startButton.setOnAction(actionEvent -> {
      if (validator.validate()) {
        notifier.notifyObservers("Start");
      }
    });

    // Wrap the start button with a tooltip
    TooltipWrapper<Button> startButtonWrapper = new TooltipWrapper<>(startButton,
        validator.containsErrorsProperty(),
        Bindings.concat("Cannot start game:\n", validator.createStringBinding()));

    this.getChildren().clear();
    container.getChildren().addAll(player1, player2, player3, player4);
    container.setHgap(10);
    container.setVgap(10);
    container.setPrefColumns(2);

    layout.getChildren().clear();
    layout.getChildren().add(container);
    layout.getChildren().add(textFields);
    layout.setSpacing(20);
    layout.setAlignment(Pos.CENTER);

    VBox mainContainer = new VBox();
    mainContainer.setSpacing(20);
    mainContainer.setAlignment(Pos.CENTER);
    mainContainer.getChildren().addAll(layout, startButtonWrapper);
    this.getChildren().add(mainContainer);
    StackPane.setAlignment(mainContainer, Pos.CENTER);
  }

  public void setTextFields(int numberOfPlayers) {
    validator = new Validator();
    initialize();
    textFields.getChildren().clear();
    textFields.setSpacing(10);
    fields = new TextField[numberOfPlayers];
    ComboBox<GamePiece>[] gamePieceSelectors = new ComboBox[numberOfPlayers];

    for (int i = 0; i < numberOfPlayers; i++) {
      TextField textField = new TextField();
      textField.setPromptText("Player " + (i + 1) + " name");
      fields[i] = textField;

      ComboBox<GamePiece> gamePieceComboBox = new ComboBox<>();
      gamePieceComboBox.getItems().addAll(GamePiece.values());
      gamePieceComboBox.setValue(GamePiece.values()[i % GamePiece.values().length]);
      gamePieceSelectors[i] = gamePieceComboBox;

      HBox playerInputContainer = new HBox();
      playerInputContainer.setAlignment(Pos.CENTER_LEFT);
      playerInputContainer.setSpacing(10);
      playerInputContainer.getChildren().addAll(textField, gamePieceComboBox);

      textFields.getChildren().add(playerInputContainer);
    }

    for (int i = 0; i < numberOfPlayers; i++) {
      final int currentIndex = i;
      // Duplicate name check
      validator.createCheck().dependsOn("name" + i, fields[i].textProperty()).withMethod(c -> {
        String name = c.get("name" + currentIndex);
        if (name.isEmpty()) {
          c.error("Name cannot be empty");
        } else if (name.length() >= 8) {
          c.error("Name must be less than 8 characters");
        } else {
          // Duplicate name check
          for (int j = 0; j < numberOfPlayers; j++) {
            if (j != currentIndex && j < fields.length && name.equalsIgnoreCase(fields[j].getText())
                && !fields[j].getText().isEmpty()) {
              c.error("Names must be unique");
              break;
            }
          }
        }
      }).decorates(fields[i]).immediate();

      validator.createCheck().dependsOn("gamePiece" + i, gamePieceSelectors[i].valueProperty())
          .withMethod(c -> {
            GamePiece selectedGamePiece = c.get("gamePiece" + currentIndex);
            for (int j = 0; j < numberOfPlayers; j++) {
              if (j != currentIndex && selectedGamePiece.equals(gamePieceSelectors[j].getValue())) {
                c.error("Game pieces must be unique");
                break;
              }
            }
          }).decorates(gamePieceSelectors[i]).immediate();
    }

  }

  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    this.notifier = notifier;
    super.setButtonClickNotifier(notifier);
  }

  /**
   * Returns a map of player names and their selected game pieces.
   *
   * @return a map of player names and their selected game pieces
   */
  public Map<String, GamePiece> getSelectedPlayers() {
    Map<String, GamePiece> selectedPlayers = new HashMap<>();

    for (TextField field : fields) {
      String playerName = field.getText();
      HBox parentContainer = (HBox) field.getParent();
      GamePiece gamePiece = parentContainer.getChildren().stream()
          .filter(node -> node instanceof ComboBox).map(node -> (ComboBox<GamePiece>) node)
          .findFirst().map(ComboBox::getValue).orElse(null);
      selectedPlayers.put(playerName, gamePiece);
    }
    return selectedPlayers;
  }
}
