package interfaces;
import models.Player;

/**
 * Interface for action classes. The preform method defines what will happen to a player when it
 * lands on a tile.
 *
 * @version 0.1.0
 * @author Erlend Sundsdal
 * @since 0.1.0
 */
public interface TileAction {

  public void preform(Player player);


}
