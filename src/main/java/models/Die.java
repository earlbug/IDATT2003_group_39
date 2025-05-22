package models;

import java.util.Random;

/**
 * @version 0.1
 * @since 0.1
 * @author Erlend Sundsdal, Tord Fosse
 */
public class Die {
  private int lastRolledValue;
  private final Random random = new Random();

  /**
   * Sets <code>lastRolledValue</code> to a number between 1 and 6
   * @return a number from 1 to 6
   */
  public int roll(){
    int dieMinValue = 1;
    int dieMaxValue = 6;
    int numberRolled = random.nextInt(dieMinValue, dieMaxValue);
    lastRolledValue = numberRolled;
    return numberRolled;
  }

  public int getValue() {
    return lastRolledValue;
  }
}
