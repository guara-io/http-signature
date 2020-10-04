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

public class AsymmetricStringSigner implements StringSigner {
  
  private static final AsymmetricStringSigner INSTANCE = new AsymmetricStringSigner();
  
  AsymmetricStringSigner() {
    // hidden
  }
  
  public static AsymmetricStringSigner getInstance() {
    return INSTANCE;
  }

  public String sign(Algorithm algorithm, Key key, String strToSign) {
  	
  	if (key == null) {
  		throw new HttpSignatureException("Key cannot be null.");
  	}
    
    try {
      
      Signature sig = Signature.getInstance(algorithm.getName());
      sig.initSign((PrivateKey)key);
      sig.update(strToSign.getBytes(Charset.forName("UTF-8")));
      
      byte[] signature = sign(sig);
      
      return Base64Encoder.getInstance().encode(signature);
      
    } catch(NoSuchAlgorithmException e) {
      throw new HttpSignatureException("Invalid algorithm: " + algorithm, e);
    } catch (InvalidKeyException e) {
      throw new HttpSignatureException("Invalid key", e);
    } catch (SignatureException e) {
      throw new HttpSignatureException("Error when signing string", e);
    }
    
  }

  byte[] sign(Signature sig) throws SignatureException {
    return sig.sign();
  }
  
}