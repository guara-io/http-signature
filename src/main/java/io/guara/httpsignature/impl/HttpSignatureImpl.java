package io.guara.httpsignature.impl;

import java.util.List;

import io.guara.httpsignature.HttpSignature;
import io.guara.httpsignature.algorithm.Algorithm;

/**
 * @author Rauffer
 *
 *         This class implements the details of a HttpSignature.
 */
public class HttpSignatureImpl implements HttpSignature {

  /**
   * The key id.
   */
  private String keyId;

  /**
   * The algorithm.
   */
  private Algorithm algorithm;

  /**
   * the header names.
   */
  private List<String> headerNames;

  /**
   * The signature.
   */
  private String signature;

  /**
   * The formatted signature.
   */
  private String formatted;

  /**
   * Retrieves the key id.
   *
   * @return the key id
   */
  public String getKeyId() {
    return this.keyId;
  }

  /**
   * Retrieves the algorithm.
   *
   * @return the algorithm
   */
  public Algorithm getAlgorithm() {
    return this.algorithm;
  }

  /**
   * Retrieves the header names.
   *
   * @return the header names
   */
  public List<String> getHeaderNames() {
    return this.headerNames;
  }

  /**
   * Retrieves the signature.
   *
   * @return the signature
   */
  public String getSignature() {
    return this.signature;
  }

  /**
   * Retrieves the formatted signature string.
   *
   * @return the formatted signature string
   */
  public String getFormatted() {
    return this.formatted;
  }

  /**
   * Overrides the key id.
   *
   * @param keyId the key id
   */
  public void setKeyId(final String keyId) {
    this.keyId = keyId;
  }

  /**
   * Overrides the algorithm.
   *
   * @param algorithm the algorithm
   */
  public void setAlgorithm(final Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Overrides the header names.
   *
   * @param headerNames the header names
   */
  public void setHeaderNames(final List<String> headerNames) {
    this.headerNames = headerNames;
  }

  /**
   * Overrides the signature.
   *
   * @param signature the signature
   */
  public void setSignature(final String signature) {
    this.signature = signature;
  }

  /**
   * Overrides the formatted signature.
   *
   * @param formatted the formatted signature
   */
  public void setFormatted(final String formatted) {
    this.formatted = formatted;
  }

}
