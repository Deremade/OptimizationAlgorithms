package Algorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import Solution.ElemType;
import Solution.MutationMethod;
import Solution.OptimizationSolution;
import staticMethods.SolutionMatcher;

public interface GeneticAlgorithm<E>{
	public static Random r = new  Random();

	ElemType<E> elmType();
	
	/**
	 * @return a selection method to be used in the evolutionary selection process
	 */
	SelectionMethod selectionMethod();
	
	/**
	 * @return a matcher to be used in matching for mating
	 */
	SolutionMatcher matcher();
	
	/**
	 * Uses the matcher() to generate a list of match-ups for the solutions
	 * @param solutions
	 * @return a collection of their matches
	 */
	default Collection<LinkedList<OptimizationSolution<E>>> genMatches(Collection<OptimizationSolution<E>> solutions) {
		return matcher().genMatches(solutions);
	}
	
	/**
	 * Uses the selectionMethod() function to trim the population via the selection process
	 * @param population
	 */
	default <S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population) {
		selectionMethod().subjectToSelection(population);
	}
	
	/**
	 * Simulates "Mating season" by crossing solutions with their match-ups
	 * @param population
	 * @return children, all newly produced solutions
	 */
	default <S extends OptimizationSolution<E>> Collection<S> matingSeason(Collection<S> population){
		Collection<LinkedList<S>> matches = matcher().genMatches(population);
		LinkedList<S> children = new LinkedList<S>();
		for(LinkedList<S> parents : matches) 
			children.add(crossoverMethod().crossover(parents));
		return children;
	}
	
	/**
	 * @return the crossover method to be used in this algorithm
	 */
	Crossover crossoverMethod();
	
	/**
	 * Simulates a generation of the algorithm
	 * @param population
	 */
	default <S extends OptimizationSolution<E>> void generation(Collection<S> population, MutationMethod mm) {
		population.addAll(matingSeason(population));
		for(S sol : population)
			mm.mutate(sol, elmType());
		subjectToSelection(population);
	}
}
