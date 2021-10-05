package Solution;

import java.util.Collection;
import java.util.LinkedList;

import staticMethods.CollectionMethods;

public interface Matchingsolution<E> extends OptimizationSolution<E> {

	/**
	 * Find the element in annother solution that would be matched witht he inputed element from this solution
	 * @param element
	 * @param other
	 * @return the element it would be matched with
	 */
	E matchingElement(E element, OptimizationSolution<E> other);
	
	/**
	 * A list of all elements in the solution set that would match the inserted Element
	 * @param element
	 * @param solutions
	 * @return a list of matching elements
	 */
	default Collection<E> matingElements(E element, Collection<OptimizationSolution<E>> solutions) {
		LinkedList<E> list = new LinkedList<E>();
		for(OptimizationSolution<E> sol : solutions)
			list.add(matchingElement(element, sol));
		return list;
	}
	
	/**
	 * @param element
	 * @param solutions
	 * @param allownull
	 * Can this function return null
	 * @return a randomly matching element
	 */
	default E randomMatch(E element, Collection<OptimizationSolution<E>> solutions) {
		return CollectionMethods.random(matingElements(element, solutions));
	}
	
	/**
	 * Creates a solution that every item in the input of solutions can be matched to at least one tiem in the result
	 * @param solutions
	 * @return a "Fully matching" solution
	 */
	OptimizationSolution<E> fullyMatching(Collection<OptimizationSolution<E>> solutions);

	OptimizationSolution<E> fullyMatching(OptimizationSolution<E> elm1, OptimizationSolution<E> elm2);

}
