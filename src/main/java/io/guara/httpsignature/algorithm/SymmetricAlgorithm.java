package io.guara.httpsignature.algorithm;

/**
 * @author Rauffer
 *
 *         This class implements a symmetric algorithm behavior.
 */
public class SymmetricAlgorithm extends Algorithm {

  SymmetricAlgorithm(final String name, final String portableName) {
    super(name, portableName);
  }

  /**
   * Indicates that the algorithm is symmetric.
   */
  @Override
  public boolean isAsymmetric() {
    return false;
  }

}
