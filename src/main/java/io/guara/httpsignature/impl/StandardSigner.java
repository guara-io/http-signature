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

/**
 * @author Rauffer
 *
 *         This class implements a standard signer.
 */
public class StandardSigner implements Signer {

  /**
   * The key to be used to perform signature.
   */
  private Key key;

  /**
   * The signature formatter.
   */
  private SignatureFormatter formatter;

  /**
   * The signature string builder.
   */
  private SignatureStringBuilder signatureStringBuilder;

  /**
   * Constructs a standard signer with a key.
   * @param key the key
   */
  public StandardSigner(final Key key) {
    this.key = key;
    this.formatter = new SignatureFormatter();
    this.signatureStringBuilder = new StandardSignatureStringBuilder();
  }

  /**
   * Signs a message.
   *
   * @param algorithm the algorithm
   * @param keyId     the key id
   * @param message   the message
   * @return the signature details
   */
  @Override
  public HttpSignature sign(final Algorithm algorithm, final String keyId, final HttpMessage message) {

    List<Header> headersToSign = determineHeadersToSign(message);
    String signingString = signatureStringBuilder.build(headersToSign);
    StringSigner stringSigner = getStringSigner(algorithm);

    String signature = stringSigner.sign(algorithm, key, signingString);

    HttpSignatureImpl signatureResult = new HttpSignatureImpl();

    List<String> headerNames = headersToSign.stream()
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

  /**
   * Determines the headers to be signed.
   * @param message the message
   * @return the headers
   */
  List<Header> determineHeadersToSign(final HttpMessage message) {

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

  /**
   * Determines with StringSigner to be used based on the algorithm.
   * @param algorithm the algorithm
   * @return the StringSigner
   */
  StringSigner getStringSigner(final Algorithm algorithm) {

    if (algorithm.isAsymmetric()) {
      return AsymmetricStringSigner.getInstance();
    }

    return SymmetricStringSigner.getInstance();

  }

}
