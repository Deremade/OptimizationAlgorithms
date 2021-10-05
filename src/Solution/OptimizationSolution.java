package Solution;

import java.util.List;

import Solution.Mutation.mutate;

/**
 *  Defines the methods for interacting with a "Solution" to an optimization problem.
 *  
 * @author David
 *
 * @param <E> The Elements of the solution
 * 

 */
 
public interface OptimizationSolution<E> extends List<E> {

	/**
	 * @return Return the value or fitness of the solution so that it can be compared as better or worse than other solutions
	 */
	public double value();
	
	/**
	 * @return A string to make the solution human readable instead of just an arbitrary list of Elements
	 */
	public String solutionDetails();
	
	/**
	 * @return boolean value [true. false] if the solution is valid as solution
	 */
	public boolean isValid();
	
	/**
	 * Function to make the Solution invalid
	 */
	public void makeInvalid();
	
	/**
	 * @return an empty solution, (usually) a constructor
	 */
	OptimizationSolution<E> emptySolution();
	
}
