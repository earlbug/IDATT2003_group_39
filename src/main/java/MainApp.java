import IO.BoardFileReaderJson;
import controllers.Board;
import controllers.BoardGame;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Player;
import views.BoardView;
import views.HUDView;
import views.container.GameView;
import views.container.MainMenuView;

public class MainApp extends Application {

  private BoardGame boardGame = new BoardGame();
  private final BoardFileReaderJson boardFileReaderJson = new BoardFileReaderJson();

  private GameView gameView = new GameView();
  private MainMenuView mainMenuView = new MainMenuView();
  private BoardView boardView;
  private HUDView hudView;

  private Player p1 = new Player("Ola", boardGame);
  private Player p2 = new Player("Per", boardGame);

  @Override
  public void start(Stage primaryStage) throws IOException {

    Board board;
    board = boardFileReaderJson.getBoard("laddergame_1.json");

    boardGame.createBoard(board);
    boardView = new BoardView(gameView, boardGame.getBoard());
    hudView = new HUDView(boardView, boardGame);
    boardGame.createDice(1);
    boardGame.addPlayer(p1);
    boardGame.setCurrentPlayer(p1);
    boardView.getView();
    boardView.updatePlayer(p1);
    gameView.getChildren().setAll(boardView, hudView);

    Scene scene = new Scene(gameView, 1280, 720);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
