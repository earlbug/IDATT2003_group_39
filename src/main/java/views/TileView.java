package views;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Tile;

public class TileView extends Rectangle{

  public TileView(Tile tile){
    initialize();
  }

  private void initialize(){
    this.setWidth(60);
    this.setHeight(60);
    this.setFill(Color.ALICEBLUE);
    this.setStroke(Color.BLACK);
  }

  public Rectangle getView(){
    return this;
  }

}
