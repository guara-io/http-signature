package io.guara.httpsignature.algorithm;

public class AsymmetricAlgorithm extends Algorithm {

  AsymmetricAlgorithm(String name, String portableName) {
    super(name, portableName);
  }

	@Override
	public boolean isAsymmetric() {
		return true;
	}

}