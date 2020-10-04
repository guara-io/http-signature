package io.guara.httpsignature.impl;

import java.util.List;

import io.guara.httpsignature.algorithm.Algorithm;
import io.guara.httpsignature.message.HttpMessage;

/**
 * @author Rauffer
 *
 *         This class implements the details of signature format.
 */
public class SignatureFormatter {

  /**
   * The keyId property structure.
   */
  private static final String KEY_ID = "keyId=\"%s\",";

  /**
   * The algorithm property structure.
   */
  private static final String ALGORITHM = "algorithm=\"%s\",";

  /**
   * the creation time property structure.
   */
  private static final String CREATED = "created=%s,";

  /**
   * the expiration time property structure.
   */
  private static final String EXPIRES = "expires=%s,";

  /**
   * the header property structure.
   */
  private static final String HEADERS = "headers=\"%s\",";

  /**
   * the signature property structure.
   */
  private static final String SIGNATURE = "signature=\"%s\"";

  /**
   * Formats the signature string.
   *
   * @param algorithm   the algorithm
   * @param keyId       the key id
   * @param message     the message
   * @param headerNames the header names
   * @param signature   the signature
   * @return the formatted signature string
   */
  String format(final Algorithm algorithm, final String keyId, final HttpMessage message, final List<String> headerNames, final String signature) {

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

  private String getFormattedHeaderNames(final List<String> headerNames) {
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
