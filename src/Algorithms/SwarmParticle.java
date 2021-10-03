package Algorithms;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import Solution.OptimizationSolution;
import Solution.VectorOperations;
import staticMethods.SolutionRanker;

public interface SwarmParticle<E> extends OptimizationSolution<E>{
	
	VectorOperations<E> vectorOperations();
	
	OptimizationSolution<E> solution();

	public SwarmParticle<E> personalBest();
	
	public void setSolution(OptimizationSolution<E> newSolution);
	
	public double nearDist();
	public double personalBestScale();
	public double localBestScale();
	public double overallBestscale();
	public double localWorstScale();
	
	public default Collection<SwarmParticle<E>> nearbySolutions(Collection<SwarmParticle<E>> particles) {
		Collection<SwarmParticle<E>> nearby = new LinkedList<SwarmParticle<E>>();
		for(SwarmParticle<E> particle : particles) 
			if(vectorOperations().distance(particle, this) <= nearDist())
				nearby.add(particle);
		
		return nearby;
	}
	
	public default SwarmParticle<E> localBest(Collection<SwarmParticle<E>> nearbyParticles) {
		SwarmParticle<E> best = null;
		for(SwarmParticle<E> sp : nearbyParticles) 
			if(best == null) best = sp;
			else
				if(sp.solution().value() > best.solution().value())
					best = sp;
		
		return best;
	}
	
	public default SwarmParticle<E> localWorst(Collection<SwarmParticle<E>> nearbyParticles) {
		SwarmParticle<E> worst = null;
		for(SwarmParticle<E> sp : nearbyParticles) 
			if(worst == null) worst = sp;
			else
				if(sp.solution().value() < worst.solution().value())
					worst = sp;
		
		return worst;
	}
	
	public default Collection<OptimizationSolution<E>> calcMovement(Collection<SwarmParticle<E>> particles) {
		Collection<OptimizationSolution<E>> movement = new LinkedList<OptimizationSolution<E>>();
		movement.add(vectorOperations().scaleSolution(
				vectorOperations().difference(localBest(nearbySolutions(particles)), this)
				, localBestScale()));
		movement.add(vectorOperations().scaleSolution(
				vectorOperations().difference(localWorst(nearbySolutions(particles)), this)
				, localWorstScale()));
		movement.add(vectorOperations().scaleSolution(
				vectorOperations().difference(personalBest(), this)
				, personalBestScale()));
		movement.add(vectorOperations().scaleSolution(
				vectorOperations().difference(localBest(particles), this)
				, overallBestscale()));
		return movement;
	}
	
	public default void change(Collection<OptimizationSolution<E>> movement) {
		movement.add(this);
		if(vectorOperations().addSolutions(movement).isValid())
			setSolution(vectorOperations().addSolutions(movement));
	}
	
	/**
	 * @return Return the value or fitness of the solution so that it can be compared as better or worse than other solutions
	 */
	public default double value() {
		return solution().value();
	}
	
	/**
	 * @return A string to make the solution human readable instead of just an arbitrary list of Elements
	 */
	public default String solutionDetails()  {
		return solution().solutionDetails();
	}
	
	/**
	 * @return boolean value [true. false] if the solution is valid as solution
	 */
	public default boolean isValid()  {
		return solution().isValid();
	}
	
	/**
	 * Function to make the Solution invalid
	 */
	public default void makeInvalid()  {
		solution().makeInvalid();
	}
	
	/**
	 * @return an empty solution, (usually) a constructor
	 */
	default OptimizationSolution<E> emptySolution()  {
		return solution().emptySolution();
	}
}
