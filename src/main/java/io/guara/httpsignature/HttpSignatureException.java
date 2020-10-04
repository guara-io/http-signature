package io.guara.httpsignature;

/**
 * @author Rauffer
 *
 *         This class is used to point a problem with the Http Signature.
 */
public class HttpSignatureException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs with a message.
   *
   * @param message the message
   */
  public HttpSignatureException(final String message) {
    super(message);
  }

  /**
   * Constructs with a message and a root cause.
   *
   * @param message the message
   * @param cause   the cause
   */
  public HttpSignatureException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
