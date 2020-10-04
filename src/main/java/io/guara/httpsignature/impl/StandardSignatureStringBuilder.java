package io.guara.httpsignature.impl;

import static java.lang.String.format;

import java.util.List;

import io.guara.httpsignature.SignatureStringBuilder;
import io.guara.httpsignature.message.Header;

public class StandardSignatureStringBuilder implements SignatureStringBuilder {

  private static final String LINE_FORMAT = "%s: %s";
  private static final String EMPTY = "";

  @Override
  public String build(List<Header> headers) {
    
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
