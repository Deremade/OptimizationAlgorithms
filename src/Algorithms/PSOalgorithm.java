package Algorithms;

import java.util.Collection;

import Solution.OptimizationSolution;

public interface PSOalgorithm<E> {
	
	public Collection<SwarmParticle<E>> particles();
	
	public Collection<SwarmParticle<E>> particalize(Collection<OptimizationSolution<E>> solutions);
	
	public void addAsParticles();
	
	public default void changeAll() {
		for(SwarmParticle<E> sp : particles())
			sp.change(sp.calcMovement(particles()));
	}

}
