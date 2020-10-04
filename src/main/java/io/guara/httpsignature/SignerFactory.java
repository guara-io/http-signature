package io.guara.httpsignature;

import java.security.Key;

import io.guara.httpsignature.impl.StandardSigner;

/**
 * @author Rauffer
 *
 *         This class is a factory of Singer objects.
 */
public final class SignerFactory {

  /**
   * The internal reference.
   */
  private static final SignerFactory INSTANCE = new SignerFactory();

  private SignerFactory() {
    // hidden
  }

  /**
   * Retrieves the singleton instance.
   *
   * @return the instance
   */
  public static SignerFactory getInstance() {
    return INSTANCE;
  }

  /**
   * Instantiates a standard signer with the given key.
   *
   * @param key the key
   * @return a StandardSigner
   */
  public Signer getStandarSigner(final Key key) {
    return new StandardSigner(key);
  }

}
