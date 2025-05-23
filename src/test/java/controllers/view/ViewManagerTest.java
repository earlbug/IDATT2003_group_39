package controllers.view;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ViewManagerTest extends ApplicationTest {

  private ViewManager viewManager;

  @Override
  public void start(Stage stage) {
    viewManager = new ViewManager(stage);
  }

  @Test
  void testInitialViewIsMenuView() {
    assertTrue(viewManager.getCurrentViewController() instanceof MenuViewController);
  }

  @Test
  void testSwitchToMonopolyView() {
    viewManager.switchToMonopolyView();
    assertTrue(viewManager.getCurrentViewController() instanceof MonopolyViewController);
  }

  @Test
  void testSwitchToSnakesAndLaddersView() {
    viewManager.switchToSnakesAndLaddersView();
    assertTrue(viewManager.getCurrentViewController() instanceof SnakesAndLaddersViewController);
  }

  @Test
  void testSwitchingViewsVisibility() {
    Pane menuPane = viewManager.getCurrentViewController().getRootPane();
    viewManager.switchToMonopolyView();
    Pane monopolyPane = viewManager.getCurrentViewController().getRootPane();

    assertFalse(menuPane.isVisible());
    assertTrue(monopolyPane.isVisible());
  }
}