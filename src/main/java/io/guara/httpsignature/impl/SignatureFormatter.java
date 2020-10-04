package io.guara.httpsignature.impl;

import java.util.List;

import io.guara.httpsignature.algorithm.Algorithm;
import io.guara.httpsignature.message.HttpMessage;

public class SignatureFormatter {

  private static final String KEY_ID = "keyId=\"%s\",";
  private static final String ALGORITHM = "algorithm=\"%s\",";
  private static final String CREATED = "created=%s,";
  private static final String EXPIRES = "expires=%s,";
  private static final String HEADERS = "headers=\"%s\",";
  private static final String SIGNATURE = "signature=\"%s\"";

  String format(Algorithm algorithm, String keyId, HttpMessage message, List<String> headerNames, String signature) {

    StringBuilder formattedSignature = new StringBuilder();
    
    formattedSignature.append(String.format(KEY_ID, keyId));
    formattedSignature.append(String.format(ALGORITHM, algorithm.getPortableName()));

    if (message.getCreatedAt() != null) {
      formattedSignature.append(String.format(CREATED, message.getCreatedAt()));
    }
    
    if (message.getExpiresAt() != null) {
      formattedSignature.append(String.format(EXPIRES, message.getExpiresAt()));
    }
    
    if (!headerNames.isEmpty()) {
      formattedSignature.append(String.format(HEADERS, getFormattedHeaderNames(headerNames)));
    }
    
    formattedSignature.append(String.format(SIGNATURE, signature));
    
    return formattedSignature.toString();

  }

  private String getFormattedHeaderNames(List<String> headerNames) {
    StringBuilder formattedHeaderNames = new StringBuilder();
    headerNames.forEach(name -> {
      if (formattedHeaderNames.length() > 0) {
        formattedHeaderNames.append(" ");
      }
      formattedHeaderNames.append(name);
    });
    return formattedHeaderNames.toString();
  }
}
