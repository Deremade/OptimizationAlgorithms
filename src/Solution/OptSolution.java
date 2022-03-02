package Solution;

import java.util.Collection;

import staticMethods.CollectionMethods;

/**
 * @author David
 * 
 * An interface for defining the methods which are used by every solution to be optimized
 *
 * @param <T>
 * @param <S>
 */
public interface OptSolution<T, S extends OptSolution<T, S>> extends Comparable<S>{
	
	S clone();
	
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
	S emptySolution();
	
	/**
	 * Replace the current element at a location with a new element [makes new placeCode if needed]
	 * @param elm
	 * The element the position is being set to
	 * @param placeCode
	 * The position or "place code" of the element being replaced
	 */
	public default void setElm(T elm, String placeCode) {
		if(hasPlaceCode(placeCode))
			setElement(elm, placeCode);
		else
			placeElm(elm, placeCode);
	}
	
	/**sets existing place code to element [error if not available]
	 * @param elm
	 * @param placeCode
	 */
	public void setElement(T elm, String placeCode);

	/**
	 * Place an element at a place code (move other elements around as necessary)
	 * @param elm
	 * The newly inserted element
	 * @param placeCode
	 * The place it will be inserted
	 */
	public void placeElm(T elm, String placeCode);
	
	/**
	 * Find the place code of the element
	 * @param elm
	 * @return the place code of the element
	 */
	public String findElm(T elm);
	
	/**
	 * Get an element based on it's place code
	 * @param placeCode
	 * @return the element at the place code
	 */
	public T getElm(String placeCode);
	
	/**
	 * @return a collection of all place codes
	 */
	public Collection<String> placeCodes();
	
	public default boolean hasPlaceCode(String placeCode) {
		return placeCodes().contains(placeCode);
	}
	
	public Collection<String> emptyPlaceCodes();
	
	public default String emptyPlaceCode() {
		return CollectionMethods.random(emptyPlaceCodes());
	}
	
	/**
	 * Find the place code of the element in another solution, and set the place code in this solution to that element
	 * @param elm
	 * @param solution
	 */
	public default void setElmFrom(T elm, S solution) {
		setElm(elm, solution.findElm(elm));
	}
	
	/**
	 * Find the element at the place code of the other solution and set this solution's place code to hold that element
	 * @param placeCode
	 * @param solution
	 */
	public default void setElmFrom(String placeCode, S solution) {
		setElm(solution.getElm(placeCode), placeCode);
	}
	

	/**
	 * Place an element from another solution at the same place code
	 * @param elm
	 * @param solution
	 */
	public default void placeElmFrom(T elm, S solution) {
		placeElm(elm, solution.findElm(elm));
	}
	
	/**
	 * Place the element held at the place code of another solution at that same place code in this solution
	 * @param placeCode
	 * @param solution
	 */
	public default void placeElmFrom(String placeCode, S solution) {
		placeElm(solution.getElm(placeCode), placeCode);
	}
	
	/**
	 * Remove an item at their place code
	 * @param placeCode
	 * The place code of the item being removed
	 * @return false if no such place code exists
	 */
	public boolean removeItem(String placeCode);
	
	
	public boolean betterThan(S other);
	
	public default int compareTo(S arg0) {
		if (betterThan(arg0)) return 1;
		else return -1;
	}

	S change();
}