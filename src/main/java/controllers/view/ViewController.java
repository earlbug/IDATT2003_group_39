package controllers.view;

import controllers.model.GameNotifier;
import models.BoardGame;
import models.Player;
import observers.BoardGameObserver;

public class ViewController extends GameNotifier implements BoardGameObserver {

  @Override
  public void onPlayerMoved(Player player, int steps) {

  }

  @Override
  public void onNextPlayer(Player newPlayer) {

  }

  @Override
  public void onWinnerDetermined(Player winner) {

  }

  @Override
  public void onPlayerLost(Player lostPlayer) {

  }

  @Override
  public void onGameStateChanged(BoardGame boardGame) {

  }
}
