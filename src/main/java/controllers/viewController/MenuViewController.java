package controllers.viewController;

import controllers.ButtonClickNotifier;
import java.util.List;
import java.util.Map;
import models.GamePiece;
import models.Player;
import models.validators.ArgumentValidator;
import org.slf4j.Logger;
import views.menu.container.MenuView;

public class MenuViewController extends ViewController {

  private final MenuView menuView = new MenuView();
  private final Logger logger = org.slf4j.LoggerFactory.getLogger(MenuViewController.class);

  /**
   * Constructor for MenuViewController.
   */
  public MenuViewController() {
    logger.debug("MenuViewController initialized");
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
  public void showGameSelectMenu() {
    menuView.selectGame();
  }

  @Override
  public Map<String, GamePiece> getSelectedPlayers() {
    return menuView.getSelectPlayersView().getSelectedPlayers();
  }

  @Override
  public void addPlayerViews(List<Player> players) {
    // Not used
  }

  @Override
  public void showView() {
    getRootPane().getChildren().clear();
    getRootPane().getChildren().add(menuView);
    logger.debug("Menu view displayed");
  }

  /**
   * Sets the button click notifier for the menu view.
   *
   * @param notifier The button click notifier to set
   */
  @Override
  public void setButtonClickNotifier(ButtonClickNotifier notifier) {
    ArgumentValidator.setButtonClickNotifier(notifier);
    menuView.setButtonClickNotifier(notifier);
  }

  @Override
  public void setUpView(String boardFileName, int boardNumber) {
    // Not used
  }

  @Override
  public void setUpView(String boardFileName) {
    // Not used
  }
}
