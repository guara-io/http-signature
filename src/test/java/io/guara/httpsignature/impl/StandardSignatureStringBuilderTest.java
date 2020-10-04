package io.guara.httpsignature.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.guara.httpsignature.message.Header;

public class StandardSignatureStringBuilderTest {

  @Test
  public void mustCreateSigningStringUsingKeyValueAndBreakingLine() {
    
    List<Header> headers = Arrays.asList(Header.of("key1", "value 1"), Header.of("key2", "value 2"));
    String signingString = new StandardSignatureStringBuilder().build(headers);
    
    Assert.assertEquals("key1: value 1\nkey2: value 2", signingString);
    
  }
  
  @Test
  public void mustCreateEmptySignatureStringWhenHeaderListIsEmpty() {
   
    String signingString = new StandardSignatureStringBuilder().build(new ArrayList<Header>());
    
    Assert.assertEquals("", signingString);
    
  }
  
  @Test
  public void mustCreateEmptySignatureStringWhenHeaderListIsNull() {
   
    String signingString = new StandardSignatureStringBuilder().build(null);
    
    Assert.assertEquals("", signingString);
    
  }
  
}
