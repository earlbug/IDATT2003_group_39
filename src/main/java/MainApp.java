import controllers.view.SnakesAndLaddersViewController;
import interfaces.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
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
    Board board = BoardFactory.getFromFile("SnL3.json");
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

    // Wrap gameView in a StackPane and center it
    StackPane root = new StackPane(gameView);
    gameView.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(50));

    Scene scene = new Scene(root, 1000, 800);
    scene.getStylesheets().add("styles.css");
    primaryStage.setTitle("Game");
    primaryStage.setScene(scene);
    primaryStage.setMinHeight(800);
    primaryStage.setMinWidth(1000);
    primaryStage.show();
  }

}
