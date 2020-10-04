package io.guara.httpsignature;

public class HttpSignatureException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  public HttpSignatureException(String message) {
    super(message);
  }
  
  public HttpSignatureException(String message, Throwable cause) {
    super(message, cause);
  }

}
