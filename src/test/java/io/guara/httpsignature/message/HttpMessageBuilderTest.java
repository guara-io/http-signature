package io.guara.httpsignature.message;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class HttpMessageBuilderTest {
	
	@Test
	public void mustBuildHttpMessageWithAllProperties() {
		
		Long createdAt = 1601740997L;
		Long expiresAt = 1601741053L;
		
		HttpMessage message = HttpMessage.builder()
				.createdAt(createdAt)
				.expiresAt(expiresAt)
				.withRequestMethod(Method.GET)
				.withRequestPath("/foo")
				.withHeader("header1", "1")
				.withHeader("header2", "2")
				.withHeaders(Arrays.asList(Header.of("header3", "3")))
				.build();
		
		Assert.assertEquals(createdAt, message.getCreatedAt());
		Assert.assertEquals(expiresAt, message.getExpiresAt());
		Assert.assertEquals(Method.GET, message.getMethod());
		Assert.assertEquals("/foo", message.getRequestPath());
		Assert.assertEquals(3, message.getHeaders().size());
		Assert.assertEquals("1", message.getHeaders().get(0).getValue());
		Assert.assertEquals("2", message.getHeaders().get(1).getValue());
		Assert.assertEquals("3", message.getHeaders().get(2).getValue());
		
	}

}
