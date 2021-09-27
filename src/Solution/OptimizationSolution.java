package Solution;

import java.util.List;

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
	 * Changes the solution according to the specifics of the optimization algorithm
	 */
	public void change();
	
	/**
	 * Copies all data in this solution into the newSoltuion
	 * 
	 * @param newSolution a new solution (usually empty) which the data will be copied into
	 */
	default void copyInto(OptimizationSolution<E> newSolution) {
		newSolution.addAll(this);
	}
	
	/**
	 * Copies this solution into newSolution, and then performs the change() function on the newSolution
	 * 
	 * @param newSolution
	 */
	default void copyAndChange(OptimizationSolution<E> newSolution) {
		copyInto(this);
		newSolution.change();
	}
	
	/**
	 * @return an empty solution, (usually) a constructor
	 */
	OptimizationSolution<E> emptySolution();
	
	/**
	 * Creates an emptySolution(), Copies all data in this solution into the emptySolution()
	 */
	default void copyInto() {
		emptySolution().addAll(this);
	}
	
	/**
	 * Creates an emptySolution(), Copies this solution into emptySolution(), and then performs the change() function on the emptySolution()
	 * 
	 * @param newSolution
	 */
	default void copyAndChange() {
		copyInto(this);
		emptySolution().change();
	}
	
}
