import controllers.ButtonClickController;
import controllers.model.GameManager;
import controllers.view.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * <h3>Main application</h3>
 *
 * <p>This is the main application class that starts the JavaFX application.
 *
 * <p>It creates the game manager and view manager, and sets up the button click controller.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    GameManager gameManager = new GameManager();
    ViewManager viewManager = new ViewManager(primaryStage);
    ButtonClickController buttonClickController = new ButtonClickController(gameManager,
        viewManager);

    viewManager.switchToMenuView();
    viewManager.getCurrentViewController().setButtonClickNotifier(buttonClickController);
    viewManager.getCurrentViewController().showView();
  }

}
