package interfaces;

import javafx.scene.layout.Pane;
import models.Player;

public interface BoardView {
  void createBoardView(Board board);
  void registerPlayer(Player player);
  void updatePlayerView(Player player);
  Pane getView();
}