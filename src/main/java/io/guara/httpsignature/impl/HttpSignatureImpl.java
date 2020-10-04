package io.guara.httpsignature.impl;

import java.util.List;

import io.guara.httpsignature.HttpSignature;
import io.guara.httpsignature.algorithm.Algorithm;

public class HttpSignatureImpl implements HttpSignature {

  private String keyId;
  private Algorithm algorithm;
  private List<String> headerNames;
  private String signature;
  private String formatted;

  public String getKeyId() {
    return keyId;
  }

  public void setKeyId(String keyId) {
    this.keyId = keyId;
  }

  public Algorithm getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  public List<String> getHeaderNames() {
    return headerNames;
  }

  public void setHeaderNames(List<String> headerNames) {
    this.headerNames = headerNames;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getFormatted() {
    return formatted;
  }

  public void setFormatted(String formatted) {
    this.formatted = formatted;
  }
  
}