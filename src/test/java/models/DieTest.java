package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DieTest {

  @Test
  public void testRoll() {
    Die die = new Die();
    int rollValue = die.roll();
    assertTrue(rollValue >= 1 && rollValue <= 6, "Die roll should be between 1 and 6");
  }

  @Test
  public void testGetValue() {
    Die die = new Die();
    die.roll();
    int value = die.getValue();
    assertTrue(value >= 1 && value <= 6, "Die value should be between 1 and 6");
  }
}