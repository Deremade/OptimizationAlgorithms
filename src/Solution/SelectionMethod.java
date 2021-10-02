package Solution;

import java.util.Collection;

import staticMethods.SolutionRanker;

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
		Collection<OptimizationSolution<E>> survivors = SolutionRanker.mostFit(population, capacity);
		population.clear();
		population.addAll(survivors);
	}
	
}