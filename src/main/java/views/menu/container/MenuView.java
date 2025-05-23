package views.menu.container;

import controllers.ButtonClickNotifier;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.validators.ArgumentValidator;
import views.menu.content.SelectBoardView;
import views.menu.content.SelectPlayersView;

/**
 * This class represents the main menu window
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */

public class MenuView extends StackPane {

  private ButtonClickNotifier notifier;
  private SelectPlayersView selectPlayersView;

  /*
   * Add the buttons and titles
   */
  public MenuView() {
    initialize();
  }

  private void initialize() {
    VBox layout = new VBox();
    Button snakesAndLaddersButton = new Button("SNAKES AND LADDERS");
    Button monopolyButton = new Button("MONOPOLY");

    snakesAndLaddersButton.getStyleClass().add("menu-button");
    monopolyButton.getStyleClass().add("menu-button");

    int titleWidth = 580;
    int titleHeight = 94;
    snakesAndLaddersButton.setPrefSize(titleWidth, titleHeight);
    snakesAndLaddersButton.setFocusTraversable(false);
    snakesAndLaddersButton.setOnAction(actionEvent -> notifier.notifyObservers("SnakesAndLadders"));

    monopolyButton.setPrefSize(titleWidth, titleHeight);
    monopolyButton.setFocusTraversable(false);
    monopolyButton.setOnAction(actionEvent -> notifier.notifyObservers("Monopoly"));

    layout.getChildren().clear();
    layout.setSpacing(20);
    layout.getChildren().addAll(snakesAndLaddersButton, monopolyButton);
    layout.setAlignment(Pos.CENTER);
    this.getChildren().clear();
    this.getStyleClass().add("main-style");
    this.getChildren().addAll(layout);

  }

  public void selectGame() {
    initialize();
  }

  public void selectSnakesAndLaddersBoards() {
    SelectBoardView selectBoardView = new SelectBoardView();
    selectBoardView.setButtonClickNotifier(notifier);
    this.getChildren().clear();
    this.getChildren().addAll(selectBoardView);
  }

  public void selectPlayers() {
    selectPlayersView = new SelectPlayersView();
    selectPlayersView.setButtonClickNotifier(notifier);
    this.getChildren().clear();
    this.getChildren().addAll(selectPlayersView);
  }

  /**
   * Sets the button click notifier that will handle button clicks
   *
   * @param notifier The notifier to handle button clicks
   */
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    ArgumentValidator.setButtonClickNotifier(notifier);
    this.notifier = notifier;
  }

  public SelectPlayersView getSelectPlayersView() {
    return selectPlayersView;
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
