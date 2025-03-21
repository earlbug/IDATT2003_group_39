import controllers.BoardGame;
import IO.PlayerReadWrite;
import java.io.IOException;
import javafx.application.Application;
import models.GamePiece;
import models.Player;
import views.MainApp;

public class BoardGameApp {

  public static void main(String[] args) {

    Application.launch(MainApp.class, args);

    PlayerReadWrite playerIO = new PlayerReadWrite();
    Player player = new Player("erlendPlayer2", new BoardGame(), GamePiece.THIMBLE);
    try {
      playerIO.addPlayer(player);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
