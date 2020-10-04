package io.guara.httpsignature.algorithm;

public abstract class Algorithm {
  
  private String name;
  private String portableName;

  Algorithm(String name, String portableName) {
    this.name = name;
    this.portableName = portableName;
  }
  
  public String getName() {
    return name;
  }

  public String getPortableName() {
    return portableName;
  }

  public abstract boolean isAsymmetric();

}