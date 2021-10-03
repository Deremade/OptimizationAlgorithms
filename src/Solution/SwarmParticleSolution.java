package Solution;

import Algorithms.SwarmParticle;

public 
class SwarmParticleSolution<E> extends AbstractSolution<E> implements SwarmParticle<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double nearDist, peronsalBestScale, localBestScale, overallBestScale, localWorstScale;
	OptimizationSolution<E> solution;
	SwarmParticle<E> personalbest;
	VectorOperations<E> vectorOptimization;

	public SwarmParticleSolution(AbstractSolution<E> as) {
		super(as.getProblem(), as.AA);
		solution = as;
	}

	@Override
	public VectorOperations<E> vectorOperations() {
		// TODO Auto-generated method stub
		return vectorOptimization;
	}

	@Override
	public OptimizationSolution<E> solution() {
		return solution;
	}

	@Override
	public SwarmParticle<E> personalBest() {
		return personalbest;
	}

	@Override
	public void setSolution(OptimizationSolution<E> newSolution) {
		solution = newSolution;
	}

	@Override
	public double nearDist() {
		return nearDist;
	}

	@Override
	public double personalBestScale() {
		return peronsalBestScale;
	}

	@Override
	public double localBestScale() {
		return localBestScale;
	}

	@Override
	public double overallBestscale() {
		return overallBestScale;
	}

	@Override
	public double localWorstScale() {
		return localWorstScale;
	}

}
