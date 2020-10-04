package io.guara.httpsignature;

import java.security.Key;

import io.guara.httpsignature.impl.StandardSigner;

public class SignerFactory {
  
  private static final SignerFactory INSTANCE = new SignerFactory();
  
  private SignerFactory() {
    // hidden
  }

  public static SignerFactory getInstance() {
    return INSTANCE;
  }
  
  public Signer getStandarSigner(Key key) {
    return new StandardSigner(key);
  }
  
}
