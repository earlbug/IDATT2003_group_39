package controllers.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

  private final ViewController menuViewController;
  private final ViewController monopolyViewController;
  private final ViewController snakesAndLaddersViewController;

  private ViewController currentViewController;
  private final Stage primaryStage;

  public ViewManager(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.menuViewController = new MenuViewController();
    this.monopolyViewController = new MonopolyViewController();
    this.snakesAndLaddersViewController = new SnakesAndLaddersViewController();
    this.currentViewController = menuViewController; // Start with the menu view
    initializeView(menuViewController);
  }

  public ViewController getCurrentViewController() {
    return currentViewController;
  }

  public void switchToMenuView() {
    switchToView(menuViewController);
  }

  public void switchToMonopolyView() {
    switchToView(monopolyViewController);
  }

  public void switchToSnakesAndLaddersView() {
    switchToView(snakesAndLaddersViewController);
  }

  private void switchToView(ViewController newViewController) {
    if (currentViewController != null) {
      primaryStage.getScene().setRoot(newViewController.getRootPane());
    }
    currentViewController = newViewController;
  }

  private void initializeView(ViewController initialViewController) {
    Scene scene = new Scene(initialViewController.getRootPane(), 1000, 800);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.setMinHeight(800);
    primaryStage.setMinWidth(1000);
    primaryStage.show();
  }
}
