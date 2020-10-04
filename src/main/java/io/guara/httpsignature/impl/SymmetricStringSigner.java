package io.guara.httpsignature.impl;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;

import io.guara.httpsignature.HttpSignatureException;
import io.guara.httpsignature.StringSigner;
import io.guara.httpsignature.algorithm.Algorithm;

public class SymmetricStringSigner implements StringSigner {

  private static final SymmetricStringSigner INSTANCE = new SymmetricStringSigner();
  
  SymmetricStringSigner() {
    // hidden
  }
  
  public static StringSigner getInstance() {
    return INSTANCE;
  }
  
  public String sign(Algorithm algorithm, Key key, String strToSign) {
    
    try {
    
      Mac mac = getMacInstance(algorithm);
      mac.init(key);
      
      byte[] signature = mac.doFinal(strToSign.getBytes(Charset.forName("UTF-8")));
      
      return Base64Encoder.getInstance().encode(signature);

    } catch(NoSuchAlgorithmException e) {
      throw new HttpSignatureException("Invalid algorithm: " + algorithm, e);
    } catch (InvalidKeyException e) {
      throw new HttpSignatureException("Invalid key", e);
    }
    
  }

  Mac getMacInstance(Algorithm algorithm) throws NoSuchAlgorithmException {
    return Mac.getInstance(algorithm.getName());
  }

}