package Algorithms.ParticleSwarmOptimization;

import java.util.Collection;

import Algorithms.OptAlgorithm;
import Solution.OptSolution;

public interface PSOAlgorithm<T, S extends OptSolution<T, S>> extends OptAlgorithm<T, S> {
	
	public ParticleMovement<T, S> particleMovement();
	
	OptAlgorithm<T, S> optAlgorithm();

	@Override
	public default void iteration() {
		particleMovement().moveAll(solutions());
	}

	@Override
	public default S randomSolution() {
		return optAlgorithm().randomSolution();
	}

	@Override
	public default Collection<S> solutions() {
		return optAlgorithm().solutions();
	}

}
