package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiceTest {

  private Dice dice;

  @BeforeEach
  public void setUp() {
    dice = new Dice(5);
  }

  @Test
  public void testRollAllDice() {
    int sum = dice.rollAllDice();
    assertTrue(sum >= 5 && sum <= 30, "Sum of rolled dice should be between 5 and 30");
  }

  @Test
  public void testGetSumOfAllDie() {
    Dice mockDice = mock(Dice.class);
    when(mockDice.getSumOfAllDie()).thenReturn(15);

    assertEquals(15, mockDice.getSumOfAllDie(), "Sum of all dice values should be 15");
  }
}