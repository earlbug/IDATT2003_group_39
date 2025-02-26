package views;

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

public class MainMenuView {

  private Pane display = new Pane();
  private HBox titleContainer = new HBox();
  private HBox title = new HBox();
  private Text titleText = new Text();

  private VBox playersContainer = new VBox();
  private HBox playersTitle = new HBox();
  private Text playersText = new Text();
  private Button playersButton = new Button();

  private Button startButton = new Button();

  private final int TITLE_WIDTH = 580;
  private final int TITLE_HEIGHT = 94;
  private final int PLAYERS_WIDTH = 300;
  private final int PLAYERS_HEIGHT = 70;

  /*
   * Add the buttons and titles
   */
  public MainMenuView() {

    // Title
    title.setAlignment(Pos.CENTER);
    title.setPrefSize(TITLE_WIDTH, TITLE_HEIGHT);
    title.setBackground(
        new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
    titleText.setFont(Font.font("Arial Black", FontWeight.BLACK, 38));
    titleText.setFill(Color.WHITE);
    titleText.setText("SNAKE AND LADDERS");
    title.getChildren().add(titleText);

    // Title container
    titleContainer.setAlignment(Pos.CENTER);
    titleContainer.prefWidthProperty().bind(display.widthProperty());
    titleContainer.setPadding(new Insets(20, 0, 0, 0));
    titleContainer.getChildren().add(title);

    display.getChildren().add(titleContainer);

    // Players
    playersTitle.setAlignment(Pos.CENTER);
    playersTitle.setPrefSize(PLAYERS_WIDTH, PLAYERS_HEIGHT);
    playersTitle.setBackground(
        new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
    playersText.setFont(Font.font("Arial Black", FontWeight.BLACK, 30));
    playersText.setFill(Color.WHITE);
    playersText.setText("PLAYERS");
    playersTitle.getChildren().add(playersText);

    // Players container
    playersContainer.setAlignment(Pos.CENTER);
    playersContainer.getChildren().add(playersTitle);
    playersContainer.getChildren().add(playersButton);

    display.getChildren().add(playersContainer);
    display.getChildren().add(startButton);
  }

  /**
   * Gets the main menu view
   *
   * @return display of the main menu
   */
  public Pane getView() {
    return display;
  }

}
