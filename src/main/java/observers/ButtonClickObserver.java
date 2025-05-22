package observers;

import exception.UnknownGameException;
import java.io.IOException;

/**
 * <h3>Listener of a button click</h3>
 *
 * <p>Listens to a button click given by the user.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public interface ButtonClickObserver {

  /**
   * Updates the state based on the action performed.
   *
   * @param action the action performed by the user.
   */
  void update(String action);

}
