package io.guara.httpsignature.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;

/**
 * For testing purposes only. Do not take it as example for your application!
 */
public class X509Utils {
	
	private X509Utils() {
		// hidden
	}

	public static X509Utils getInstance() {
		return new X509Utils();
	}
	
  public PrivateKey loadPrivateKey(String fileName) {

    try {

      Path path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
      String privateKeyContent = new String(Files.readAllBytes(path)).replaceAll("[\\n\\r]", "")
          .replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");

      privateKeyContent = privateKeyContent
          .replaceAll("(-+BEGIN RSA PRIVATE KEY-+\\r?\\n|-+END RSA PRIVATE KEY-+\\r?\\n?)", "");

      return loadPrivateKey(Base64.getDecoder().decode(privateKeyContent));

    } catch (Exception e) {
      throw new RuntimeException(e); // don't do it in real life :)
    }
  }
  
  private PrivateKey loadPrivateKey(final byte[] pem) {
		try {
			RSAPrivateCrtKeySpec spec = getSpec(pem);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return factory.generatePrivate(spec);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private RSAPrivateCrtKeySpec getSpec(final byte[] pem) throws IOException {

		DerParser parser = new DerParser(pem);
		Asn1Object sequence = parser.read();

		DerParser p = sequence.getParser();

		p.read();

		BigInteger modulus = p.read().getInteger();
		BigInteger publicExponent = p.read().getInteger();
		BigInteger privateExponent = p.read().getInteger();
		BigInteger primeP = p.read().getInteger();
		BigInteger primeQ = p.read().getInteger();
		BigInteger primeExponentP = p.read().getInteger();
		BigInteger primeExponentQ = p.read().getInteger();
		BigInteger crtCoefficient = p.read().getInteger();

		return new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP,
		    primeExponentQ, crtCoefficient);
	}

	class Asn1Object {

		protected final byte[] value;

		public Asn1Object(byte[] value) {
			this.value = value;
		}

		public DerParser getParser() throws IOException {
			return new DerParser(value);
		}

		public BigInteger getInteger() throws IOException {
			return new BigInteger(value);
		}

	}

	class DerParser {

		protected final InputStream in;

		public DerParser(final InputStream in) throws IOException {
			this.in = in;
		}

		public DerParser(final byte[] bytes) throws IOException {
			this(new ByteArrayInputStream(bytes));
		}

		public Asn1Object read() throws IOException {
			in.read();

			final int length = getLength();

			final byte[] value = new byte[length];
			in.read(value);

			return new Asn1Object(value);
		}

		private int getLength() throws IOException {

			final int i = in.read();

			if ((i & ~0x7F) == 0)
				return i;

			final int num = i & 0x7F;

			final byte[] bytes = new byte[num];
			in.read(bytes);

			return new BigInteger(1, bytes).intValue();
			
		}
	}

}
