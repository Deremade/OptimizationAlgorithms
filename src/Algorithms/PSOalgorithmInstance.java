package Algorithms;
import Solution.IndexedSolution;
import Solution.VectorOperations;

public class PSOalgorithmInstance<E> implements PSOalgorithm<E> {
	double nearDist, 
	localBestScale,
	overallBestscale,
	localWorstScale,
	curVelScale;
	
	public PSOalgorithmInstance(double nearDist, double localBestScale, double overallBestscale, double localWorstScale,
			double curVelScale, VectorOperations<E, ?> vo) {
		super();
		this.nearDist = nearDist;
		this.localBestScale = localBestScale;
		this.overallBestscale = overallBestscale;
		this.localWorstScale = -localWorstScale;
		this.curVelScale = curVelScale;
		this.vo = vo;
	}
	
	VectorOperations<E, ?> vo;

	@Override
	public VectorOperations<E, ?> vectorOperations() {
		// TODO Auto-generated method stub
		return vo;
	}

	@Override
	public double nearDist() {
		// TODO Auto-generated method stub
		return nearDist;
	}


	@Override
	public double localBestScale() {
		// TODO Auto-generated method stub
		return localBestScale;
	}

	@Override
	public double overallBestscale() {
		// TODO Auto-generated method stub
		return overallBestscale;
	}

	@Override
	public double localWorstScale() {
		// TODO Auto-generated method stub
		return localWorstScale;
	}

	@Override
	public double curVelScale() {
		// TODO Auto-generated method stub
		return curVelScale;
	}

}
