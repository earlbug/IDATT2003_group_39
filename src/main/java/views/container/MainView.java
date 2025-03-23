package views.container;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import views.BoardView;
import views.HUDView;

public class MainView extends HBox{
  private final HBox boardContainer;
  private final StackPane hudContainer;

  private final BoardView boardView;
  private final HUDView hudView;

  public MainView(){
    boardContainer = new HBox();
    hudContainer = new StackPane();

    this.boardView = new BoardView(this);
    this.hudView = new HUDView(this, boardView);
  }

  public HBox getView(){
    boardContainer.getChildren().add(boardView.getView());
    hudContainer.getChildren().add(hudView.getView());
    StackPane.setAlignment(hudView.getView(), Pos.CENTER);
    HBox.setHgrow(hudContainer, Priority.ALWAYS);

    this.getChildren().setAll(boardContainer, hudContainer);
    return this;
  }
}
