package Default;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
	
	static <T, S extends OptSolution<T, S>> survivalOfThefittest<T, S> survivalOfThefittest() {
		return new survivalOfThefittest<T,S>();
	}

	
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
		LinkedList<S> mostFit = new LinkedList<S>();
		List<S> sorted = SolutionMethods.sort(solutions);
		while(mostFit.size() < size) {
			S mostFitSol = sorted.get(sorted.size());
			mostFit.add(mostFitSol);
			sorted.remove(mostFitSol);
		}
		return mostFit;
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
