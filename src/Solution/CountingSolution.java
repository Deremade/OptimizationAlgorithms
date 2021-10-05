package Solution;

import java.util.Collection;
import java.util.LinkedList;

import staticMethods.CollectionMethods;

public interface CountingSolution<E> extends OptimizationSolution<E> {

	public default double countElm(E element) {
		return countElm(element, this);
	}
	
	public double countElm(E element, OptimizationSolution<E> solution);
	/**
	 * A list of all element counts in the inserted solutions
	 * @param element
	 * @param solutions
	 * @return a list that counts all elements in all solutions
	 */
	default Collection<Double> countAllElms(E element, Collection<OptimizationSolution<E>> solutions) {
		LinkedList<Double> list = new LinkedList<Double>();
		for(OptimizationSolution<E> sol : solutions)
			list.add(countElm(element, sol));
		return list;
	}
	
	/**
	 * @param element
	 * @param solutions
	 * @param allownull
	 * Can this function return null
	 * @return a randomly matching element
	 */
	default double randomCount(E element, Collection<OptimizationSolution<E>> solutions) {
		return CollectionMethods.random(countAllElms(element, solutions));
	}
	
	public void addCount(E elm, double count);
	
	/**
	 * Creates a solution that holds 0 of every element in the collection of solutions
	 * @param solutions
	 * @return a solution with all elements
	 */
	OptimizationSolution<E> allElms(Collection<OptimizationSolution<E>> solutions);
}
