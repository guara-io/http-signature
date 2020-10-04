package io.guara.httpsignature.algorithm;

public class Algorithms {

  public static class Symmetric {
    public static final SymmetricAlgorithm HMAC_SHA256 = new SymmetricAlgorithm("HmacSHA256", "hmac-sha256");
    public static final SymmetricAlgorithm HMAC_SHA512 = new SymmetricAlgorithm("HmacSHA512", "hmac-sha512");
  }

  public static class Asymmetric {
    public static final AsymmetricAlgorithm RSA_SHA256 = new AsymmetricAlgorithm("SHA256withRSA", "rsa-sha256");
    public static final AsymmetricAlgorithm RSA_SHA384 = new AsymmetricAlgorithm("SHA384withRSA", "rsa-sha384");
    public static final AsymmetricAlgorithm RSA_SHA512 = new AsymmetricAlgorithm("SHA512withRSA", "rsa-sha512");
    public static final AsymmetricAlgorithm ECDSA_SHA256 = new AsymmetricAlgorithm("SHA256withECDSA", "ecdsa-sha256");
    public static final AsymmetricAlgorithm ECDSA_SHA512 = new AsymmetricAlgorithm("SHA512withECDSA", "ecdsa-sha512");
  }

	public static class HS2019 {

		public static Algorithm with(AsymmetricAlgorithm algorithm) {
			return new Algorithm(algorithm.getName(), "hs2019") {
				@Override
				public boolean isAsymmetric() {
					return algorithm.isAsymmetric();
				}
			};
		}

	}

}
