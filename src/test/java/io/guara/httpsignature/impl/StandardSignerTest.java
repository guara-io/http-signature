package io.guara.httpsignature.impl;

import java.security.Key;
import java.security.PrivateKey;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.Test;

import io.guara.httpsignature.HttpSignature;
import io.guara.httpsignature.algorithm.Algorithms;
import io.guara.httpsignature.message.Header;
import io.guara.httpsignature.message.HttpMessage;
import io.guara.httpsignature.message.Method;

public class StandardSignerTest {

  @Test
  public void mustNotAddRequestTargetIfRequestPathIsNotPresent() {
    
    HttpMessage message = new HttpMessage();
    message.setCreatedAt(1402170695L);
    message.setMethod(Method.GET);
    message.addHeader("Host", "localhost");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key", message);
    
    Assert.assertFalse(signature.getHeaderNames().contains("(request-target)"));
    
  }
  
  @Test
  public void mustNotAddRequestTargetIfHttpMethodIsNotPresent() {
    
    HttpMessage message = new HttpMessage();
    message.setCreatedAt(1402170695L);
    message.setRequestPath("/foo");
    
    Header host = new Header();
    host.setName("Host");
    host.setValue("localhost");
    
    message.addHeader(host);

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key", message);
    
    Assert.assertFalse(signature.getHeaderNames().contains("(request-target)"));
    
  }
  
  @Test
  public void mustNotAddRequestTargetIfBothHttpMethodAndRequestPathAreNotPresent() {
    
    HttpMessage message = new HttpMessage();
    message.setCreatedAt(1402170695L);
    message.addHeader("Host", "localhost");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key", message);
    
    Assert.assertFalse(signature.getHeaderNames().contains("(request-target)"));
    
  }
  
  @Test
  public void mustAddRequestTargetIfBothHttpMethodAndRequestPathArePresent() {
    
    HttpMessage message = new HttpMessage();
    message.setMethod(Method.POST);
    message.setRequestPath("/foo");
    message.addHeader("Host", "localhost");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key", message);
    
    Assert.assertTrue(signature.getHeaderNames().contains("(request-target)"));
    
  }
  
  @Test
  public void mustAddCreatedHeader() {
    
    HttpMessage message = new HttpMessage();
    message.setCreatedAt(System.currentTimeMillis());
    message.addHeader("Host", "localhost");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key", message);
    
    Assert.assertTrue(signature.getHeaderNames().contains("(created)"));
    
  }
  
  @Test
  public void mustAddExpiresHeader() {
    
    HttpMessage message = new HttpMessage();
    message.setExpiresAt(System.currentTimeMillis());
    message.addHeader("Host", "localhost");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key", message);
    
    Assert.assertTrue(signature.getHeaderNames().contains("(expires)"));
    
  }
  
  @Test
  public void mustSetKeyIdInSignatureAsDefined() {
    
    HttpMessage message = new HttpMessage();
    message.addHeader("Host", "localhost");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key-id", message);
    
    Assert.assertEquals("my-key-id", signature.getKeyId());
    
  }
  
  @Test
  public void mustSetAlgorithmRsaSha256() {
    
    HttpMessage message = new HttpMessage();
    message.addHeader("Host", "localhost");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "my-key-id", message);
    
