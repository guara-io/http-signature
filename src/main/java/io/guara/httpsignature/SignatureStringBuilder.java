package io.guara.httpsignature;

import java.util.List;

import io.guara.httpsignature.message.Header;

/**
 * @author Rauffer
 *
 *         Specifies the signature string builder contract.
 */
public interface SignatureStringBuilder {

  /**
   * Builds a signature string.
   *
   * @param headers headers
   * @return the string to be signed
   */
  String build(List<Header> headers);

}
