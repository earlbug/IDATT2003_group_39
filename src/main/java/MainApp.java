import controllers.model.GameManager;
import controllers.view.ViewManager;
import controllers.ButtonClickController;
import javafx.application.Application;
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
    GameManager gameManager = new GameManager();
    ViewManager viewManager = new ViewManager(primaryStage);
    ButtonClickController buttonClickController = new ButtonClickController(gameManager, viewManager);
    viewManager.getCurrentViewController().setButtonClickNotifier(buttonClickController);

    viewManager.switchToMenuView();
  }

}
