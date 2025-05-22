package views.content;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ActionTileView extends Rectangle {


  public ActionTileView() {
    initialize();
  }

  private void initialize() {
    this.setHeight(60);
    this.setWidth(60);
  }

  public void setBackground(String fileName) {
    Image image = new Image("resources/data/actionTileImages/" + fileName + ".png");
    ImagePattern pattern = new ImagePattern(image);
    this.setFill(pattern);
  }

  public Rectangle getView() {
    return this;
  }
}
