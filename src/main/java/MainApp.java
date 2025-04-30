import IO.BoardFileReaderJson;
import controllers.model.SnakesAndLaddersController;
import controllers.view.SnakesAndLaddersViewController;
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
import views.content.HudView;
import views.container.GameView;
import views.container.MainMenuView;

/**
 * This class represents the main application.
 *
 * @author Tord Fosse
 * @version 1.0
 * @since 1.0
 */
public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException, UnknownGameException {

    BoardGame boardGame = new BoardGame();
    Board board = BoardFactory.getFromFile("laddergame_1.json");
    BoardView boardView = BoardViewFactory.createBoardView(GameType.SNAKES_AND_LADDERS, board);
    GameView gameView = new GameView(boardView);
    HudView hudView = gameView.getHudView();

    Player p1 = new Player("Ola", boardGame);
    p1.setColor(Color.RED);
    Player p2 = new Player("Per", boardGame);
    p2.setColor(Color.BLUE);

    GameController gameController = new SnakesAndLaddersController(boardGame);
    SnakesAndLaddersViewController boardGameViewController = new SnakesAndLaddersViewController(gameView);
    ButtonClickController buttonClickController = new ButtonClickController(gameController, gameView);
    hudView.addButtonClickObserver(buttonClickController);

    boardGame.setBoard(board);

    gameController.handleAddPlayer(p1);
    gameController.handleAddPlayer(p2);
    gameController.handlePlayerIds();

    boardGameViewController.addPlayerViews(boardGame.getPlayers());
    //boardView.registerPlayer(p2);
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
