package interfaces;
import controllers.BoardGameNotifier;
import models.Player;

/**
 * Interface for action classes. The perform method defines what will happen to a player when it
 * lands on a tile.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 */
public interface TileAction {

  void perform(Player player, BoardGameNotifier notifier);


}
