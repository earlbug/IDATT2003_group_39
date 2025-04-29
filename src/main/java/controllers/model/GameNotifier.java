package controllers.model;

import java.util.ArrayList;
import java.util.List;
import models.BoardGame;
import models.Player;
import observers.BoardGameObserver;

public abstract class GameNotifier {

  List<BoardGameObserver> observers = new ArrayList<>();

  public void addObserver(BoardGameObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(BoardGameObserver observer) {
    observers.remove(observer);
  };

  public void notifyPlayerMoved(Player player, int steps) {
    observers.forEach(observer -> observer.onPlayerMoved(player, steps));
  }

  public void notifyNextPlayer(Player newPlayer) {
    observers.forEach(observer -> observer.onNextPlayer(newPlayer));
  }

  public void notifyWinnerDetermined(Player winner) {
    observers.forEach(observer -> observer.onWinnerDetermined(winner));
  }

  public void notifyGameStateChanged(BoardGame boardGame) {
    observers.forEach(observer -> observer.onGameStateChanged(boardGame));
  }
}
