package controllers.view;

import controllers.ButtonClickNotifier;
import controllers.model.GameNotifier;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.StackPane;
import models.GamePiece;
import models.Player;

/**
 * Abstract class representing a view controller in the game.
 *
 * @author Tord Fosse
 * @version 1.0.0
 */
public abstract class ViewController extends GameNotifier {

  private final StackPane rootPane;

  /**
   * Constructor for ViewController.
   */
  public ViewController() {
    this.rootPane = new StackPane();
  }

  /**
   * Gets the root pane of the view controller.
   *
   * @return the root pane
   */
  public StackPane getRootPane() {
    return rootPane;
  }

  /**
   * Show the board selection menu.
   */
  public abstract void showBoardSelectMenu();

  /**
   * Show the player selection menu.
   */
  public abstract void showPlayerSelectMenu();

  /**
   * Show the game selection menu.
   */
  public abstract void showGameSelectMenu();

  /**
   * Get the selected players from the menu.
   *
   * @return a map of selected players
   */
  public abstract Map<String, GamePiece> getSelectedPlayers();

  /**
   * Adds player views to the game board.
   *
   * @param players List of players to add views for
   */
  public abstract void addPlayerViews(List<Player> players);

  /**
   * Show the view.
   */
  public abstract void showView();

  /**
   * Sets the button click notifier for the view.
   *
   * @param notifier the button click notifier
   */
  public abstract void setButtonClickNotifier(ButtonClickNotifier notifier);

  /**
   * Sets up the view with the specified board file name and board number.
   *
   * @param boardFileName the name of the board file
   * @param boardNumber   the number of the board
   */
  public abstract void setUpView(String boardFileName, int boardNumber);

  /**
   * Sets up the view with the specified board file name.
   *
   * @param boardFileName the name of the board file
   */
  public abstract void setUpView(String boardFileName);

}
