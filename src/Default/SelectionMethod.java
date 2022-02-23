package Default;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import staticMethods.SolutionMethods;


public interface SelectionMethod<T, S extends OptSolution<T, S>> {

	default public Collection<S> subjecttoSelection(Collection<S> pop, int size) {
		LinkedList<S> validSolutons = new LinkedList<S>();
		for(S solution : pop) {
			if(solution.isValid())
				validSolutons.add(solution);
		}
		
		return subjectValidToSelection(validSolutons, size);
	}
	
	public Collection<S> subjectValidToSelection(Collection<S> pop, int siz);
	
	public default LinkedList<S> mostFit(Collection<S> solutions, int size) {
		return mostFit(SolutionMethods.sort(solutions), size);
	}
	
	public default LinkedList<S> mostFit(LinkedList<Entry<S, Double>> rankedPopulation, int size) {
		// TODO Auto-generated method stub
		if(rankedPopulation.size() <= size) { //retunn all solutions if there is less then the number you want
			LinkedList<S> survivors = new LinkedList<S>();
			for(Entry<S, Double> entry : rankedPopulation) {
					survivors.add(entry.getKey());
			}
			return survivors;
		}
		LinkedList<S> survivors = new LinkedList<S>();
		for(int i = rankedPopulation.size()-1; i > Math.max(rankedPopulation.size()-size, 0); i--) {
				survivors.add((S) rankedPopulation.get(i).getKey());
		}
		return survivors;
	}
}

class survivalOfThefittest<T, S extends OptSolution<T, S>> implements SelectionMethod<T, S>{

	@Override
	public Collection<S> subjectValidToSelection(Collection<S> pop, int size) {
		return mostFit(pop, size);
	}

}
