import IO.BoardFileReaderJson;
import controllers.model.MonopolyController;
import controllers.model.SnakesAndLaddersController;
import controllers.view.MonopolyViewController;
import controllers.view.SnakesAndLaddersViewController;
import controllers.view.ViewController;
import interfaces.Board;
import javafx.scene.paint.Color;
import models.BoardGame;
import controllers.model.GameController;
import controllers.ButtonClickController;
import exception.UnknownGameException;
import factory.BoardFactory;
import factory.GameType;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Player;
import factory.BoardViewFactory;
import interfaces.BoardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.content.HudView;
import views.container.GameView;

/**
 * This class represents the main application.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class MainApp extends Application {

  private static final Logger log = LoggerFactory.getLogger(MainApp.class);

  @Override
  public void start(Stage primaryStage) throws IOException, UnknownGameException {

    playSnakesAndLadders(primaryStage);
    //playMonopoly(primaryStage);
  }

  public void playMonopoly(Stage primaryStage) {
    BoardGame boardGame = new BoardGame();
    Board board = null;
    try {
    board = BoardFactory.getFromFile("monopolygame_1.json");
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    BoardView boardView = BoardViewFactory.createBoardView(GameType.MONOPOLY, board);
    GameView gameView = new GameView(boardView);
    HudView hudView = gameView.getHudView();

    Player p1 = new Player("Are", boardGame);
    p1.setColor(Color.RED);
    Player p2 = new Player("Beathe", boardGame);
    p2.setColor(Color.BLUE);
    Player p3 = new Player("Carl", boardGame);
    p3.setColor(Color.YELLOW);
    Player p4 = new Player("Daniel", boardGame);
    p4.setColor(Color.GREEN);

    MonopolyController monopolyController = new MonopolyController(boardGame);
    MonopolyViewController viewController = new MonopolyViewController(gameView);
    ButtonClickController buttonClickController = new ButtonClickController(monopolyController, viewController);
    viewController.setNotifiers(buttonClickController);

    boardGame.setBoard(board);

    monopolyController.handleAddPlayer(p1);
    monopolyController.handleAddPlayer(p2);
    monopolyController.handleAddPlayer(p3);
    monopolyController.handleAddPlayer(p4);
    monopolyController.handlePlayerIds();

    viewController.addPlayerViews(boardGame.getPlayers());
    boardGame.createDice(1);
    boardGame.setCurrentPlayer(p1);
    boardView.createBoardView(board);
    boardView.updatePlayerView(p1);
    boardView.updatePlayerView(p2);
    boardView.updatePlayerView(p3);
    boardView.updatePlayerView(p4);
    boardGame.addPlayersOnStartPos();

    Scene scene = new Scene(gameView, 1280, 720);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public void playSnakesAndLadders(Stage primaryStage) {
    BoardGame boardGame = new BoardGame();
    Board board = null;
    try {
      board = BoardFactory.getFromFile("laddergame_1.json");
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    BoardView boardView = BoardViewFactory.createBoardView(GameType.SNAKES_AND_LADDERS, board);
    GameView gameView = new GameView(boardView);
    HudView hudView = gameView.getHudView();

    Player p1 = new Player("Are", boardGame);
    p1.setColor(Color.RED);
    Player p2 = new Player("Beathe", boardGame);
    p2.setColor(Color.BLUE);
    Player p3 = new Player("Carl", boardGame);
    p3.setColor(Color.YELLOW);
    Player p4 = new Player("Daniel", boardGame);
    p4.setColor(Color.GREEN);


    SnakesAndLaddersController snakesAndLaddersController = new SnakesAndLaddersController(boardGame);
    SnakesAndLaddersViewController viewController = new SnakesAndLaddersViewController(gameView);
    ButtonClickController buttonClickController = new ButtonClickController(snakesAndLaddersController, viewController);
    viewController.setNotifiers(buttonClickController);

    boardGame.setBoard(board);

    snakesAndLaddersController.handleAddPlayer(p1);
    snakesAndLaddersController.handleAddPlayer(p2);
    snakesAndLaddersController.handleAddPlayer(p3);
    snakesAndLaddersController.handleAddPlayer(p4);
    snakesAndLaddersController.handlePlayerIds();

    viewController.addPlayerViews(boardGame.getPlayers());
    boardGame.createDice(1);
    boardGame.setCurrentPlayer(p1);
    boardView.createBoardView(board);
    boardView.updatePlayerView(p1);
    boardView.updatePlayerView(p2);
    boardView.updatePlayerView(p3);
    boardView.updatePlayerView(p4);
    boardGame.addPlayersOnStartPos();

    Scene scene = new Scene(gameView, 1280, 720);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}


