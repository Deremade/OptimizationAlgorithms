package Algorithms;

import java.util.Collection;

import Solution.OptimizationSolution;

/**
 * The interface to run an optimization algorithm
 * 
 * @author David
 *
 * @param <E> The Elements of the solution
 */
public interface OptAlgorithm<E> {
	
	/**
	 * @return a random solution
	 */
	public OptimizationSolution<E> randomSolution();
	
	/**
	 * @return a list of all solutions
	 */
	public Collection<OptimizationSolution<E>> solutions();
	
	/**
	 * Fills the collection of solutions with {size} random solutions
	 * 
	 * @param size how many solutions generated
	 */
	public void fillSolutions(int size);
	
	/**
	 * Generates a number of random solutions and puts them in an existing collection
	 * 
	 * @param solutions the collection that will store the solutions
	 * @param numOfsolutions the number of solutions
	 */
	public default void generateSolutions(Collection<OptimizationSolution<E>> solutions, int numOfsolutions){
		while(solutions.size() < numOfsolutions) {
			solutions.add(randomSolution());
		}
	}
	
	/**
	 * Generates a number of random solutions and puts them in the main collection
	 * 
	 * @param numOfsolutions the number of solutions
	 */
	public default void generateSolutions(int numOfsolutions){
		while(solutions().size() < numOfsolutions) {
			solutions().add(randomSolution());
		}
	}
	
	/**
	 * prunes a collection of solutions of all invalid options
	 * 
	 * @param solutions - a collection to be pruned
	 */
	public default void removeInvalid(Collection<OptimizationSolution<E>> solutions) {
		for(OptimizationSolution<E> os : solutions) {
			if(!os.isValid())
				solutions.remove(os);
		}
	}
	
	/**
	 * runs a single iteration of the optimization algorithm
	 */	
	public void iteration();
	
	/**
	 * Runs a number of iterations
	 * 
	 * @param iterations
	 * how many iterations are run
	 */
	public default void iterations(int iterations) {
		while(iterations > 0) {
			iteration();
			iterations--;
		}
	}
	

	
	/**
	 * @return a String representing all solutions currently considered by the algorithm
	 */
	public default String displaySolutions() {
		String s = "<";
		for(OptimizationSolution<E> solution : solutions()) {
			s += solution.solutionDetails() + ",";
		}
		return s.substring(0, s.length()-1)+">";
	}
}
