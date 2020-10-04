package io.guara.httpsignature.impl;

import static java.lang.String.format;

import java.util.List;

import io.guara.httpsignature.SignatureStringBuilder;
import io.guara.httpsignature.message.Header;

/**
 * @author Rauffer
 *
 *         This class implements a default signature string builder.
 */
public class StandardSignatureStringBuilder implements SignatureStringBuilder {

  /**
   * The header declaration format.
   */
  private static final String LINE_FORMAT = "%s: %s";

  /**
   * Empty value.
   */
  private static final String EMPTY = "";

  /**
   * Builds a signature string.
   *
   * @param headers headers
   * @return the string to be signed
   */
  @Override
  public String build(final List<Header> headers) {

    if (headers == null || headers.isEmpty()) {
      return EMPTY;
    }

    StringBuilder signingString = new StringBuilder();

    headers.forEach(header -> {
      String headerName = header.getName().toLowerCase();
      String line = format(LINE_FORMAT, headerName, header.getValue());
      if (signingString.length() > 0) {
        signingString.append("\n");
      }
      signingString.append(line);
    });

    return signingString.toString();
  }

}
