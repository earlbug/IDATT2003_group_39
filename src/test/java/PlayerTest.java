import static org.junit.jupiter.api.Assertions.assertEquals;

import controllers.BoardGame;
import models.GamePiece;
import models.Player;
import models.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  private Player player;
  private BoardGame boardGame = new BoardGame();
  private Tile tile1;
  private Tile tile2;

  @BeforeEach
  public void setUp(){
    tile1 = new Tile(1);
    tile2 = new Tile(2);
    player = new Player("player1", boardGame);
    boardGame.addPlayer(player);
    boardGame.getBoard().addTile(tile1);
    boardGame.getBoard().addTile(tile2);
  }

  @Test
  public void testPlaceOnTile(){
    player.placeOnTile(tile1);
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
