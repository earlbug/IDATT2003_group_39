import IO.BoardFileReaderJson;
import controllers.Board;
import controllers.BoardGame;
import controllers.BoardGameHandler;
import controllers.BoardGameNotifier;
import controllers.ButtonClickHandler;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Player;
import views.BoardView;
import views.HudView;
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
  public void start(Stage primaryStage) throws IOException {

    BoardGame boardGame = new BoardGame();
    GameView gameView = new GameView();
    HudView hudView = gameView.getHudView();
    BoardView boardView = gameView.getBoardView();

    Player p1 = new Player("Ola", boardGame);
    Player p2 = new Player("Per", boardGame);

    BoardGameNotifier boardGameNotifier = new BoardGameNotifier();
    BoardGameHandler boardGameHandler = new BoardGameHandler(boardGame, boardGameNotifier);
    ButtonClickHandler buttonClickHandler = new ButtonClickHandler(boardGameHandler, gameView);
    hudView.addButtonClickObserver(buttonClickHandler);

    Board board;
    board = boardFileReaderJson.getBoard("laddergame_1.json");

    boardView.registerPlayer(p1);
    boardGame.createBoard(board);
    boardGame.createDice(1);
    boardGame.addPlayer(p1);
    boardGame.setCurrentPlayer(p1);
    boardView.createBoard(board);
    boardView.updatePlayer(p1);
    boardGame.addPlayersOnStartPos();

    Scene scene = new Scene(gameView, 1280, 720);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
