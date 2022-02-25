package Default;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import Solution.OptSolution;
import staticMethods.SolutionMethods;


/**
 * @author David
 * 
 * An interface which allows for defining selection methods
 *
 * @param <T>
 * @param <S>
 */
public interface SelectionMethod<T, S extends OptSolution<T, S>> {

	
	/**
	 * @param pop
	 * @param size
	 * @return All solutions that make it through the selection process
	 */
	default public Collection<S> subjecttoSelection(Collection<S> pop, int size) {
		//Make list to hold valid solutions
		LinkedList<S> validSolutons = new LinkedList<S>();
		for(S solution : pop) {
			if(solution.isValid())
				validSolutons.add(solution);
		}
		
		return subjectValidToSelection(validSolutons, size);
	}
	
	/**
	 * @param pop
	 * A collection of valid solutions
	 * @param size
	 * @return All items that make it through the selection process
	 */
	public Collection<S> subjectValidToSelection(Collection<S> pop, int size);
	
	/**
	 * @param solutions
	 * @param size
	 * @return The most fir [size] solutions
	 */
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

/**
 * @author David
 * 
 * Only the most fit survive, all are ranked based on fitness the top survive
 *
 * @param <T>
 * @param <S>
 */
class survivalOfThefittest<T, S extends OptSolution<T, S>> implements SelectionMethod<T, S>{

	@Override
	public Collection<S> subjectValidToSelection(Collection<S> pop, int size) {
		return mostFit(pop, size);
	}

}
