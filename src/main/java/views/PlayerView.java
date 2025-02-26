package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class PlayerView {
  private Rectangle player;
  private double playerSize = 40;

  public PlayerView() {
    player = new Rectangle(playerSize, playerSize);
    player.setFill(Color.BLUE);
  }

  public Rectangle getPlayer() {
    return player;
  }

  public void setPosition(double x, double y) {
    player.setX(x + 10);
    player.setY(y + 10);
  }


}
