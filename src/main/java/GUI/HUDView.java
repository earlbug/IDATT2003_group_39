package GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HUDView {
  VBox vBox = new VBox();
  Button button = new Button("Button");

  public HUDView() {
    vBox.setPrefWidth(296);
    vBox.setPrefHeight(600);
    vBox.getChildren().add(button);
  }

  public VBox getView() {
    return vBox;
  }

}
