import IO.BoardFileReaderJson;
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

    playMonopoly(primaryStage);
  }

  public void playMonopoly(Stage primaryStage) {
    BoardGame boardGame = new BoardGame();
    Board board = null;
    try {
    board = BoardFactory.getFromFile("monopolygame_2.json");
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    BoardView boardView = BoardViewFactory.createBoardView(GameType.MONOPOLY, board);
    GameView gameView = new GameView(boardView);
    HudView hudView = gameView.getHudView();

    Player p1 = new Player("Ola", boardGame);
    p1.setColor(Color.RED);
    Player p2 = new Player("Per", boardGame);
    p2.setColor(Color.BLUE);

    GameController gameController = new GameController(boardGame);
    MonopolyViewController viewController = new MonopolyViewController(gameView);
    ButtonClickController buttonClickController = new ButtonClickController(gameController, viewController);
    viewController.setNotifiers(buttonClickController);

    boardGame.setBoard(board);

    gameController.handleAddPlayer(p1);
    gameController.handleAddPlayer(p2);
    gameController.handlePlayerIds();

    viewController.addPlayerViews(boardGame.getPlayers());
    boardGame.createDice(1);
    boardGame.setCurrentPlayer(p1);
    boardView.createBoardView(board);
    boardView.updatePlayerView(p1);
    boardView.updatePlayerView(p2);
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

    Player p1 = new Player("Ola", boardGame);
    p1.setColor(Color.RED);
    Player p2 = new Player("Per", boardGame);
    p2.setColor(Color.BLUE);

    GameController gameController = new GameController(boardGame);
    SnakesAndLaddersViewController viewController = new SnakesAndLaddersViewController(gameView);
    ButtonClickController buttonClickController = new ButtonClickController(gameController, viewController);
    viewController.setNotifiers(buttonClickController);

    boardGame.setBoard(board);

    gameController.handleAddPlayer(p1);
    gameController.handleAddPlayer(p2);
    gameController.handlePlayerIds();

    viewController.addPlayerViews(boardGame.getPlayers());
    boardGame.createDice(1);
    boardGame.setCurrentPlayer(p1);
    boardView.createBoardView(board);
    boardView.updatePlayerView(p1);
    boardView.updatePlayerView(p2);
    boardGame.addPlayersOnStartPos();

    Scene scene = new Scene(gameView, 1280, 720);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}


