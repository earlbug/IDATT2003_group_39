package controllers.viewController;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ViewManager {

  private final MenuViewController menuViewController;
  private final MonopolyViewController monopolyViewController;
  private final SnakesAndLaddersViewController snakesAndLaddersViewController;

  private ViewController currentViewController;
  private final Stage primaryStage;
  private final StackPane rootStackPane = new StackPane();

  public ViewManager(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.menuViewController = new MenuViewController();
    this.monopolyViewController = new MonopolyViewController();
    this.snakesAndLaddersViewController = new SnakesAndLaddersViewController();
    this.currentViewController = menuViewController;
    initializeView();
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
    for (Node child : rootStackPane.getChildren()) {
      child.setVisible(false);
    }

    newViewController.getRootPane().setVisible(true);
    currentViewController = newViewController;
  }


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
