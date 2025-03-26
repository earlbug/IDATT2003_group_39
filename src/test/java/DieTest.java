import static org.junit.jupiter.api.Assertions.assertTrue;

import models.Die;
import org.junit.jupiter.api.Test;

public class DieTest {

  Die die1 = new Die();

  @Test
  public void testRoll(){
    int rollValue = die1.roll();
    assertTrue(rollValue >= 1 && rollValue <= 6, "Die roll should be between 1 and 6");
  }

  @Test
  public void testGetters(){
    die1.roll();
    int value = die1.getValue();
    assertTrue(value >= 1 && value <= 6, "Die value should be between 1 and 6");

  }

}
