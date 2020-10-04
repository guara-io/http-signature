package io.guara.httpsignature;

import java.util.List;

import io.guara.httpsignature.message.Header;

public interface SignatureStringBuilder {

  String build(List<Header> headers);
  
}
