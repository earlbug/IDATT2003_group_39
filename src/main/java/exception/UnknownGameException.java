package exception;

/**
 * Exception thrown when an unknown game is encountered.
 *
 * @author Tord Fosse
 * @version 1.0.0
 * @since 1.0.0
 */
public class UnknownGameException extends Exception {

  /**
   * Constructs a new UnknownGameException with the specified detail message.
   *
   * @param message The detail message
   */
  public UnknownGameException(String message) {
    super(message);
  }
}
