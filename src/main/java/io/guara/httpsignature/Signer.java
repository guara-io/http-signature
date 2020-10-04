package io.guara.httpsignature;

import io.guara.httpsignature.algorithm.Algorithm;
import io.guara.httpsignature.message.HttpMessage;

public interface Signer {
  
  HttpSignature sign(Algorithm algorithm, String keyId, HttpMessage message);

}
