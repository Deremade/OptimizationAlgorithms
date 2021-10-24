package Solution;

import java.util.Collection;
import java.util.List;

import Solution.Mutation.mutate;
import staticMethods.SolutionMethods;

/**
 *  Defines the methods for interacting with a "Solution" to an optimization problem.
 *  
 * @author David
 *
 * @param <E> The Elements of the solution
 * 

 */
 
public interface OptimizationSolution<E> {

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
	<S extends OptimizationSolution<E>> S emptySolution();
	
	/**
	 * Replace the current element at a location with a new element
	 * @param elm
	 * The element the position is being set to
	 * @param placeCode
	 * The position or "place code" of the element being replaced
	 */
	public void setElm(E elm, String placeCode);
	
	/**
	 * Place an element at a place code (move other elements around as necessary)
	 * @param elm
	 * The newly inserted element
	 * @param placeCode
	 * The place it will be inserted
	 */
	public void placeElm(E elm, String placeCode);
	
	/**
	 * Find the place code of the element
	 * @param elm
	 * @return the place code of the element
	 */
	public String findElm(E elm);
	
	/**
	 * Get an element based on it's place code
	 * @param placeCode
	 * @return the element at the place code
	 */
	public E getElm(String placeCode);
	
	/**
	 * @return a collection of all place codes
	 */
	public Collection<String> placeCodes();
	
	public boolean hasPlaceCode(String placeCode);
	
	public Collection<String> emptyPlaceCodes();
	
	public default String emptyPlaceCode() {
		return SolutionMethods.getRandom(emptyPlaceCodes());
	}
	
	/**
	 * Find the place code of the element in another solution, and set the place code in this solution to that element
	 * @param elm
	 * @param solution
	 */
	public default <S extends OptimizationSolution<E>> void setElmFrom(E elm, S solution) {
		setElm(elm, solution.findElm(elm));
	}
	
	/**
	 * Find the element at the place code of the other solution and set this solution's place code to hold that element
	 * @param placeCode
	 * @param solution
	 */
	public default <S extends OptimizationSolution<E>> void setElmFrom(String placeCode, S solution) {
		setElm(solution.getElm(placeCode), placeCode);
	}
	

	/**
	 * Place an element from another solution at the same place code
	 * @param elm
	 * @param solution
	 */
	public default <S extends OptimizationSolution<E>> void placeElmFrom(E elm, S solution) {
		placeElm(elm, solution.findElm(elm));
	}
	
	/**
	 * Place the element held at the place code of another solution at that same place code in this solution
	 * @param placeCode
	 * @param solution
	 */
	public default <S extends OptimizationSolution<E>> void placeElmFrom(String placeCode, S solution) {
		placeElm(solution.getElm(placeCode), placeCode);
	}
	
	/**
	 * Remove an item at their place code
	 * @param placeCode
	 * The place code of the item being removed
	 * @return false if no such place code exists
	 */
	public boolean removeItem(String placeCode);
}
