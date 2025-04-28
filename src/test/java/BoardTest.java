import static org.junit.jupiter.api.Assertions.assertEquals;

import interfaces.Board;
import models.Tile;
import models.boards.SnakesAndLaddersBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

  private Board board;

  @BeforeEach
  public void setUp(){
    board = new SnakesAndLaddersBoard();
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
