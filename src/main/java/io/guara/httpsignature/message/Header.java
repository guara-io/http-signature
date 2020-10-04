package io.guara.httpsignature.message;

public class Header {

  private String name;
  private String value;

  public Header() {
    // default
  }

  public Header(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public static Header of(String name, String value) {
    return new Header(name, value);
  }

}
