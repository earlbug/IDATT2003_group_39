package views.game.content;

import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerInfoView extends VBox {

  private final Text playerName;
  private final Text playerTile;
  private final Text gamePiece;
  private final Text money;
  private final Text turnsToSkip;

  public PlayerInfoView() {
    VBox infoContainer = new VBox();
    Region spacer = new Region();
    playerName = new Text();
    playerTile = new Text();
    gamePiece = new Text();
    money = new Text();
    turnsToSkip = new Text();

    playerName.getStyleClass().add("player-title");
    playerTile.getStyleClass().add("player-title");
    gamePiece.getStyleClass().add("player-title");
    money.getStyleClass().add("player-title");
    turnsToSkip.getStyleClass().add("player-title");


    infoContainer.getStyleClass().add("player-info");
    infoContainer.setSpacing(20);
    infoContainer.setPrefSize(300, 100);
    infoContainer.getChildren().addAll(playerName, playerTile, gamePiece, money, turnsToSkip);

    VBox.setVgrow(spacer, Priority.ALWAYS);

    this.getStyleClass().add("game-info-container");
    this.setPadding(new Insets(10));
    this.getChildren().addAll(infoContainer, spacer);
  }

  public void setPlayerName(String text) {
    playerName.setText(text);
  }

  public void setPlayerTile(String text) {
    playerTile.setText(text);
  }

  public void setGamePiece(String text) {
    this.gamePiece.setText(text);
  }

  public void setMoney(String text) {
    this.money.setText(text);
  }

  public void setTurnsToSkip(String text){
    turnsToSkip.setText(text);
  }

}
