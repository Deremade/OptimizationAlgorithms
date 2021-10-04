package Algorithms;

import java.util.Collection;
import java.util.LinkedList;

import Solution.OptimizationSolution;

public interface CombinedSolutions<E, S extends OptimizationSolution<E>> {
	/**
	 * For each index of each solution, their elements at each index are randomly selected
	 * the result of splitting the difference is then put in the child solution
	 * @param parents
	 * The solutions being crossed
	 * @return child solution
	 */
	public OptimizationSolution<E> randomSplit(Collection<S> parents);

	public default LinkedList<OptimizationSolution<E>> makeList(Collection<OptimizationSolution<E>> parents) {
		LinkedList<OptimizationSolution<E>> list = new LinkedList<OptimizationSolution<E>>();
		for(OptimizationSolution<E> solution : parents) {
			list.add(solution);
		}
		return list;
	}

	/**
	 * Splits all parent solutions into sections
	 * Stitches those sections back together to make a child solution
	 * @param parents
	 * @return child solution
	 */
	public OptimizationSolution<E> splitSection(Collection<S> parents);

	/**
	 * Crosses solutions by splitting the difference between each gene index
	 * @param parents
	 * @return new solution
	 */
	public OptimizationSolution<E> splitDifferenceCrossover(Collection<S> parents);

	/**
	 * Randomly selects a parent at each index to give a child solution that parent's index's element
	 * @param parents
	 * @return child
	 */
	public OptimizationSolution<E> randomCrossover(Collection<S> parents);

	/**
	 * Generates a child solution by cycling through each parent
	 * the index incremented each step though the cycle to give the child node one element from each parent each cycle
	 * @param parents
	 * @return
	 */
	public OptimizationSolution<E> crisscross(Collection<S> parents);
}
