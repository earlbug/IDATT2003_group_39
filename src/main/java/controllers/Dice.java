package controllers;
import models.Die;

import java.util.ArrayList;

/**
 * @version 0.1
 * @since 0.1
 * @author Erlend Sundsdal
 */
public class Dice {
  // ArrayList of Dice
  private ArrayList<Die> dice;

  /**
   * Constructor Creates a given number of dice, and stores it in an ArrayList
   * @param numberOfDice how many dice to be made
   */
  public Dice(int numberOfDice){
    for (int i = 0; i < numberOfDice; i++) {
      addDie(new Die());
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

  public int getDieValue(int dieIndex) {
    // validate parameter here
    return dice.get(dieIndex).getValue();
  }

  private void addDie(Die die){
    // Add exception handling here
    dice.add(die);
  }
}