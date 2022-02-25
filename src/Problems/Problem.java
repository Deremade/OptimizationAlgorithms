package Problems;

import Solution.OptSolution;

/**
 * @author David
 * 
 * an interface for defining the problems to be optimized
 *
 * @param <T>
 * @param <S>
 */
public interface Problem<T, S extends OptSolution<T, S>> {
	
	/**
	 * @param solution
	 * @return weather or not the solution is valid for the problem
	 */
	public boolean isValid(S solution);
	
	/**
	 * @param solution
	 * @return a human readable string
	 */
	public String solutionDetails(S solution);

	/**
	 * @param sol1
	 * @param sol2
	 * @return 
	 * 1 = sol1 is better
	 * -1 = sol2 is better
	 */
	int compare(S sol1, S sol2);
}
