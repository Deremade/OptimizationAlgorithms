package Solution;

import java.util.Collection;

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
}
