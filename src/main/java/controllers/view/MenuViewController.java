package controllers.view;

import controllers.ButtonClickNotifier;
import java.util.Map;
import models.GamePiece;
import views.menu.container.MenuView;

public class MenuViewController extends ViewController {

  private final MenuView menuView;

  /**
   * Constructor for MenuViewController.
   *
   */
  public MenuViewController() {
    this.menuView = new MenuView();

    this.getRootPane().getChildren().add(menuView);
  }

  @Override
  public void showBoardSelectMenu() {
    menuView.selectSnakesAndLaddersBoards();
  }

  @Override
  public void showPlayerSelectMenu() {
    menuView.selectPlayers();
  }

  @Override
  public Map<String, GamePiece> getSelectedPlayers() {
    return menuView.getSelectPlayersView().getSelectedPlayers();
  }

  /**
   * Sets the button click notifier for the menu view.
   *
   * @param notifier The button click notifier to set
   */
  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    super.setButtonClickNotifier(notifier);
    menuView.setButtonClickNotifier(notifier);
  }

}
