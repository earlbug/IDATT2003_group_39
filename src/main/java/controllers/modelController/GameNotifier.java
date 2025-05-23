package controllers.modelController;

import java.util.ArrayList;
import java.util.List;
import models.BoardGame;
import models.Player;
import observers.BoardGameObserver;

/**
 * Abstract class for notifying observers about game events.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class GameNotifier {

  List<BoardGameObserver> observers = new ArrayList<>();

  /**
   * Adds an observer to the list.
   *
   * @param observer The observer to add
   */
  public void addObserver(BoardGameObserver observer) {
    observers.add(observer);
  }

  /**
   * Removes an observer from the list.
   *
   * @param observer The observer to remove
   */
  public void removeObserver(BoardGameObserver observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all observers that a player has moved.
   *
   * @param player The player who moved
   * @param steps  The number of steps moved
   */
  public void notifyPlayerMoved(Player player, int steps) {
    observers.forEach(observer -> observer.onPlayerMoved(player, steps));
  }

  /**
   * Notifies all observers that a player has been added.
   *
   * @param newPlayer The player who was added
   */
  public void notifyNextPlayer(Player newPlayer) {
    observers.forEach(observer -> observer.onNextPlayer(newPlayer));
  }

  /**
   * Notifies all observers that a player has won.
   *
   * @param winner The player who won
   */
  public void notifyWinnerDetermined(Player winner) {
    observers.forEach(observer -> observer.onWinnerDetermined(winner));
  }

  /**
   * Notifies all observers that a Player has lost.
   *
   * @param lostPlayer The Player which has lost.
   */
  public void notifyPlayerLost(Player lostPlayer) {
    observers.forEach(observer -> observer.onPlayerLost(lostPlayer));
  }

  /**
   * Notifies all observers that a tile action has been performed.
   *
   * @param player The player who performed the action
   */
  public void notifyTileActionPerformed(Player player) {
    observers.forEach(observer -> observer.onTileActionPerformed(player));
  }

  public void notifyTurnEnded(Player player) {
    observers.forEach(observers -> observers.onEndTurn(player));
  }

  /**
   * Notifies all observers that the game has started.
   *
   * @param boardGame The game that started
   */
  public void notifyGameStarted(BoardGame boardGame) {
    observers.forEach(observer -> observer.onGameStarted(boardGame));
  }
}
