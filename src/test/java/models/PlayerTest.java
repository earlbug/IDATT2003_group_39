package models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import interfaces.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  private Player player;
  private Tile tile1;
  private Tile tile2;

  @BeforeEach
  public void setUp() {
    Board mockBoard = mock(Board.class);
    BoardGame boardGame = new BoardGame();
    boardGame.setBoard(mockBoard);

    tile1 = new Tile(1);
    tile2 = new Tile(2);

    when(mockBoard.getTile(1)).thenReturn(tile1);
    when(mockBoard.getTile(2)).thenReturn(tile2);

    player = new Player("Player1", boardGame);
    boardGame.addPlayer(player);
  }

  @Test
  public void testPlaceOnTile() {
    player.placeOnTile(tile1);
    assertEquals(tile1, player.getCurrentTile());
  }

  @Test
  public void testMove() {
    tile1.setNextTile(tile2);
    player.placeOnTile(tile1);
    player.move(1);
    assertEquals(tile2, player.getCurrentTile());
  }

  @Test
  public void testSettersAndGetters() {
    player.setName("Ola");
    assertEquals("Ola", player.getName());

    player.setGamePiece(GamePiece.CAR);
    assertEquals(GamePiece.CAR, player.getGamePiece());

    player.setMoney(3000);
    assertEquals(3000, player.getMoney());

    player.setHasLost(true);
    assertTrue(player.hasLost());

    player.setHasWon(true);
    assertTrue(player.hasWon());
  }

  @Test
  public void testAddAndDeductMoney() {
    player.addMoney(500);
    assertEquals(2500, player.getMoney());

    player.deductMoney(1000);
    assertEquals(1500, player.getMoney());
  }

  @Test
  public void testAddTurnsToSkip() {
    player.addTurnsToSkip(2);
    assertEquals(2, player.getTurnsToSkip());
  }
}