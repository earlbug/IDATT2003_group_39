package views.game.content;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerInfoView extends VBox {

  private final VBox infoContainer;

  private final Text playerName;
  private final Text playerTile;
  private final Text gamePiece;
  private final Text money;
  private final Text turnsToSkip;

  public PlayerInfoView() {
    infoContainer = new VBox();
    playerName = new Text("Player:");
    playerTile = new Text("Tile:");
    gamePiece = new Text("Piece:");
    money = new Text("Money:");
    turnsToSkip = new Text("Turns to skip:");

    playerName.getStyleClass().add("player-title");
    playerTile.getStyleClass().add("player-title");
    gamePiece.getStyleClass().add("player-title");
    money.getStyleClass().add("player-title");
    turnsToSkip.getStyleClass().add("player-title");


    infoContainer.getStyleClass().add("player-info");
    infoContainer.setSpacing(20);
    infoContainer.getChildren().addAll(playerName, playerTile, gamePiece, money, turnsToSkip);
    this.setPadding(new Insets(10));
    this.getChildren().add(infoContainer);
  }

  public void setPlayerName(String name) {
    playerName.setText("Player: " + name);
  }

  public void setPlayerTile(String tileId) {
    playerTile.setText("Tile: " + tileId);
  }

  public void setGamePiece(String gamePiece) {
    this.gamePiece.setText("Piece: " + gamePiece);
  }

  public void setMoney(String money) {
    this.money.setText("Money: " + money);
  }

  public void setTurnsToSkip(String turns){
    turnsToSkip.setText("Turns to skip: " + turns);
  }

}
