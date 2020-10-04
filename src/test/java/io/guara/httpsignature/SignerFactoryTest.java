package io.guara.httpsignature;

import java.nio.charset.Charset;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.Test;

import io.guara.httpsignature.algorithm.Algorithms;

public class SignerFactoryTest {

  @Test
  public void mustReturnDifferentStandarSignerInstances() {
    
    Key key = new SecretKeySpec("secret".getBytes(Charset.forName("UTF-8")), Algorithms.Symmetric.HMAC_SHA256.getName());
    
    Signer instance1 = SignerFactory.getInstance().getStandarSigner(key);
    Signer instance2 = SignerFactory.getInstance().getStandarSigner(key);
    
    Assert.assertNotSame(instance1, instance2);
    
  }
  
}
