package views;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class MainView {
  HBox root = new HBox();
  HBox boardContainer = new HBox();
  StackPane hudContainer = new StackPane();

  BoardView boardView = new BoardView();
  HUDView hudView = new HUDView();

  public MainView(){
    boardContainer.getChildren().add(boardView.getView());
    hudContainer.getChildren().add(hudView.getView());
    StackPane.setAlignment(hudView.getView(), Pos.CENTER);
    HBox.setHgrow(hudContainer, Priority.ALWAYS);
    root.getChildren().addAll(boardContainer, hudContainer);
  }

  public HBox getView(){
    return root;
  }
}
