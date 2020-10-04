# Guara http signature for Java
###### Simple to use and easy to extend!

Are you looking for a simple java library to help you sign http messages?   
You found the right one!

**Guara http signature** makes http signature creation and validation* simple for you. 

## How to use it?

You just need to add a maven dependency:

```xml
<dependency>
	<groupId>io.guara</groupId>
	<artifactId>http-signature</artifactId>
	<version>1.0.0</version>
</dependency>
```

### Examples

The example below is compatible with [draft 10](https://tools.ietf.org/html/draft-cavage-http-signatures-10) of http signature specification.

```java
Key key = loadRsaPrivateKey(); // up to you to implement it or use a lib you trust :)

Signer signer = SignerFactory.getInstance().getStandarSigner(key); // instantiating your signer

// preparing the message to be signed
HttpMessage message = HttpMessage.builder()
		.withRequestMethod(Method.GET)
		.withRequestPath("/foo")
		.withHeader("header-1", "1")
		.withHeader("header-2", "2")
		.build();

Algorithm algorithm = Algorithms.Asymmetric.RSA_SHA256; // we are going to sign using algorithm SHA-256

HttpSignature httpSignature = signer.sign(algorithm, "my-key", message);

String formattedSignature = httpSignature.getFormatted(); // here it is!
```

You may have heard the new updates on http signature specification in [draft 12](https://tools.ietf.org/html/draft-cavage-http-signatures-12).

So, yes, we support it ;)

```java
long creationTime = System.currentTimeMillis(); // when the signature has been calculated (now :))
long expirationTime = creationTime + 300000; // will expire in 5 minutes

HttpMessage message = HttpMessage.builder()
		.createdAt(creationTime) // adds (created) pseudo-header
		.expiresAt(expirationTime) // adds (expires) pseudo-header
		.withRequestMethod(Method.GET)
		.withRequestPath("/foo")
		.withHeader("header-1", "1")
		.withHeader("header-2", "2")
		.build();
		
Algorithm algorithm = Algorithms.HS2019.with(Algorithms.Asymmetric.RSA_SHA256); // we are going to sign using algorithm SHA-256, but in the signature definition, we show hs2019. Lovely, isn't it? 

HttpSignature httpSignature = signer.sign(algorithm, "my-key", message);
```

Wait, haven't heard about hs2019 algorithm? [Check out the specification](https://tools.ietf.org/html/draft-cavage-http-signatures-12) and get yourself updated.

A detailed explanation is coming soon in http://httpsignature.com

### What's next?

We are working on signature validation. 

### Want to contribute?

You found a bug, need a new feature, a new algorithm or just improve something?  
We'd love to hear from you and/or check your pull request!

## License

**Guara http signature** is free and open source (MIT License). Use it as you wish.

[Read License](https://github.com/anotheria/moskito/blob/master/LICENSE)