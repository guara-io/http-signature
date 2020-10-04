package io.guara.httpsignature.impl;

import java.nio.charset.Charset;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import io.guara.httpsignature.HttpSignatureException;
import io.guara.httpsignature.algorithm.Algorithms;

public class SymmetricStringSignerTest {

  @Test
  public void mustSignStringUsingHmacSha256() {

    String secret = "secret";
    String message = "message";

    Key key = new SecretKeySpec(secret.getBytes(Charset.forName("UTF-8")), Algorithms.Symmetric.HMAC_SHA256.getName());
    String signed = new SymmetricStringSigner().sign(Algorithms.Symmetric.HMAC_SHA256, key, message);
    Assert.assertEquals("i19IcCmVwVmMVz2x4hhmqbgl1KeU0WnXBgoDYFeWNgs=", signed);

  }
  
  @Test
  public void mustSignStringUsingHmacSha512() {

    String secret = "secret";
    String message = "message";

    Key key = new SecretKeySpec(secret.getBytes(Charset.forName("UTF-8")), Algorithms.Symmetric.HMAC_SHA512.getName());
    String signed = new SymmetricStringSigner().sign(Algorithms.Symmetric.HMAC_SHA512, key, message);
    Assert.assertEquals("G7pYfHMO7box9Tq7C2ylieCd5OiU7kVeYUCAc5l1mtqvoGnux8AWR7sXPcsX9V0ir0mhgHG3SMXC7df3qCnGMg==", signed);

  }

  @Test(expected = HttpSignatureException.class)
  public void mustThrowHttpSignatureExceptionWhenAlgorithmIsNotValid() throws Exception {

    SymmetricStringSigner simmetricStringSigner = new SymmetricStringSigner();

    simmetricStringSigner.sign(Algorithms.Asymmetric.RSA_SHA256, null, "");

    Assert.fail("Should throw HttpSignatureException");

  }

  @Test(expected = HttpSignatureException.class)
  public void mustThrowHttpSignatureExceptionWhenKeyIsNotValid() throws Exception {

    SymmetricStringSigner simmetricStringSigner = new SymmetricStringSigner();

    simmetricStringSigner.sign(Algorithms.Symmetric.HMAC_SHA256, Mockito.any(Key.class), "to be signed");

    Assert.fail("Should throw HttpSignatureException");

  }

}