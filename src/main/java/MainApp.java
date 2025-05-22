import controllers.view.ViewManager;
import controllers.model.GameController;
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

    GameController gameController = new GameController();
    ViewManager viewManager = new ViewManager(primaryStage);
    ButtonClickController buttonClickController = new ButtonClickController(gameController, viewManager);
    viewManager.getCurrentViewController().setButtonClickNotifier(buttonClickController);

    /*
    BoardGame boardGame = new BoardGame();
    Board board = BoardFactory.getFromFile("SnL2.json");
    BoardView boardView = BoardViewFactory.createBoardView(GameType.SNAKES_AND_LADDERS, board);
    GameView gameView = new GameView(boardView);

    Player p1 = new Player("Ola", boardGame);
    p1.setColor(Color.RED);
    Player p2 = new Player("Per", boardGame);
    p2.setColor(Color.BLUE);
    Player p3 = new Player("Kari", boardGame);
    p3.setColor(Color.GREEN);

    GameController gameController = new GameController(boardGame);
    SnakesAndLaddersViewController viewController = new SnakesAndLaddersViewController(gameView);
    ButtonClickController buttonClickController = new ButtonClickController(gameController, viewController);
    viewController.setNotifiers(buttonClickController);

    boardGame.setBoard(board);

    gameController.handleAddPlayer(p1);
    gameController.handleAddPlayer(p2);
    gameController.handleAddPlayer(p3);
    gameController.handlePlayerIds();

    viewController.addPlayerViews(boardGame.getPlayers());
    boardGame.createDice(1);
    boardGame.setCurrentPlayer(p1);
    boardView.drawPlayerView(p1);
    boardView.drawPlayerView(p2);
    boardView.drawPlayerView(p3);
    boardGame.addPlayersOnStartPos();
    */

    // Wrap gameView in a StackPane and center it


    viewManager.switchToMenuView();
  }

}
