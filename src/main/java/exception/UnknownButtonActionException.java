package exception;

/**
 * Exception thrown when an unknown button action is encountered.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownButtonActionException extends RuntimeException {

  /**
   * Constructs a new UnknownButtonActionException with the specified detail message.
   *
   * @param message The detail message
   */
  public UnknownButtonActionException(String message) {
    super(message);
  }
}
