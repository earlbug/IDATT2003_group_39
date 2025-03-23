package controllers;

import java.util.ArrayList;
import models.Player;
import models.Tile;

/**
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 *
 * Represents a game (round), including methods for setting up the game, playing a turn etc.
 *
 */
public class BoardGame {
  private Board board;
  private ArrayList<Player> playerList;
  private Player currentPlayer;
  private Dice dice;

  public BoardGame() {
    playerList = new ArrayList<>();
  }
  /**
   * Adds a player to the game.
   * @param player player to be added to the game.
   */
  public void addPlayer(Player player) {
    playerList.add(player);
  }

  /**
   * Sets up a board from a JSON-file
   */
  public void setUpBoard() {
    // Get hard-coded board from JSON file via serializing-class
  }

  /**
   * Sets up a number of dice to be used in the game.
   * @param numberOfDice how many dies to use
   */
  public void createDice(int numberOfDice) {
    this.dice = new Dice(numberOfDice);
  }

  /**
   * Passes the turn to the next player.
   */
  public void nextPlayer() {
    int currentPlayerIndex = playerList.indexOf(currentPlayer);
    if(currentPlayerIndex + 1 >= playerList.size()) {
      currentPlayer = playerList.getFirst();
    } else {
      currentPlayer = playerList.get(currentPlayerIndex  + 1);
    }
  }

  /**
   * Makes the current player do its turn.
   */
  public void playOneTurn() {
    int stepsToMove = dice.rollAllDice();
    currentPlayer.move(stepsToMove);
    nextPlayer();
  }

  /**
   * Gets the player which are currently playing.
   * @return current Player
   */
  public Player getWinner() {
    return currentPlayer;
  }

}
