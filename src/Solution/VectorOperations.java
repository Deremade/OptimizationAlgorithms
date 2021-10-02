package Solution;

import java.util.Collection;
import java.util.LinkedList;

public interface VectorOperations<E> {

	public E add(Collection<E> elements);
	
	/**
	 * @param elm1
	 * @param elm2
	 * @return the sum of the elements
	 */
	public default E add(E elm1, E elm2) {
		LinkedList<E> ll = new LinkedList<E>();
		ll.add(elm1);
		ll.add(elm2);
		return add(ll);
	}
	
	/**
	 * @param elm1
	 * The "Start"
	 * @param elm2
	 * The "End"
	 * @return The difference between the elements, (as well as the direction +/-)
	 */
	public E difference(E elm1, E elm2);
	
	public default OptimizationSolution<E> difference(OptimizationSolution<E> sol1, OptimizationSolution<E> sol2) {
		LinkedList<OptimizationSolution<E>> ll = new LinkedList<OptimizationSolution<E>>();
		ll.add(sol1);
		ll.add(scaleSolution(sol2, -1));
		return addSolutions(ll);
	}
	
	/**
	 * @param elm
	 * Element
	 * @param scale
	 * The decimal by which it scales
	 * @return The resulting element
	 */
	public E scale(E elm, double scale);
	
	/**
	 * Performs scale() on a whole solution of Elements
	 * @param solution
	 * @param scale
	 * @return the new solution
	 */
	public default OptimizationSolution<E> scaleSolution(OptimizationSolution<E> solution, double scale){
		OptimizationSolution<E> scaled = solution.emptySolution();
		for(E element : solution) {
			scaled.add(scale(element, scale));
		}
		return scaled;
	}
	
	/**
	 * By default performs add() on all elements at all indexes
	 * @param parents
	 * The solutions being summed
	 * @return The sum of all solutions
	 */
	public default OptimizationSolution<E> addSolutions(Collection<OptimizationSolution<E>> parents) {
		 int maxSize = 0;
		 OptimizationSolution<E> newSolution = null;
		 for(OptimizationSolution<E> parent : parents) {
			 if (parent.size() > maxSize)
				 maxSize = parent.size();
			 if(newSolution == null) newSolution = parent.emptySolution();
		 }
		 while(newSolution.size() < maxSize) {
			 LinkedList<E> list = new LinkedList<E>();
			 for(OptimizationSolution<E> parent : parents) {
				 if (parent.size() > newSolution.size())
					list.add(parent.get(newSolution.size()));
			 }
			 newSolution.add(add(list));
		 }
		 return newSolution;
	}
	
	/**
	 * Finds some numerical representation of distance between two solutions
	 * @param elm1
	 * @param elm2
	 * @return The "distance"
	 */
	public double distance(OptimizationSolution<E> elm1, OptimizationSolution<E> elm2);
}
