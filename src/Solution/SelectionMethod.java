package Solution;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import staticMethods.SolutionMethods;

public abstract class SelectionMethod implements Selector{

	public static SelectionMethod topSurvivors(int capacity) {
		return new TopSurvivors(capacity);
	}
}

interface Selector {
	
	public <E> void subjectToSelection(Collection<OptimizationSolution<E>> population);
	
}

class TopSurvivors extends SelectionMethod {
	int capacity;
	
	public TopSurvivors(int capacity) {
		super();
		this.capacity = capacity+1;
	}

	public <E> void subjectToSelection(Collection<OptimizationSolution<E>> population) {
		Collection<OptimizationSolution<E>> survivors = mostFit(population, capacity);
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