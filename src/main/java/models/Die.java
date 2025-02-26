package models;

import java.util.Random;

/**
 * @version 0.1
 * @since 0.1
 * @author Erlend Sundsdal
 */
public class Die {
  private int lastRolledValue;
  private Random random = new Random();

  public Die(){}

  /**
   * Sets <code>lastRolledValue</code> to a number between 1 and 6
   * @return a number from 1 to 6
   */
  public int roll(){
    int dieMinValue = 1;
    int dieMaxValue = 6;
    int numberRolled = random.nextInt(dieMaxValue - dieMinValue + 1) + dieMinValue;
    setLastRolledValue(numberRolled);
    return numberRolled;
  }

  public void setLastRolledValue(int lastRolledValue) {
    this.lastRolledValue = lastRolledValue;
  }

  public int getValue() {
    return lastRolledValue;
  }
}
