package models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import interfaces.TileAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTest {

  private Tile tile1;
  private Tile tile2;
  private TileAction mockAction;

  @BeforeEach
  public void setUp() {
    tile1 = new Tile(1);
    tile2 = new Tile(2);
    mockAction = mock(TileAction.class);
  }

  @Test
  public void testSetAndGetTileId() {
    tile1.setTileId(3);
    assertEquals(3, tile1.getTileId());
  }

  @Test
  public void testSetAndGetNextTile() {
    tile1.setNextTile(tile2);
    assertEquals(tile2, tile1.getNextTile());
  }

  @Test
  public void testSetAndGetLandAction() {
    tile1.setLandAction(mockAction);
    assertEquals(mockAction, tile1.getLandAction());
  }

  @Test
  public void testSetTileIdWithInvalidValue() {
    assertThrows(IllegalArgumentException.class, () -> tile1.setTileId(-1));
  }

  @Test
  public void testSetLandActionWithNull() {
    assertThrows(IllegalArgumentException.class, () -> tile1.setLandAction(null));
  }
}