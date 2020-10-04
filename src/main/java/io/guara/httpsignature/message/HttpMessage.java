package io.guara.httpsignature.message;

import java.util.ArrayList;
import java.util.List;

public class HttpMessage {

  private List<Header> headers;
  private Method method;
  private String requestPath;
  private Long createdAt;
  private Long expiresAt;

  public HttpMessage() {
    this.headers = new ArrayList<Header>();
  }

  public List<Header> getHeaders() {
    return headers;
  }

  public void setHeaders(List<Header> headers) {
    this.headers = headers;
  }

  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public String getRequestPath() {
    return requestPath;
  }

  public void setRequestPath(String requestPath) {
    this.requestPath = requestPath;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public Long getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Long expiresAt) {
    this.expiresAt = expiresAt;
  }

  public void addHeader(String name, String value) {
    this.headers.add(Header.of(name, value));
  }

  public void addHeader(Header header) {
    this.headers.add(header);
  }
  
  public static HttpMessageBuilder builder() {
  	return new HttpMessageBuilder();
  }

  public static class HttpMessageBuilder {
  	
  	private List<Header> headers = new ArrayList<>();
    private Method method;
    private String requestPath;
    private Long createdAt;
    private Long expiresAt;
    
		public HttpMessageBuilder createdAt(long createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public HttpMessageBuilder expiresAt(long expiresAt) {
			this.expiresAt = expiresAt;
			return this;
		}

		public HttpMessageBuilder withRequestMethod(Method method) {
			this.method = method;
			return this;
		}

		public HttpMessageBuilder withRequestPath(String path) {
			this.requestPath = path;
			return this;
		}

		public HttpMessageBuilder withHeader(String name, String value) {
			this.headers.add(Header.of(name, value));
			return this;
		}
		
		public HttpMessageBuilder withHeaders(List<Header> headers) {
			this.headers.addAll(headers);
			return this;
		}

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