    Assert.assertEquals(Algorithms.Asymmetric.RSA_SHA256, signature.getAlgorithm());
    
  }
  
  @Test
  public void mustSignDateHeader() {
    
    HttpMessage message = new HttpMessage();
    message.setHeaders(Arrays.asList(Header.of("Date", "Sun, 05 Jan 2014 21:31:40 GMT")));
    
    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft12_privateKey.pem"); // https://tools.ietf.org/html/draft-cavage-http-signatures-12#appendix-C
    StandardSigner signer = new StandardSigner(key);
    
    String keyId = "Test";
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, keyId, message);
    
    String expected = "keyId=\"Test\",algorithm=\"rsa-sha256\",headers=\"date\",signature=\"SjWJWbWN7i0wzBvtPl8rbASWz5xQW6mcJmn+ibttBqtifLN7Sazz6m79cNfwwb8DMJ5cou1s7uEGKKCs+FLEEaDV5lp7q25WqS+lavg7T8hc0GppauB6hbgEKTwblDHYGEtbGmtdHgVCk9SuS13F0hZ8FD0k/5OxEPXe5WozsbM=\"";

    Assert.assertEquals(expected, signature.getFormatted());
    
  }
  
  @Test
  public void mustSignDateHeaderAndDisplayHs2019() {
    
    HttpMessage message = new HttpMessage();
    message.setHeaders(Arrays.asList(Header.of("Date", "Sun, 05 Jan 2014 21:31:40 GMT")));
    
    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft12_privateKey.pem"); // https://tools.ietf.org/html/draft-cavage-http-signatures-12#appendix-C
    StandardSigner signer = new StandardSigner(key);
    
    String keyId = "Test";
    
    HttpSignature signature = signer.sign(Algorithms.HS2019.with(Algorithms.Asymmetric.RSA_SHA256), keyId, message);
    
    String expected = "keyId=\"Test\",algorithm=\"hs2019\",headers=\"date\",signature=\"SjWJWbWN7i0wzBvtPl8rbASWz5xQW6mcJmn+ibttBqtifLN7Sazz6m79cNfwwb8DMJ5cou1s7uEGKKCs+FLEEaDV5lp7q25WqS+lavg7T8hc0GppauB6hbgEKTwblDHYGEtbGmtdHgVCk9SuS13F0hZ8FD0k/5OxEPXe5WozsbM=\"";

    Assert.assertEquals(expected, signature.getFormatted());
    
  }
  
  @Test
  public void mustSignRequestTargetHostAndDate() {
    
    HttpMessage message = new HttpMessage();
    message.setMethod(Method.POST);
    message.setRequestPath("/foo?param=value&pet=dog");
    
    message.setHeaders(Arrays.asList(
        Header.of("host", "example.com"),
        Header.of("date", "Sun, 05 Jan 2014 21:31:40 GMT")));

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft12_privateKey.pem"); // https://tools.ietf.org/html/draft-cavage-http-signatures-12#appendix-C
    StandardSigner signer = new StandardSigner(key);
    
    String keyId = "Test";
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, keyId, message);
    
    String expected = "keyId=\"Test\",algorithm=\"rsa-sha256\",headers=\"(request-target) host date\",signature=\"qdx+H7PHHDZgy4y/Ahn9Tny9V3GP6YgBPyUXMmoxWtLbHpUnXS2mg2+SbrQDMCJypxBLSPQR2aAjn7ndmw2iicw3HMbe8VfEdKFYRqzic+efkb3nndiv/x1xSHDJWeSWkx3ButlYSuBskLu6kd9Fswtemr3lgdDEmn04swr2Os0=\"";
                      
    Assert.assertEquals(expected, signature.getFormatted());
    
  }
  
  @Test
  public void mustSignRequestTargetHostDateContentTypeDigestAndContentLength() {
    
    HttpMessage message = new HttpMessage();
    message.setMethod(Method.POST);
    message.setRequestPath("/foo?param=value&pet=dog");
    
    message.setHeaders(Arrays.asList(
    Header.of("host", "example.com"),
    Header.of("date", "Sun, 05 Jan 2014 21:31:40 GMT"),
    Header.of("content-type", "application/json"),
    Header.of("digest", "SHA-256=X48E9qOokqqrvdts8nOJRJN3OWDUoyWxBf7kbu9DBPE="),
    Header.of("content-length", "18")));
    
    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft12_privateKey.pem"); // https://tools.ietf.org/html/draft-cavage-http-signatures-12#appendix-C
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "Test", message);
    
    String expected = "keyId=\"Test\",algorithm=\"rsa-sha256\",headers=\"(request-target) host date content-type digest content-length\",signature=\"vSdrb+dS3EceC9bcwHSo4MlyKS59iFIrhgYkz8+oVLEEzmYZZvRs8rgOp+63LEM3v+MFHB32NfpB2bEKBIvB1q52LaEUHFv120V01IL+TAD48XaERZFukWgHoBTLMhYS2Gb51gWxpeIq8knRmPnYePbF5MOkR0Zkly4zKH7s1dE=\"";

    Assert.assertEquals(expected, signature.getFormatted());
    
  }
  
  @Test
  public void mustSignWithEmptyHeaderValue() {
    
    HttpMessage message = new HttpMessage();
    message.setCreatedAt(1402170695L);
    message.setMethod(Method.GET);
    message.setRequestPath("/foo");
    message.addHeader("Host", "example.org");
    message.addHeader("Date", "Tue, 07 Jun 2014 20:51:35 GMT");
    message.addHeader("Cache-Control", "max-age=60, must-revalidate");
    message.addHeader("X-EmptyHeader", "");
    message.addHeader("X-Example", "Example header with some whitespace.");

    PrivateKey key = X509Utils.getInstance().loadPrivateKey("draft-ietf-httpbis-message-signatures-00_privateKey.pem"); // https://tools.ietf.org/html/draft-ietf-httpbis-message-signatures-00#appendix-A
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Asymmetric.RSA_SHA256, "test-key-b", message);
    
    String expected = "T1l3tWH2cSP31nfuvc3nVaHQ6IAu9YLEXg2pCeEOJETXnlWbgKtBTaXV6LNQWtf4O42V2DZwDZbmVZ8xW3TFW80RrfrY0+fyjD4OLN7/zV6L6d2v7uBpuWZ8QzKuHYFaRNVXgFBXN3VJnsIOUjv20pqZMKO3phLCKX2/zQzJLCBQvF/5UKtnJiMp1ACNhG8LF0Q0FPWfe86YZBBxqrQr5WfjMu0LOO52ZAxi9KTWSlceJ2U361gDb7S5Deub8MaDrjUEpluphQeo8xyvHBoNXsqeax/WaHyRYOgaW6krxEGVaBQAfA2czYZhEA05Tb38ahq/gwDQ1bagd9rGnCHtAg==";
    
    Assert.assertEquals(expected, signature.getSignature());
    
  }
 
  @Test
  public void mustSignUsingAlgorithmHmacSha256() {

    final Key key = new SecretKeySpec("do not show me".getBytes(), Algorithms.Symmetric.HMAC_SHA256.getName());
    
    HttpMessage message = new HttpMessage();
    message.setMethod(Method.GET);
    message.setRequestPath("/foo");
    
    message.setHeaders(Arrays.asList(
        Header.of("Host", "localhost"), 
        Header.of("Date", "Sat, 06 Jun 2020 16:09:02 GMT")));
    
    StandardSigner signer = new StandardSigner(key);
    
    HttpSignature signature = signer.sign(Algorithms.Symmetric.HMAC_SHA256, "my-key", message);
    
    Assert.assertEquals("N+mf4SfM8r3QwUtZPwMzgrPZJQrYWbhqJtlphcfNo2o=", signature.getSignature());
    
  }
  
}