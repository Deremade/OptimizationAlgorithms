package Solution;

/** 
 * An interface for defining a general problem, including deciding how to score solutions and their value
 * 
 * @author David
 *
 * @param <E> Solution Element
 */
public interface Problem<E> {
	
	/**
	 * @param solution
	 * @return A human readable string to display the solution's details
	 */
	public String solutionDetails(OptimizationSolution<E> solution);
	
	/**
	 * @param solution
	 * @return boolean value of weather the solution is valid or not
	 */
	public boolean isValid(OptimizationSolution<E> solution);

	/**
	 * @return The chance the solution will change its size
	 */
	public double changeSizeChance();

	/**
	 * Compares two solutions and return weather or not sol0 is better than sol1
	 * @param sol0
	 * @param sol1
	 * @return
	 */
	public <S extends OptimizationSolution<E>> boolean compare(S sol0, S sol1);
}
