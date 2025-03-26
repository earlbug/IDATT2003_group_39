import static org.junit.jupiter.api.Assertions.assertEquals;

import controllers.Board;
import models.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

  private Board board;

  @BeforeEach
  public void setUp(){
    board = new Board();
  }

  @Test
  public void testAddTile(){
    Tile tile = new Tile(1);
    board.addTile(tile);
    assertEquals(tile, board.getTile(1));
  }

  @Test
  public void testGetTile(){
    Tile tile1 = new Tile(1);
    Tile tile2 = new Tile(2);
    board.addTile(tile1);
    board.addTile(tile2);
    assertEquals(tile1, board.getTile(1));
    assertEquals(tile2, board.getTile(2));
  }

}
