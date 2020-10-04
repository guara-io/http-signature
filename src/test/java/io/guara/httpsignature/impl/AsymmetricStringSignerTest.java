package io.guara.httpsignature.impl;

import java.security.PrivateKey;
import java.security.SignatureException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import io.guara.httpsignature.HttpSignatureException;
import io.guara.httpsignature.algorithm.Algorithms;

public class AsymmetricStringSignerTest {

  @Test
  public void mustSignStringUsingRsaSha256() {
    
    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    String signature = new AsymmetricStringSigner().sign(Algorithms.Asymmetric.RSA_SHA256, key, "to be signed");
    
    Assert.assertEquals("DV1pFidF4dEm6X+7M3gQfsTiHJ6pVn1b3pXatqjJTNeqCwfA9PoujO8E2PGsFto0Dwo8w5jo+u7lznBawXptMht48griz+wVutrnneYFGXon6lTl2mvDzZpigcxKPDCOG9pppbS7E2YYfPbSi1fbNXHKP+wH7Ak2BFSLbW95TJiF/i3rFQ2o+BFRh52GcOeiJk4zDax4kZfi73PCCyK7CQ+rrY7HbDONsyPjFzH4tdx1BV/uwVV1LE+f8jUSgVnq6XWIneIGeqgR13f+VP3+TtZdH/jBgpa8YTSvDBNiwqYcPztpLOeT7yqASet227IMRJLFbHofbcR+yST1Aegglw==", signature);
    
  }
  
  @Test
  public void mustSignStringUsingRsaSha512() {
    
    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    String signature = new AsymmetricStringSigner().sign(Algorithms.Asymmetric.RSA_SHA512, key, "to be signed");
    
    Assert.assertEquals("NBR5CoBFbRugGOCewbwjILULN5MVGl9PTTOHM8iLGab4yproYrW13uTiEFqQFth2RzdgT84IfBERaj94JhViSuqCUu38MbEDpL40R2Lq0dBWJmGOKIU6nl2hyX9JLZRzGRuHvGyfCQNDD9Hjf3Rwf7cbBWM4sFH8Ue31Y/sJLCbEzKmfFUUdcwtgT/YSWbRNIGYbCbh/K0ayPFcmRzKVmOEesal5Edd7tEVghLR9bidFZWWwv2rSmf6cskiryCHPxReGIjdfuqr6YP+rUWEqNQicAgV4m+H5ZeJXqGxGjAqsaj8IdHDzTYvFi3uZlc5wrA4fOyyBUU518F21lDX+IQ==", signature);
    
  }
  
  @Test(expected = HttpSignatureException.class)
  public void mustThrowHttpSignatureExceptionWhenAlgorithmIsNotValid() {
  
    new AsymmetricStringSigner().sign(Algorithms.Symmetric.HMAC_SHA256, Mockito.any(), "to be signed");
    
    Assert.fail("Should throw HttpSignatureException");
    
  }
  
  @Test(expected = HttpSignatureException.class)
  public void mustThrowHttpSignatureExceptionWhenKeyIsNotValid() {
  
    new AsymmetricStringSigner().sign(Algorithms.Asymmetric.RSA_SHA256, Mockito.any(), "to be signed");
    
    Assert.fail("Should throw HttpSignatureException");
    
  }
  
  @Test(expected = HttpSignatureException.class)
  public void mustThrowHttpSignatureExceptionWhenSignatureIsNotValid() {
    
    AsymmetricStringSigner signer = new AsymmetricStringSigner() {
      byte[] sign(java.security.Signature sig) throws java.security.SignatureException {
        throw new SignatureException();
      }
    };
    
    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    signer.sign(Algorithms.Asymmetric.RSA_SHA256, key, "to be signed");
    
  }
  
}
