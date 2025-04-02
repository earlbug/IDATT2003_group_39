package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Player;
import observers.BoardGameObserver;

/**
 * Handles notifications to observers about game state changes.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class BoardGameNotifier {

  private final List<BoardGameObserver> observers = new ArrayList<>();

  /**
   * Add an observer to be notified of game state changes.
   *
   * @param observer The observer to add
   */
  public void addObserver(BoardGameObserver observer) {
    observers.add(observer);
  }

  /**
   * Remove an observer from notification list.
   *
   * @param observer The observer to remove
   */
  public void removeObserver(BoardGameObserver observer) {
    observers.remove(observer);
  }

  /**
   * Notifies observers that a player has moved.
   *
   * @param player The player who moved
   * @param steps  Number of steps moved
   */
  public void nofifyPlayerMoved(Player player, int steps) {
    for (BoardGameObserver observer : observers) {
      observer.onPlayerMoved(player, steps);
    }
  }

  /**
   * Notifies observers about player turn change.
   *
   * @param player The new current player
   */
  public void notifyNextPlayer(Player player) {
    for (BoardGameObserver observer : observers) {
      observer.onNextPlayer(player);
    }
  }

  /**
   * Notifies observers about a winner.
   *
   * @param winner The winning player
   */
  public void nofityWinnerDetermined(Player winner) {
    for (BoardGameObserver observer : observers) {
      observer.onWinnerDetermined(winner);
    }
  }

  /**
   * Notifies observers about general game state changes.
   *
   * @param boardGame The board game instance
   */
  public void nofifyGameStateChanged(BoardGame boardGame) {
    for (BoardGameObserver observer : observers) {
      observer.onGameStateChanged(boardGame);
    }
  }

}
