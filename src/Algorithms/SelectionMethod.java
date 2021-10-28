package Algorithms;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Solution.OptimizationSolution;
import staticMethods.SolutionMatcher;
import staticMethods.SolutionMethods;

public abstract class SelectionMethod implements Selector{

	public static SelectionMethod topSurvivors(int capacity) {
		return new TopSurvivors(capacity);
	}
	
	public static TopGoruped topGroups(int winners, SolutionMatcher solutionMatcher) {
		return new TopGoruped(winners, solutionMatcher);
	}
}

interface Selector {
	
	/**
	 * Determines which members of the population survive the selection method
	 * 
	 * @param population
	 * The sample of all solutions being considered
	 */
	public <E, S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population);
	
}

class TopSurvivors extends SelectionMethod {
	int capacity;
	
	/**
	 * @param capacity
	 */
	public TopSurvivors(int capacity) {
		super();
		this.capacity = capacity+1;
	}

	public <E, S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population) {
		Collection<S> survivors = mostFit(population, capacity);
		population.clear();
		population.addAll(survivors);
	}

	private <E, S extends OptimizationSolution<E>> Collection<S> mostFit(Collection<S> population, int capacity) {
		// TODO Auto-generated method stub
		List<S> solutions = SolutionMethods.sort(population);
		while(solutions.size() > capacity)
			solutions.remove(0);
		return solutions;
	}

}

class TopGoruped extends SelectionMethod {
	int winners;
	SolutionMatcher matcher;
	
	/**
	 * @param winners
	 * @param solutionMatcher
	 */
	public TopGoruped(int winners, SolutionMatcher solutionMatcher) {
		super();
		this.winners = winners;
		this.matcher = solutionMatcher;
	}

	public <E, S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population) {
		Collection<S> survivors = new LinkedList<S>();
		for(Collection<S> group : matcher.genMatches(population)) 
			survivors.addAll(mostFit(group, winners));
		population.clear();
		population.addAll(survivors);
	}

	private <E, S extends OptimizationSolution<E>> Collection<S> mostFit(Collection<S> population, int capacity) {
		// TODO Auto-generated method stub
		List<S> solutions = SolutionMethods.sort(population);
		while(solutions.size() > capacity)
			solutions.remove(0);
		return solutions;
	}

}