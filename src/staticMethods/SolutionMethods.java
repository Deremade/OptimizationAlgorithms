package staticMethods;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import Algorithms.OptAlgorithm;
import Solution.OptSolution;

public class SolutionMethods {

	/**
	 * @param solutions
	 * @return a list of all place codes contained in all solutions
	 */
	public static <T, S extends OptSolution<T, S>> Queue<String> placeCodes(Collection<S> solutions) {
		Queue<String> placeCodes = new ArrayDeque<String>();
		for(S sol : solutions)
			for(String s : sol.placeCodes())
				if(!placeCodes.contains(s))
					placeCodes.add(s);
		return placeCodes;
	}
	
	/**
	 * @param placeCode
	 * @param solutions
	 * @return a list of all elemetns contained at the palce code in all solutions
	 */
	public static <T, S extends OptSolution<T, S>> List<T> getElms(String placeCode, Collection<S> solutions){
		List<T> elms = new LinkedList<T>();
		for(S sol : solutions)
			if(sol.hasPlaceCode(placeCode))
				elms.add(sol.getElm(placeCode));
		return elms;
	}
	
	/**
	 * @param placeCode
	 * @param solutions
	 * @return a random element which resides at the place code in at least 1 solution
	 */
	public static <T, S extends OptSolution<T, S>> T randomElmAtPlaceCode(String placeCode, Collection<S> solutions) {
		return CollectionMethods.random(getElms(placeCode, solutions));
	}
	
	
	/**
	 * @param solutions
	 * @return a sorted list of solutions
	 */
	public static <T, S extends OptSolution<T, S>>  List<S> sort(Collection<S> solutions) {
		List<S> solutionList = new LinkedList<S>();
		for(S sol : solutions)
			solutionList.add(sol);
		return mergeSort(solutionList);
	}
	
	public static <T, S extends OptSolution<T, S>> List<S> mergeSort(List<S> solutions) {
		if(solutions.size() > 1) {
			List<S> left = new LinkedList<S>();
			List<S> right = new LinkedList<S>(); 
			int index = 0;
			//fill left
			while(index < solutions.size()/2) {
				left.add(solutions.get(index));
				index++;
			}
			//fill right
			while(index < solutions.size()) {
				right.add(solutions.get(index));
				index++;
			}
			return merge(mergeSort(left), mergeSort(right));
		}
		else return solutions;
	}
	
	public static <T, S extends OptSolution<T, S>> List<S> merge(List<S> solutions0, List<S> solutions1) {
		List<S> merged = new LinkedList<S>();
		//While neither list is empty
		while(!solutions0.isEmpty() && !solutions1.isEmpty()) {
			//if the first list's first item is greater than the second, add the first item of the second list (lowest item first)
			if(solutions0.get(0).betterThan(solutions1.get(0))) {
				merged.add(solutions1.get(0));
				solutions1.remove(0);
			} else {
				merged.add(solutions0.get(0));
				solutions0.remove(0);
			}
		}
		while(!solutions0.isEmpty()) {
			merged.add(solutions0.get(0));
			solutions0.remove(0);
		}
		while(!solutions1.isEmpty()) {
			merged.add(solutions1.get(0));
			solutions1.remove(0);
		}
		return merged;
	}
	
	
	/**
	 * @param nearbySolutions
	 * @return the best solution
	 */
	public static <T, S extends OptSolution<T, S>> S bestSolution(Collection<S> nearbySolutions) {
		S best = null;
		for(S sp : nearbySolutions) 
			if(best == null) best = sp;
			else
				if(sp.betterThan(best))
					best = sp;
		return best;
	}
	
	/**
	 * @param nearbySolutions
	 * @return the worst solution
	 */
	public static <T, S extends OptSolution<T, S>>  S worstSolution(Collection<S> nearbySolutions) {
		S worst = null;
		for(S sp : nearbySolutions) 
			if(worst == null) worst = sp;
			else
				if(worst.betterThan(sp))
					worst = sp;
		return worst;
	}
	

	
	/**
	 * @param solutions
	 * the Sample of solutions to draw from to find most fit
	 * @param size
	 * How many solutions to return
	 * @return a list of the most fit solutions
	 */
	public static <T, S extends OptSolution<T, S>>  LinkedList<S> bestSolutions(Collection<S> solutions, int size) {
		//Create list to hold the fit solutions
		LinkedList<S> mostFitsolutions = new LinkedList<S>();
		//Sort the existing solutions
		List<S> sortedsolutions = sort(solutions);
		//While we have yet to find the needed number of solutions
		while(mostFitsolutions.size() < size) {
			//add at index
			mostFitsolutions.add(sortedsolutions.get(mostFitsolutions.size()));
		}
		return mostFitsolutions;
	}
}
