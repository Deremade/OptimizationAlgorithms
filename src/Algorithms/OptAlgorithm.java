package Algorithms;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Solution.OptSolution;
import staticMethods.SolutionMethods;

/**
 * @author David
 *
 *The interface to define the basic methods of every optimization algorithm
 *
 * @param <T> Element Type
 * @param <S> Solution
 */
public interface OptAlgorithm<T, S extends OptSolution<T, S>> {
	
	/**
	 * @return a randomly generated 'solution'
	 */
	public S randomSolution();
	
	/**
	 * A collection of all solutions the algorithm remembers
	 * @return all currently generated solutions
	 */
	public Collection<S> solutions();
	
	/**
	 * Adds a number of randomly generated solutions to an existing collection
	 * @param solutions
	 * @param numOfsolutions
	 */
	public default void generateSolutions(Collection<S> solutions, int numOfsolutions){
		while(solutions.size() < numOfsolutions) {
			solutions.add(randomSolution());
		}
	}
	
	/**
	 * Removes all invalid solutions from a collection of solutions
	 * @param solutions
	 */
	public default void removeInvalid(Collection<S> solutions) {
		//loop through the list to remove all invalid
		for(S os : solutions) {
			if(!os.isValid())
				solutions.remove(os);
		}
	}
	
	/**
	 * @param solutions
	 * Sample of solutions to sort
	 * @return a Sorted List of solutions
	 */
	public default List<S> sortedSolutions(Collection<S> solutions){
		//Borrow sorting algorithm from SolutionMethod
		return SolutionMethods.sort(solutions);
	}
	
	/**
	 * @param solutions
	 * the Sample of solutions to draw from to find most fit
	 * @param size
	 * How many solutions to return
	 * @return a list of the most fit solutions
	 */
	public default LinkedList<S> mostFit(Collection<S> solutions, int size) {
		//Create list to hold the fit solutions
		LinkedList<S> mostFitsolutions = new LinkedList<S>();
		//Sort the existing solutions
		List<S> sortedsolutions = sortedSolutions(solutions);
		//While we have yet to find the needed number of solutions
		while(mostFitsolutions.size() < size) {
			//add at index
			mostFitsolutions.add(sortedsolutions.get(mostFitsolutions.size()));
		}
		return mostFitsolutions;
	}
	
	/**
	 * @param solutions
	 * @return The most fit solution
	 */
	public default S mostFitsolution(Collection<S> solutions) {
		return sortedSolutions(solutions).get(0);
	}
	
	public default String printSolutions(Collection<S> solutions) {
		String s = "<";
		for(S os : solutions) {
			s += os.solutionDetails()+",";
		}
		return s.substring(0, s.length()-1);
	}
	
	/**
	 * Complete 1 iteration of the Optimization
	 */
	public void iteration();
	
	/**
	 * Complete multiple iterations of the Optimization
	 * @param cycles
	 * The number of iterations
	 */
	public default void iteration(int cycles) {
		for(int i = 0; i < cycles; i++)
			iteration();
	}
}
