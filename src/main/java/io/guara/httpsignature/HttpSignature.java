package io.guara.httpsignature;

import java.util.List;

import io.guara.httpsignature.algorithm.Algorithm;

public interface HttpSignature {

  String getKeyId();

  Algorithm getAlgorithm();

  List<String> getHeaderNames();

  String getSignature();

  void setSignature(String signature);

  String getFormatted();

}
