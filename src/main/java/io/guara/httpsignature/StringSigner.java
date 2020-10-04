package io.guara.httpsignature;

import java.security.Key;

import io.guara.httpsignature.algorithm.Algorithm;

/**
 * @author Rauffer
 *
 *         Specifies the string signer contract.
 */
public interface StringSigner {

  /**
   * Signs a string using the specified algorithm and key.
   *
   * @param algorithm the algorithm to be used to sign
   * @param key       the key to be used to sign
   * @param strToSign the string to be signed
   * @return the signed string
   */
  String sign(Algorithm algorithm, Key key, String strToSign);

}
