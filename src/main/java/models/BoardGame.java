package models;

import interfaces.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>BoardGame</h3>
 *
 * <p>Represents a game (round), including methods for setting up the game, playing a turn etc.
 *
 * @author Erlend Sundsdal
 * @version 0.1.0
 * @since 0.1.0
 */
public class BoardGame {

  private Board board;
  private final List<Player> players = new ArrayList<>();
  private Player currentPlayer;
  private Dice dice;

  /**
   * Adds a player to the game.
   *
   * @param player player to be added to the game.
   */
  public void addPlayer(Player player) {
    players.add(player);
  }

  /**
   * Sets up a number of dice to be used in the game.
   *
   * @param numberOfDice how many dies to use
   */
  public void createDice(int numberOfDice) {
    this.dice = new Dice(numberOfDice);
  }

  /**
   * Passes the turn to the next player.
   */
  public void nextPlayer() {
    int currentPlayerIndex = players.indexOf(currentPlayer);
    if (currentPlayerIndex + 1 >= players.size()) {
      currentPlayer = players.getFirst();
    } else {
      currentPlayer = players.get(currentPlayerIndex + 1);
    }
  }

  public Board getBoard() {
    return board;
  }

  public void setCurrentPlayer(Player player) {
    this.currentPlayer = player;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public Dice getDice() {
    return dice;
  }

  /**
   * Sets the starting position for all players to the first tile on the board.
   */
  public void addPlayersOnStartPos() {
    if (board == null || board.getTile(1) == null) {
      throw new IllegalStateException("Board or starting tile is not initialized.");
    }

    for (Player player : players) {
      player.setCurrentTile(board.getTile(1));
    }
  }

  /**
   * Sets the player IDs for all players in the game. The IDs are set to the index of the player in
   * the players list.
   */
  public void setPlayerIds() {
    int id = 0;
    for (Player player : players) {
      player.setPlayerId(id++);
    }
  }

  /**
   * Removes a player from the game. The player will then no longer exist in the game, and wil have
   * no information about it.
   *
   * @param player Player to remove.
   */
  public void removePlayer(Player player) {
    players.remove(player);
  }

  public List<Player> getPlayers() {
    return players;
  }
}
