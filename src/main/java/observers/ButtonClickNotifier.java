package observers;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Notifier for button clicks</h3>
 *
 * <p>This class is manages and notifies button click listeners.
 * It allows adding, removing and notifying listeners when a button is clicked.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class ButtonClickNotifier {

  List<ButtonClickListener> buttonClickListeners = new ArrayList<>();

  /**
   * Adds a listener to the list of listeners.
   *
   * @param buttonClickListener a listener for button clicks.
   */
  public void addListener(ButtonClickListener buttonClickListener) {
    buttonClickListeners.add(buttonClickListener);
  }

  /**
   * Removes a listener form the list of listeners.
   *
   * @param buttonClickListener a listener for button clicks.
   */
  public void removeListener(ButtonClickListener buttonClickListener) {
    buttonClickListeners.remove(buttonClickListener);
  }

  /**
   * Notifies all the listeners of a button click given to a listener.
   *
   * @param action from a button pressed by the user.
   */
  public void notifyListeners(String action) {
    buttonClickListeners.forEach(buttonClickListener -> buttonClickListener.update(action));
  }
}
