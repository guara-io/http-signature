package io.guara.httpsignature.impl;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;

import io.guara.httpsignature.HttpSignatureException;
import io.guara.httpsignature.StringSigner;
import io.guara.httpsignature.algorithm.Algorithm;

/**
 * @author Rauffer
 *
 *         Implements an symmetric string signer.
 */
public class SymmetricStringSigner implements StringSigner {

  /**
   * Internal instance reference.
   */
  private static final SymmetricStringSigner INSTANCE = new SymmetricStringSigner();

  SymmetricStringSigner() {
    // hidden
  }

  /**
   * Retrieves a singleton instance.
   *
   * @return the instance
   */
  public static StringSigner getInstance() {
    return INSTANCE;
  }

  /**
   * Signs the string using an symmetric algorithm.
   *
   * @param algorithm the algorithm to be used to sign
   * @param key       the key to be used to sign
   * @param strToSign the string to be signed
   * @return the signed string
   */
  public String sign(final Algorithm algorithm, final Key key, final String strToSign) {

    try {

      Mac mac = getMacInstance(algorithm);
      mac.init(key);

      byte[] signature = mac.doFinal(strToSign.getBytes(Charset.forName("UTF-8")));

      return Base64Encoder.getInstance().encode(signature);

    } catch (NoSuchAlgorithmException e) {
      throw new HttpSignatureException("Invalid algorithm: " + algorithm, e);
    } catch (InvalidKeyException e) {
      throw new HttpSignatureException("Invalid key", e);
    }

  }

  /**
   * Instantiate a Mac object based on the algorithm.
   *
   * @param algorithm the algorithm
   * @return the Mac instance
   * @throws NoSuchAlgorithmException case the algorithm is not recognized
   */
  Mac getMacInstance(final Algorithm algorithm) throws NoSuchAlgorithmException {
    return Mac.getInstance(algorithm.getName());
  }

}
