package io.guara.httpsignature.impl;

import java.util.Base64;

/**
 * @author Rauffer
 *
 *         Utility class used for base 64 encoding.
 */
public final class Base64Encoder {

  /**
   * Internal instance reference.
   */
  private static final Base64Encoder INSTANCE = new Base64Encoder();

  private Base64Encoder() {
    // hidden
  }

  /**
   * Retrieves a singleton instance.
   * @return the instance
   */
  public static Base64Encoder getInstance() {
    return INSTANCE;
  }

  /**
   * Encodes a message to base 64 format.
   *
   * @param message the message
   * @return the encoded message
   */
  public String encode(final byte[] message) {
    return new String(Base64.getEncoder().encode(message));
  }

}
