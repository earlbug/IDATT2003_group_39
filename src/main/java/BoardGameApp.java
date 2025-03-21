import controllers.BoardGame;
import controllers.PlayerReadWrite;
import java.io.IOException;
import models.GamePiece;
import models.Player;

public class BoardGameApp {

  public static void main(String[] args) {

    PlayerReadWrite playerIO = new PlayerReadWrite();
    Player player = new Player("erlendPlayer2", new BoardGame(), GamePiece.THIMBLE);
    try {
      playerIO.addPlayer(player);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
