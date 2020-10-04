package io.guara.httpsignature.impl;

import java.util.Base64;

public class Base64Encoder {
  
  private static final Base64Encoder INSTANCE = new Base64Encoder();
  
  private Base64Encoder() {
    // hidden
  }
  
  public static Base64Encoder getInstance() {
    return INSTANCE;
  }

  public String encode(byte[] message) {
    return new String(Base64.getEncoder().encode(message));
  }

}