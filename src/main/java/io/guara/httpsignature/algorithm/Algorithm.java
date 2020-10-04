package io.guara.httpsignature.algorithm;

/**
 * @author Rauffer
 *         This class specifies the algorithm contract.
 */
public abstract class Algorithm {

  /**
   * The algorithm name.
   */
  private String name;

  /**
   * The portable name used to display.
   */
  private String portableName;

  Algorithm(final String algorithmName, final String algorithmPortableName) {
    this.name = algorithmName;
    this.portableName = algorithmPortableName;
  }

  /**
   * Retrieves the algorithm name.
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the algorithm portable name.
   * @return the portable name
   */
  public String getPortableName() {
    return portableName;
  }

  /**
   * Indicates whether the algorithm is asymmetric.
   * @return true if is asymmetric, false if it is not
   */
  public abstract boolean isAsymmetric();

}
