package Solution;

import java.util.Collection;
import java.util.LinkedList;

import staticMethods.SolutionMethods;

public interface VectorOperations<E> {
	
	ElemType<E> elmType();
	
	/**
	 * @param elm1
	 * @param elm2
	 * @return the sum of the elements
	 */
	public default E addElms(E elm1, E elm2) {
		LinkedList<E> ll = new LinkedList<E>();
		ll.add(elm1);
		ll.add(elm2);
		return  elmType().addElms(ll);
	}
	
	/**
	 * @param elm1
	 * The "Start"
	 * @param elm2
	 * The "End"
	 * @return The difference between the elements, (as well as the direction +/-)
	 */
	//public E difference(E elm1, E elm2);
	
	public <S extends OptimizationSolution<E>> double solutionLength(S solution);
	
	public default OptimizationSolution<E> difference(OptimizationSolution<E> sol1, OptimizationSolution<E> sol2) {
		LinkedList<OptimizationSolution<E>> ll = new LinkedList<OptimizationSolution<E>>();
		if(sol1 == null) sol1 = sol2.emptySolution();
		if(sol2 == null) sol2 = sol1.emptySolution();
		ll.add(sol1);
		ll.add(scaleSolution(sol2, -1));
		return addSolutions(ll);
	}
	
	/**
	 * Performs scale() on a whole solution of Elements
	 * @param solution
	 * @param scale
	 * @return the new solution
	 */
	public default OptimizationSolution<E> scaleSolution(OptimizationSolution<E> solution, double scale){
		OptimizationSolution<E> scaled = solution.emptySolution();
		for(String placeCode : solution.placeCodes())
			scaled.placeElm( elmType().scale(solution.getElm(placeCode), scale), placeCode);
		return scaled;
	}
	
	/**
	 * By default performs addElms() on all elements at all indexes
	 * @param parents
	 * The solutions being summed
	 * @return The sum of all solutions
	 */
	public default <S extends OptimizationSolution<E>> S addSolutions(Collection<S> solutions) {
		S newSolution = SolutionMethods.getRandom(solutions).emptySolution();
		 for(String code : SolutionMethods.placeCodes(solutions))
			 newSolution.placeElm( elmType().addElms(SolutionMethods.getElms(code, solutions)), code);
		 return newSolution;
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
	
	public default <S extends OptimizationSolution<E>> Collection<S> nearbySolutions(Collection<S> sample, S selectedSolution, double nearDist) {
		Collection<S> nearby = new LinkedList<S>();
		for(S particle : sample) 
			if(distance(particle, selectedSolution) <= nearDist)
				nearby.add(particle);
		return nearby;
	}
	
	public default  <S extends OptimizationSolution<E>> S closest(Collection<S> sample, S sol) {
		S closest = null;
		sample.remove(sol);
		if(sample.isEmpty()) return sol;
		for(S candidate : sample)
			if(closest == null) 
				closest = candidate;
			 else
				 if(distance(sol, closest) < distance(sol, candidate))  
					closest = candidate;
		sample.add(sol);
		return closest;
	}
	
	public default  <S extends OptimizationSolution<E>> Collection<S> closestItems(Collection<S> sample, S sol, int num) {
		LinkedList<S> returned = new LinkedList<S>();
		while(returned.size() < num && !sample.isEmpty()) {
			S closeSolution = closest(sample, sol);
			returned.add(closeSolution);
			sample.remove(closeSolution);
		}
		sample.addAll(returned);
		return returned;
	}

}
