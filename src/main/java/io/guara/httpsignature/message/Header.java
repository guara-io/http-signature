package io.guara.httpsignature.message;

/**
 * @author Rauffer
 *
 *         This class implements a header definition.
 */
public class Header {

  /**
   * The header name.
   */
  private String name;

  /**
   * The header value.
   */
  private String value;

  /**
   * Default constructor.
   */
  public Header() {
    // default
  }

  /**
   * Constructs a header with name and value set.
   *
   * @param name  the name
   * @param value the value
   */
  public Header(final String name, final String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Retrieves the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Overrides the name.
   *
   * @param name the name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Retrieves the value.
   *
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * Overrides the value.
   *
   * @param value the value
   */
  public void setValue(final String value) {
    this.value = value;
  }

  /**
   * Instantiate a header with its name and value.
   *
   * @param name the name
   * @param value the value
   * @return the header instance
   */
  public static Header of(final String name, final String value) {
    return new Header(name, value);
  }

}
