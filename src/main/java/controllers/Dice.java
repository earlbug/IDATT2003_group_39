package controllers;
import java.util.List;
import models.Die;

import java.util.ArrayList;

/**
 * @version 0.1
 * @since 0.1
 * @author Erlend Sundsdal
 */
public class Dice {
  // ArrayList of Dice
  private List<Die> dice;

  /**
   * Constructor Creates a given number of dice, and stores it in an ArrayList
   * @param numberOfDice how many dice to be made
   */
  public Dice(int numberOfDice){
    dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  /**
   * Rolls all the dice
   * @return the sum of all rolled Dice
   */
  public int rollAllDice(){
    int sumRolledDice = 0;
    for (Die die : dice) {
      sumRolledDice += die.roll();
    }
    return sumRolledDice;
  }

  public int getSumOfAllDie() {
    int sum = 0;
    for (Die die : dice) {
      sum += die.getValue();
    }
    return sum;
  }
}