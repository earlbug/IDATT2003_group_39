package controllers.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Manages the different view controllers in the game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 */
public class ViewManager {

  private final MenuViewController menuViewController;
  private final MonopolyViewController monopolyViewController;
  private final SnakesAndLaddersViewController snakesAndLaddersViewController;

  private ViewController currentViewController;
  private final Stage primaryStage;
  private final StackPane rootStackPane = new StackPane();

  /**
   * Constructor for ViewManager.
   *
   * @param primaryStage the primary stage of the application
   */
  public ViewManager(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.menuViewController = new MenuViewController();
    this.monopolyViewController = new MonopolyViewController();
    this.snakesAndLaddersViewController = new SnakesAndLaddersViewController();
    this.currentViewController = menuViewController;
    initializeView();
  }

  /**
   * Gets the current view controller.
   *
   * @return the current view controller
   */
  public ViewController getCurrentViewController() {
    return currentViewController;
  }

  /**
   * Switches to the menu view.
   */
  public void switchToMenuView() {
    switchToView(menuViewController);
  }

  /**
   * Switches to the Monopoly view.
   */
  public void switchToMonopolyView() {
    switchToView(monopolyViewController);
  }

  /**
   * Switches to the Snakes and Ladders view.
   */
  public void switchToSnakesAndLaddersView() {
    switchToView(snakesAndLaddersViewController);
  }

  /**
   * Switches to the selected view controller.
   *
   * @param newViewController the new view controller to switch to
   */
  private void switchToView(ViewController newViewController) {
    for (Node child : rootStackPane.getChildren()) {
      child.setVisible(false);
    }

    newViewController.getRootPane().setVisible(true);
    currentViewController = newViewController;
  }

  /**
   * Initializes the view by adding the menu and game views to the root stack pane.
   */
  private void initializeView() {
    rootStackPane.getChildren()
        .addAll(menuViewController.getRootPane(), monopolyViewController.getRootPane(),
            snakesAndLaddersViewController.getRootPane());

    Scene scene = new Scene(rootStackPane);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.setMinHeight(800);
    primaryStage.setMinWidth(1200);
    primaryStage.setMaximized(true);
    primaryStage.show();
  }
}
