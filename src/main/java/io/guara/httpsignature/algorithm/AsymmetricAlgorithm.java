package io.guara.httpsignature.algorithm;

/**
 * @author Rauffer
 *
 *         This class implements an asymmetric algorithm behavior.
 */
public class AsymmetricAlgorithm extends Algorithm {

  AsymmetricAlgorithm(final String name, final String portableName) {
    super(name, portableName);
  }

  /**
   * Indicates that the algorithm is asymmetric.
   */
  @Override
  public boolean isAsymmetric() {
    return true;
  }

}
