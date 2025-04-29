import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import models.BoardGame;
import models.actions.LadderAction;
import models.Player;
import models.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTest {

  private Tile tile1;
  private Tile tile2;
  private BoardGame game;

  @BeforeEach
  public void setUp(){
    tile1 = new Tile(1);
    tile2 = new Tile(2);
    Player player1 = new Player("Ola", game);
  }

  @Test
  public void testSettersAndGetters(){
    tile1.setTileId(3);
    assertEquals(3, tile1.getTileId());

    tile1.setNextTile(tile2);
    assertEquals(tile1.getNextTile(), tile2);

    LadderAction ladderAction = new LadderAction(3,"Test");
    tile2.setLandAction(ladderAction);
  }

  //TODO
  @Test
  public void testLandPlayer(){

  }

  //TODO
  @Test
  public void testLeavePlayer(){

  }

  @Test
  public void testSetTileIdWithNegativeValue() {
    Tile tile = new Tile(1);
    assertThrows(IllegalArgumentException.class, () -> tile.setTileId(-1));
  }

  @Test
  public void testSetNextTileWithNull() {
    Tile tile = new Tile(1);
    assertThrows(IllegalArgumentException.class, () -> tile.setNextTile(null));
  }

  @Test
  public void testSetLandActionWithNull() {
    Tile tile = new Tile(1);
    assertThrows(IllegalArgumentException.class, () -> tile.setLandAction(null));
  }
}
