package exception;

/**
 * Exception thrown when an unknown tile action is encountered.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownTileActionException extends RuntimeException {

  /**
   * Constructs a new UnknownTileActionException with the specified detail message.
   *
   * @param message The detail message
   */
  public UnknownTileActionException(String message) {
    super(message);
  }
}
