package io.guara.httpsignature.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.guara.httpsignature.HttpSignature;
import io.guara.httpsignature.SignatureStringBuilder;
import io.guara.httpsignature.Signer;
import io.guara.httpsignature.StringSigner;
import io.guara.httpsignature.algorithm.Algorithm;
import io.guara.httpsignature.message.Header;
import io.guara.httpsignature.message.HttpMessage;

public class StandardSigner implements Signer {

  private Key key;
  private SignatureFormatter formatter;
  private SignatureStringBuilder signatureStringBuilder;
  
  public StandardSigner(Key key) {
    this.key = key;
    this.formatter = new SignatureFormatter();
    this.signatureStringBuilder = new StandardSignatureStringBuilder();
  }
  
  public HttpSignature sign(Algorithm algorithm, String keyId, HttpMessage message) {
    
    List<Header> headersToSign = determineHeadersToSign(message);
    String signingString = signatureStringBuilder.build(headersToSign);
    StringSigner stringSigner = getStringSigner(algorithm);
    
    String signature = stringSigner.sign(algorithm, key, signingString);
    
    HttpSignatureImpl signatureResult = new HttpSignatureImpl();
    
    List<String> headerNames = headersToSign
        .stream()
        .map(header -> header.getName().toLowerCase())
        .collect(Collectors.toList());
    
    signatureResult.setAlgorithm(algorithm);
    signatureResult.setKeyId(keyId);
    signatureResult.setHeaderNames(headerNames);
    signatureResult.setSignature(signature);
    String formatted = formatter.format(algorithm, keyId, message, headerNames, signature);
    signatureResult.setFormatted(formatted);
    
    return signatureResult;
    
  }

  List<Header> determineHeadersToSign(HttpMessage message) {
    
    List<Header> headersToSign = new ArrayList<Header>();
    
    String requestPath = message.getRequestPath();
    
    if (message.getMethod() != null && requestPath != null) {
      String methodLowerCase = message.getMethod().name().toLowerCase();
      headersToSign.add(Header.of("(request-target)", methodLowerCase + " " + requestPath));
    }
    
    if (message.getCreatedAt() != null) {
      headersToSign.add(Header.of("(created)", String.valueOf(message.getCreatedAt())));
    }
    
    if (message.getExpiresAt() != null) {
      headersToSign.add(Header.of("(expires)", String.valueOf(message.getExpiresAt())));
    }
    
    headersToSign.addAll(message.getHeaders());
    
    return headersToSign;
    
  }

  StringSigner getStringSigner(Algorithm algorithm) {
    
    if (algorithm.isAsymmetric()) {
      return AsymmetricStringSigner.getInstance();
    }
    
    return SymmetricStringSigner.getInstance();
    
  }

}
