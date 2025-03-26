import static org.junit.jupiter.api.Assertions.assertEquals;

import controllers.BoardGame;
import models.GamePiece;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  private Player player;
  private BoardGame boardGame;

  @BeforeEach
  public void setUp(){
    boardGame = new BoardGame();
    player = new Player("player1", boardGame);
    boardGame.addPlayer(player);
    boardGame.createBoard(5);
  }

  @Test
  public void testPlaceOnTile(){
    player.placeOnTile(boardGame.getBoard().getTile(5));
    assertEquals(5, player.getCurrentTile().getTileId());
  }

  @Test
  public void testMove(){
    player.move(1);
    assertEquals(2, player.getCurrentTile().getTileId());
  }

  @Test
  public void testSettersAndGetters(){
    player.setCurrentTile(boardGame.getBoard().getTile(2));
    assertEquals(2, player.getCurrentTile().getTileId());
    player.setGamePiece(GamePiece.CAR);
    assertEquals(GamePiece.CAR, player.getGamePiece());
    player.setName("Ola");
    assertEquals("Ola", player.getName());
  }

}
