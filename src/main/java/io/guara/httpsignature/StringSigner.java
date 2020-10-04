package io.guara.httpsignature;

import java.security.Key;

import io.guara.httpsignature.algorithm.Algorithm;

public interface StringSigner {

  String sign(Algorithm algorithm, Key key, String strToSign);
  
}