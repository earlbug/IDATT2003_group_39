package models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import interfaces.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BoardGameTest {

  @InjectMocks
  private BoardGame boardGame;

  @Mock
  private Board mockBoard;

  private Player player1;
  private Player player2;

  @BeforeEach
  public void setUp() {
    player1 = new Player("Player1", boardGame);
    player2 = new Player("Player2", boardGame);

    boardGame.addPlayer(player1);
    boardGame.addPlayer(player2);
  }

  @Test
  public void testAddPlayer() {
    List<Player> players = boardGame.getPlayers();
    assertEquals(2, players.size());
    assertTrue(players.contains(player1));
    assertTrue(players.contains(player2));
  }

  @Test
  public void testSetBoard() {
    boardGame.setBoard(mockBoard);
    assertEquals(mockBoard, boardGame.getBoard());
  }

  @Test
  public void testAddPlayersOnStartPos() {
    Tile startTile = new Tile(1);
    when(mockBoard.getTile(1)).thenReturn(startTile);

    boardGame.addPlayersOnStartPos();

    assertEquals(startTile, player1.getCurrentTile());
    assertEquals(startTile, player2.getCurrentTile());
  }
}