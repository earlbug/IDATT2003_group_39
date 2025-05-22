package controllers;

import java.util.ArrayList;
import java.util.List;
import observers.ButtonClickObserver;

/**
 * <h3>Notifier for button clicks</h3>
 *
 * <p>This class is manages and notifies button click listeners.
 * It allows adding, removing and notifying listeners when a button is clicked.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public abstract class ButtonClickNotifier {

  List<ButtonClickObserver> buttonClickObservers = new ArrayList<>();

  /**
   * Adds a listener to the list of listeners.
   *
   * @param buttonClickObserver a listener for button clicks.
   */
  public void addObserver(ButtonClickObserver buttonClickObserver) {
    buttonClickObservers.add(buttonClickObserver);
  }

  /**
   * Removes a listener form the list of listeners.
   *
   * @param buttonClickObserver a listener for button clicks.
   */
  public void removeObserver(ButtonClickObserver buttonClickObserver) {
    buttonClickObservers.remove(buttonClickObserver);
  }

  /**
   * Notifies all the listeners of a button click given to a listener.
   *
   * @param action from a button pressed by the user.
   */
  public void notifyObservers(String action) {
    buttonClickObservers.forEach(buttonClickObserver -> buttonClickObserver.update(action));
  }
}
