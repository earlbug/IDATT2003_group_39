package views.container;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the main menu window
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */

public class MainMenuView extends Pane{

  private final HBox titleContainer;
  private final HBox title;
  private final Text titleText;

  private final VBox playersContainer;
  private final HBox playersTitle;
  private final Text playersText;
  private final Button playersButton;

  private final Button startButton;

  /*
   * Add the buttons and titles
   */
  public MainMenuView() {
    titleContainer = new HBox();
    title = new HBox();
    titleText = new Text();
    playersContainer = new VBox();
    playersTitle = new HBox();
    playersText = new Text();
    playersButton = new Button();
    startButton = new Button();

    initialize();
  }

  private void initialize(){
    title.setAlignment(Pos.CENTER);
    int titleWidth = 580;
    int titleHeight = 94;
    title.setPrefSize(titleWidth, titleHeight);
    title.setBackground(
        new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
    titleText.setFont(Font.font("Arial Black", FontWeight.BLACK, 38));
    titleText.setFill(Color.WHITE);
    titleText.setText("SNAKE AND LADDERS");
    title.getChildren().add(titleText);

    // Title container
    titleContainer.setAlignment(Pos.CENTER);
    titleContainer.prefWidthProperty().bind(this.widthProperty());
    titleContainer.setPadding(new Insets(20, 0, 0, 0));
    titleContainer.getChildren().add(title);

    this.getChildren().add(titleContainer);

    // Players
    playersTitle.setAlignment(Pos.CENTER);
    int playersWidth = 300;
    int playersHeight = 70;
    playersTitle.setPrefSize(playersWidth, playersHeight);
    playersTitle.setBackground(
        new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
    playersText.setFont(Font.font("Arial Black", FontWeight.BLACK, 30));
    playersText.setFill(Color.WHITE);
    playersText.setText("PLAYERS");
    playersTitle.getChildren().add(playersText);

    // Players container
    playersContainer.setAlignment(Pos.CENTER);
    playersContainer.getChildren().setAll(playersTitle, playersButton);

    this.getChildren().setAll(playersContainer, startButton);
  }

  /**
   * Gets the main menu view
   *
   * @return display of the main menu
   */
  public Pane getView() {
    return this;
  }
}
