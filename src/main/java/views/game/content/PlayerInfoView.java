package views.game.content;

import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * <h3>Represents the view of the player info</h3>
 *
 * <p>Contains the view of the player info.
 *
 * @author Tord Fosse
 * @version 1.0.0
 */
public class PlayerInfoView extends VBox {

  private final Text playerName;
  private final Text playerTile;
  private final Text gamePiece;
  private final Text money;
  private final Text turnsToSkip;

  /**
   * Constructs a new view of the player info.
   *
   * <p>Creates a VBox to hold the player info.
   */
  public PlayerInfoView() {
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

    VBox infoContainer = new VBox();
    infoContainer.getStyleClass().add("player-info");
    infoContainer.setSpacing(20);
    infoContainer.setPrefSize(300, 100);
    infoContainer.getChildren().addAll(playerName, playerTile, gamePiece, money, turnsToSkip);

    Region spacer = new Region();
    VBox.setVgrow(spacer, Priority.ALWAYS);

    this.getStyleClass().add("game-info-container");
    this.setPadding(new Insets(10));
    this.getChildren().addAll(infoContainer, spacer);
  }

  /**
   * Sets the player name.
   *
   * @param text the name to set
   */
  public void setPlayerName(String text) {
    playerName.setText(text);
  }

  /**
   * Sets the player tile.
   *
   * @param text the tile to set
   */
  public void setPlayerTile(String text) {
    playerTile.setText(text);
  }

  /**
   * Sets the game piece.
   *
   * @param text the game piece to set
   */
  public void setGamePiece(String text) {
    this.gamePiece.setText(text);
  }

  /**
   * Sets the amount of money the player has.
   *
   * @param text the amount of money to set
   */
  public void setMoney(String text) {
    this.money.setText(text);
  }

  /**
   * Sets the amount of turns the player has to skip.
   *
   * @param text the amount of turns to skip
   */
  public void setTurnsToSkip(String text) {
    turnsToSkip.setText(text);
  }

}
