package io.guara.httpsignature.impl;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

import io.guara.httpsignature.HttpSignatureException;
import io.guara.httpsignature.StringSigner;
import io.guara.httpsignature.algorithm.Algorithm;

/**
 * @author Rauffer
 *
 *         Implements an asymmetric string signer.
 */
public class AsymmetricStringSigner implements StringSigner {

  /**
   * Internal instance reference.
   */
  private static final AsymmetricStringSigner INSTANCE = new AsymmetricStringSigner();

  AsymmetricStringSigner() {
    // hidden
  }

  /**
   * Retrieves a singleton instance.
   *
   * @return the instance
   */
  public static AsymmetricStringSigner getInstance() {
    return INSTANCE;
  }

  /**
   * Signs the string using an asymmetric algorithm.
   *
   * @param algorithm the algorithm to be used to sign
   * @param key       the key to be used to sign
   * @param strToSign the string to be signed
   * @return the signed string
   */
  public String sign(final Algorithm algorithm, final Key key, final String strToSign) {

    if (key == null) {
      throw new HttpSignatureException("Key cannot be null.");
    }

    try {

      Signature signature = Signature.getInstance(algorithm.getName());
      signature.initSign((PrivateKey) key);
      signature.update(strToSign.getBytes(Charset.forName("UTF-8")));

      byte[] signed = sign(signature);

      return Base64Encoder.getInstance().encode(signed);

    } catch (NoSuchAlgorithmException e) {
      throw new HttpSignatureException("Invalid algorithm: " + algorithm, e);
    } catch (InvalidKeyException e) {
      throw new HttpSignatureException("Invalid key", e);
    } catch (SignatureException e) {
      throw new HttpSignatureException("Error when signing string", e);
    }

  }

  /**
   * Implements signature invocation details.
   *
   * @param signature signature
   * @return signed string
   * @throws SignatureException case and error occurs during signature
   */
  byte[] sign(final Signature signature) throws SignatureException {
    return signature.sign();
  }

}
