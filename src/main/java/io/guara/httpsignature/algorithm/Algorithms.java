package io.guara.httpsignature.algorithm;

/**
 * @author Rauffer
 *
 *         Declares the algorithms supported by default.
 */
public class Algorithms {

  /**
   * Defines symmetric algorithms.
   */
  public static class Symmetric {

    /**
     * HMAC SHA-256 algorithm.
     */
    public static final Algorithm HMAC_SHA256 = new SymmetricAlgorithm("HmacSHA256", "hmac-sha256");

    /**
     * HMAC SHA-512 algorithm.
     */
    public static final Algorithm HMAC_SHA512 = new SymmetricAlgorithm("HmacSHA512", "hmac-sha512");

  }

  /**
   * Defines asymmetric algorithms.
   */
  public static class Asymmetric {

    /**
     * RSA SHA-256 algorithm.
     */
    public static final Algorithm RSA_SHA256 = new AsymmetricAlgorithm("SHA256withRSA", "rsa-sha256");

    /**
     * RSA SHA-384 algorithm.
     */
    public static final Algorithm RSA_SHA384 = new AsymmetricAlgorithm("SHA384withRSA", "rsa-sha384");

    /**
     * RSA SHA-512 algorithm.
     */
    public static final Algorithm RSA_SHA512 = new AsymmetricAlgorithm("SHA512withRSA", "rsa-sha512");

    /**
     * ECDSA SHA-256 algorithm.
     */
    public static final Algorithm ECDSA_SHA256 = new AsymmetricAlgorithm("SHA256withECDSA", "ecdsa-sha256");

    /**
     * ECDSA SHA-512 algorithm.
     */
    public static final Algorithm ECDSA_SHA512 = new AsymmetricAlgorithm("SHA512withECDSA", "ecdsa-sha512");
  }

  /**
   * Defines hs2019 algorithm.
   */
  public static class HS2019 {

    /**
     * Specifies the algorithm to be used to sign the string.
     *
     * @param algorithm the algorithm
     * @return itself
     */
    public static Algorithm with(final Algorithm algorithm) {
      return new Algorithm(algorithm.getName(), "hs2019") {
        @Override
        public boolean isAsymmetric() {
          return algorithm.isAsymmetric();
        }
      };
    }

  }

}
