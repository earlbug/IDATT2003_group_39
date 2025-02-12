package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class represents the main application.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    /*
    * Create the root layout and add different views to it.
    */
    BorderPane root = new BorderPane();

    /*
    * Add the board view to the top of the root layout.
    */
    BoardView boardView = new BoardView();
    root.setCenter(boardView.getView());
    root.setRight(new HUDView().getView());
    root.setStyle("-fx-background-color: #b8f1ff;");

    /*
    * Setting up the scene and stage.
    */
    Scene scene = new Scene(root, 800, 600);
    primaryStage.setTitle("JavaFX Multi-View Application");
    primaryStage.setScene(scene);
    primaryStage.sizeToScene();
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}