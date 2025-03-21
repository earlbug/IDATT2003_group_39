package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Player;


public class PlayerView extends Rectangle{

  private final Color color;

  public PlayerView(Player player, Color color) {
    this.color = color;

    initialize();
  }

  private void initialize(){
    double playerSize = 40;
    this.setHeight(playerSize);
    this.setWidth(playerSize);
    this.setFill(color);
    this.getStyleClass().add("player-avatar");
  }

  public Rectangle getView(){
    return this;
  }
}
