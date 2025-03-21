import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.container.MainView;

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
    MainView mainView = new MainView();
    root.setCenter(mainView.getView());

    /*
    * Setting up the scene and stage.
    */
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    primaryStage.setTitle("JavaFX Multi-View Application");
    primaryStage.setScene(scene);
    primaryStage.sizeToScene();
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}