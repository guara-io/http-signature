package io.guara.httpsignature.message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rauffer
 *
 *         Defines the message details.
 */
public class HttpMessage {

  /**
   * Headers to be signed.
   */
  private List<Header> headers;

  /**
   * HTTP method of the request to be signed.
   */
  private Method method;

  /**
   * The path of the request to be signed. It must include query parameters.
   */
  private String requestPath;

  /**
   * The time of signature creation.
   */
  private Long createdAt;

  /**
   * The time the signature expires.
   */
  private Long expiresAt;

  /**
   * Default constructor.
   */
  public HttpMessage() {
    this.headers = new ArrayList<Header>();
  }

  /**
   * Retrieves the headers.
   *
   * @return the headers
   */
  public List<Header> getHeaders() {
    return headers;
  }

  /**
   * Overrides the headers.
   *
   * @param headers the new headers
   */
  public void setHeaders(final List<Header> headers) {
    this.headers = headers;
  }

  /**
   * Retrieves the request method.
   *
   * @return the method
   */
  public Method getMethod() {
    return method;
  }

  /**
   * Overrides the request method.
   *
   * @param method the method
   */
  public void setMethod(final Method method) {
    this.method = method;
  }

  /**
   * Retrieves the request path.
   *
   * @return the request path
   */
  public String getRequestPath() {
    return requestPath;
  }

  /**
   * Overrides the request path.
   *
   * @param requestPath the request path
   */
  public void setRequestPath(final String requestPath) {
    this.requestPath = requestPath;
  }

  /**
   * Retrieves the creation time.
   *
   * @return the creation time in milliseconds
   */
  public Long getCreatedAt() {
    return createdAt;
  }

  /**
   * Overrides the creation time.
   *
   * @param createdAt the creation time in milliseconds
   */
  public void setCreatedAt(final Long createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Retrieves the expiration time.
   *
   * @return the expiration time in milliseconds
   */
  public Long getExpiresAt() {
    return expiresAt;
  }

  /**
   * Overrides the expiration time.
   *
   * @param expiresAt the expiration time in milliseconds
   */
  public void setExpiresAt(final Long expiresAt) {
    this.expiresAt = expiresAt;
  }

  /**
   * Adds a header in the header list.
   *
   * @param name  the header name
   * @param value the header value
   */
  public void addHeader(final String name, final String value) {
    this.headers.add(Header.of(name, value));
  }

  /**
   * Adds a header in the header list.
   *
   * @param header the header
   */
  public void addHeader(final Header header) {
    this.headers.add(header);
  }

  /**
   * Instantiates a builder.
   *
   * @return the builder
   */
  public static HttpMessageBuilder builder() {
    return new HttpMessageBuilder();
  }

  /**
   * @author Rauffer
   *
   *         This class implements a builder of HttpMessage objects.
   */
  public static class HttpMessageBuilder {

    /**
     * The headers.
     */
    private List<Header> headers = new ArrayList<>();

    /**
     * The http method.
     */
    private Method method;

    /**
     * The request path.
     */
    private String requestPath;

    /**
     * The creation time.
     */
    private Long createdAt;

    /**
     * The expiration time.
     */
    private Long expiresAt;

    /**
     * Sets the creation time.
     *
     * @param createdAt the creation time in milliseconds
     * @return itself
     */
    public HttpMessageBuilder createdAt(final Long createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    /**
     * Sets the expiration time.
     *
     * @param expiresAt the expiration time in milliseconds
     * @return itself
     */
    public HttpMessageBuilder expiresAt(final Long expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    /**
     * Sets the http method.
     *
     * @param method the method
     * @return itself
     */
    public HttpMessageBuilder withRequestMethod(final Method method) {
      this.method = method;
      return this;
    }

    /**
     * Sets the request path.
     *
     * @param path the request path
     * @return itself
     */
    public HttpMessageBuilder withRequestPath(final String path) {
      this.requestPath = path;
      return this;
    }

    /**
     * Adds a header in the list.
     *
     * @param name the name
     * @param value the value
     * @return itself
     */
    public HttpMessageBuilder withHeader(final String name, final String value) {
      this.headers.add(Header.of(name, value));
      return this;
    }

    /**
     * Adds headers in the list.
     *
     * @param headers the headers
     * @return itself
     */
    public HttpMessageBuilder withHeaders(final List<Header> headers) {
      this.headers.addAll(headers);
      return this;
    }

    /**
     * Builds the message.
     *
     * @return the message
     */
    public HttpMessage build() {
      HttpMessage message = new HttpMessage();
      message.setCreatedAt(createdAt);
      message.setExpiresAt(expiresAt);
      message.setMethod(method);
      message.setRequestPath(requestPath);
      message.setHeaders(headers);
      return message;
    }

  }

}
