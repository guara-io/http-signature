package io.guara.httpsignature.algorithm;

public class SymmetricAlgorithm extends Algorithm {

  SymmetricAlgorithm(String name, String portableName) {
    super(name, portableName);
  }

	@Override
	public boolean isAsymmetric() {
		return false;
	}

}