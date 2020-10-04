package io.guara.httpsignature;

import io.guara.httpsignature.algorithm.Algorithm;
import io.guara.httpsignature.message.HttpMessage;

/**
 * @author Rauffer
 *
 *         Specifies the signer contract.
 */
public interface Signer {

  /**
   * Signs a message.
   *
   * @param algorithm the algorithm
   * @param keyId     the key id
   * @param message   the message
   * @return the signature details
   */
  HttpSignature sign(Algorithm algorithm, String keyId, HttpMessage message);

}
