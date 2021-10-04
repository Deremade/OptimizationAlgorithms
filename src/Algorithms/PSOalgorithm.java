package Algorithms;

import java.util.Collection;
import java.util.LinkedList;

import Solution.OptimizationSolution;
import Solution.VectorOperations;

public interface PSOalgorithm<E> {

	public VectorOperations<E, ?> vectorOperations();
	
	public default void changeAll(Collection<OptimizationSolution<E>> solutions) {
		for(OptimizationSolution<E> sp : solutions) {
			//System.out.println("Before "+sp.solutionDetails());
			OptimizationSolution<E> newSolution = vectorOperations().addAllTo(calcMovement(solutions, sp), sp);
			sp.clear();
			sp.addAll(newSolution);
			//System.out.println("After "+sp.solutionDetails());
		}
	}

	
	public double nearDist();
	public double localBestScale();
	public double overallBestscale();
	public double localWorstScale();
	public double curVelScale();

	
	public default Collection<OptimizationSolution<E>> calcMovement(Collection<OptimizationSolution<E>> particles, OptimizationSolution<E> mover) {
		Collection<OptimizationSolution<E>> movement = new LinkedList<OptimizationSolution<E>>();
		Collection<OptimizationSolution<E>> nearbySolutions = vectorOperations().nearbySolutions(particles, mover, nearDist());
		movement.add(vectorOperations().scaleSolution(
				vectorOperations().difference(vectorOperations().bestSolution(nearbySolutions), mover)
				, localBestScale()));
		movement.add(vectorOperations().scaleSolution(
				vectorOperations().difference(vectorOperations().worstSolution(nearbySolutions), mover)
				, localWorstScale()));
		movement.add(vectorOperations().scaleSolution(
				vectorOperations().difference(vectorOperations().bestSolution(particles), mover)
				, overallBestscale()));
		movement.add(vectorOperations().scaleSolution(
				mover
				, curVelScale()));
		return movement;
	}
}
