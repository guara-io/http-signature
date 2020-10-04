package io.guara.httpsignature;

import java.util.List;

import io.guara.httpsignature.algorithm.Algorithm;

/**
 * @author Rauffer
 *
 *         Specifies the signature result contract.
 */
public interface HttpSignature {

  /**
   * Retrieves the key id.
   *
   * @return the key id
   */
  String getKeyId();

  /**
   * Retrieves the algorithm.
   *
   * @return the algorithm
   */
  Algorithm getAlgorithm();

  /**
   * Retrieves the header names.
   *
   * @return the header names
   */
  List<String> getHeaderNames();

  /**
   * Retrieves the signature.
   *
   * @return the signature
   */
  String getSignature();

  /**
   * Retrieves the formatted signature string.
   *
   * @return the formatted signature string
   */
  String getFormatted();

}
