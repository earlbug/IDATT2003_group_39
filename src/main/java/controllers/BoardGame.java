package controllers;

import IO.BoardFileReaderJson;
import java.util.ArrayList;
import java.util.List;
import models.actions.LadderAction;
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
  private final Board board = new Board();
  private final List<Player> players = new ArrayList<>();
  private Player currentPlayer;
  private Dice dice;
  private final BoardFileReaderJson boardReader = new BoardFileReaderJson();

  /**
   * Adds a player to the game.
   * @param player player to be added to the game.
   */
  public void addPlayer(Player player) {
    players.add(player);
  }

  public void createBoard(Board board){
    Tile[] tiles = board.getTiles();
    for(Tile tile : tiles){
      this.board.addTile(tile);
      if(tile.getLandAction() != null) {
        this.board.getTile(tile.getTileId()).setLandAction(new LadderAction(3, "Moves the player to tile 3"));
      }
    }
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
    int currentPlayerIndex = players.indexOf(currentPlayer);
    if(currentPlayerIndex + 1 >= players.size()) {
      currentPlayer = players.getFirst();
    } else {
      currentPlayer = players.get(currentPlayerIndex  + 1);
    }
  }

  /**
   * Makes the current player do its turn.
   */
  public void playOneTurn(int stepsToMove) {
    currentPlayer.move(stepsToMove);
  }

  /**
   * Gets the player which are currently playing.
   * @return current Player
   */
  public Player getWinner() {
    return currentPlayer;
  }


  public Board getBoard(){
    return board;
  }

  public void setCurrentPlayer(Player player){
    this.currentPlayer = player;
  }

  public Player getCurrentPlayer(){
    return currentPlayer;
  }

  public Dice getDice(){
    return dice;
  }

  public void addPlayersOnStartPos() {
    for (Player player : players) {
      player.setCurrentTile(board.getTile(1));
    }
  }
}
