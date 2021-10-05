package Solution;

import java.util.Collection;
import java.util.LinkedList;

import staticMethods.CollectionMethods;

public interface VectorOperations<E> {

	public E addElements(Collection<E> elements);
	
	/**
	 * @param elm1
	 * @param elm2
	 * @return the sum of the elements
	 */
	public default E addElements(E elm1, E elm2) {
		LinkedList<E> ll = new LinkedList<E>();
		ll.add(elm1);
		ll.add(elm2);
		return addElements(ll);
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
		if(sol1 == null) sol1 = sol2.emptySolution();
		if(sol2 == null) sol2 = sol1.emptySolution();
		ll.add(sol1);
		ll.add(scaleSolution(sol2, -1));
		return addSolutions(ll);
	}
	
	/**
	 * Returns the "length" of a solution, a single numerical representation of the solution
	 * @param sol
	 * @return "length"
	 */
	public double solutionLength(OptimizationSolution<E> sol);
	
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
	public default OptimizationSolution<E> addSolutions(Collection<OptimizationSolution<E>> solutions) {
		OptimizationSolution<E> newSolution = CollectionMethods.random(solutions).emptySolution();
		 OptimizationSolution<E> fullyMixed = fullMix(solutions, newSolution);
		 for(E elm : fullyMixed) {
			 if(newSolution instanceof Matchingsolution<?>)
					newSolution.add(addElements(((Matchingsolution<E>) newSolution).matingElements(elm, solutions)));
			if(newSolution instanceof CountingSolution<?>)
				((CountingSolution<E>) newSolution).addCount(elm, 
						CollectionMethods.sum(((CountingSolution<E>) newSolution).countAllElms(elm, solutions)));
		 }
		 return newSolution;
	}
	
	static <E> OptimizationSolution<E> fullMix(Collection<OptimizationSolution<E>> solutions, OptimizationSolution<E> solution){
		if(solution instanceof Matchingsolution<?>)
			return ((Matchingsolution<E>) solution).fullyMatching(solutions);
		if(solution instanceof CountingSolution<?>)
			return ((CountingSolution<E>) solution).allElms(solutions);
		return null;
	}
	
	public default OptimizationSolution<E> addAllTo(Collection<OptimizationSolution<E>> adding, OptimizationSolution<E> added) {
		adding.add(added);
		return addSolutions(adding);
	}
	
	/**
	 * Finds some numerical representation of distance between two solutions
	 * @param elm1
	 * @param elm2
	 * @return The "distance"
	 */
	public default double distance(OptimizationSolution<E> elm1, OptimizationSolution<E> elm2) {
		return solutionLength(difference(elm1, elm2));
	}

	public default Collection<OptimizationSolution<E>> nearbySolutions(Collection<OptimizationSolution<E>> sample, OptimizationSolution<E> selectedSolution, double nearDist) {
		Collection<OptimizationSolution<E>> nearby = new LinkedList<OptimizationSolution<E>>();
		for(OptimizationSolution<E> particle : sample) 
			if(distance(particle, selectedSolution) <= nearDist)
				nearby.add(particle);
		return nearby;
	}
	
	public default OptimizationSolution<E> bestSolution(Collection<OptimizationSolution<E>> nearbySolutions) {
		OptimizationSolution<E> best = null;
		for(OptimizationSolution<E> sp : nearbySolutions) 
			if(best == null) best = sp;
			else
				if(sp.value() > best.value())
					best = sp;
		return best;
	}
	
	public default OptimizationSolution<E> worstSolution(Collection<OptimizationSolution<E>> nearbySolutions) {
		OptimizationSolution<E> worst = null;
		for(OptimizationSolution<E> sp : nearbySolutions) 
			if(worst == null) worst = sp;
			else
				if(sp.value() < worst.value())
					worst = sp;
		return worst;
	}
}
