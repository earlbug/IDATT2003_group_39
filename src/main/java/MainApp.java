import IO.BoardFileReaderJson;
import interfaces.Board;
import controllers.BoardGame;
import controllers.BoardGameController;
import controllers.BoardGameNotifier;
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

  private final BoardFileReaderJson boardFileReaderJson = new BoardFileReaderJson();
  private MainMenuView mainMenuView = new MainMenuView();

  @Override
  public void start(Stage primaryStage) throws IOException, UnknownGameException {

    BoardGame boardGame = new BoardGame();
    Board board = BoardFactory.getFromFile("laddergame_1.json");
    BoardView boardView = BoardViewFactory.createBoardView(GameType.SNAKES_AND_LADDERS, board);
    GameView gameView = new GameView(boardView);
    HudView hudView = gameView.getHudView();

    Player p1 = new Player("Ola", boardGame);
    Player p2 = new Player("Per", boardGame);

    BoardGameNotifier boardGameNotifier = new BoardGameNotifier();
    BoardGameController boardGameController = new BoardGameController(boardGame, boardGameNotifier);
    ButtonClickController buttonClickController = new ButtonClickController(boardGameController, gameView);
    hudView.addButtonClickObserver(buttonClickController);

    boardGame.setBoard(board);

    boardView.registerPlayer(p1);
    //boardView.registerPlayer(p2);
    boardGame.createDice(1);
    boardGame.addPlayer(p1);
    ///boardGame.addPlayer(p2);
    boardGame.setCurrentPlayer(p1);
    boardView.createBoardView(board);
    boardView.updatePlayerView(p1);
    boardGame.addPlayersOnStartPos();

    Scene scene = new Scene(gameView, 1280, 720);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
