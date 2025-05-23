package views.menu.content;

import controllers.ButtonClickNotifier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import views.menu.container.MenuView;

/**
 * <h3>Represents the view for selecting a game board</h3>
 *
 * <p>This class allows the user to select a game board for the game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 */
public class SelectBoardView extends MenuView {

  private ButtonClickNotifier notifier;

  /**
   * Constructs the view.
   */
  public SelectBoardView() {
    initialize();
  }

  /**
   * Initializes the view.
   */
  private void initialize() {
    Text title = new Text("SELECT BOARD");
    title.setFont(Font.font("Arial Black", 38));
    title.setFill(Color.WHITE);
    TextFlow titleFlow = new TextFlow(title);
    titleFlow.setPadding(new Insets(20));
    titleFlow.setMaxWidth(Region.USE_PREF_SIZE);
    titleFlow.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

    int imageSize = 300;
    Image board1Image = new Image("file:src/main/resources/images/SnL1.png");
    Button board1 = new Button();
    board1.setBackground(new Background(
        new BackgroundImage(board1Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(imageSize, imageSize, false, false, true, true))));

    Image board2Image = new Image("file:src/main/resources/images/SnL2.png");
    Button board2 = new Button();
    board2.setBackground(new Background(
        new BackgroundImage(board2Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(imageSize, imageSize, false, false, true, true))));

    Image board3Image = new Image("file:src/main/resources/images/SnL3.png");
    Button board3 = new Button();
    board3.setBackground(new Background(
        new BackgroundImage(board3Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(imageSize, imageSize, false, false, true, true))));

    board1.setPrefSize(imageSize, imageSize);
    board2.setPrefSize(imageSize, imageSize);
    board3.setPrefSize(imageSize, imageSize);

    board1.setOnAction(actionEvent -> notifier.notifyObservers("Board1"));
    board2.setOnAction(actionEvent -> notifier.notifyObservers("Board2"));
    board3.setOnAction(actionEvent -> notifier.notifyObservers("Board3"));

    Button backButton = new Button("Back");
    backButton.getStyleClass().add("exit-button");
    backButton.setOnAction(actionEvent -> notifier.notifyObservers("Menu"));

    HBox buttonContainer = new HBox();
    buttonContainer.setSpacing(20);
    buttonContainer.getChildren().addAll(board1, board2, board3);
    buttonContainer.setAlignment(Pos.CENTER);

    VBox layout = new VBox();
    layout.getChildren().addAll(titleFlow, buttonContainer, backButton);
    layout.setSpacing(20);
    layout.setAlignment(Pos.CENTER);
    this.getChildren().clear();
    this.getStyleClass().add("main-style");
    this.getChildren().addAll(layout);
  }

  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    this.notifier = notifier;
    super.setButtonClickNotifier(notifier);
  }

}
