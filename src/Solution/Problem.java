package Solution;

/** 
 * An interface for defining a general problem, including deciding how to score solutions and their value
 * 
 * @author David
 *
 * @param <E> Solution Element
 */
public interface Problem<E, S extends OptimizationSolution<E>> {

	/**
	 * @param solution
	 * @return the value of the solution
	 */
	public double value(S solution);
	
	/**
	 * @param solution
	 * @return A human readable string to display the solution's details
	 */
	public String solutionDetails(S solution);
	
	/**
	 * @param solution
	 * @return boolean value of weather the solution is valid or not
	 */
	public boolean isValid(S solution);

	public double changeSizeChance();
}
