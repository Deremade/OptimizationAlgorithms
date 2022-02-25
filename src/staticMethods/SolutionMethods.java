package staticMethods;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import Default.OptAlgorithm;
import Solution.OptSolution;

public class SolutionMethods {

	public static <T, S extends OptSolution<T, S>> Queue<String> placeCodes(Collection<S> solutions) {
		Queue<String> placeCodes = new ArrayDeque<String>();
		for(S sol : solutions)
			for(String s : sol.placeCodes())
				if(!placeCodes.contains(s))
					placeCodes.add(s);
		return placeCodes;
	}
	
	public static <T, S extends OptSolution<T, S>> List<T> getElms(String placeCode, Collection<S> solutions){
		List<T> elms = new LinkedList<T>();
		for(S sol : solutions)
			if(sol.hasPlaceCode(placeCode))
				elms.add(sol.getElm(placeCode));
		return elms;
	}
	
	public static <E> E getRandom (Collection<E> coll) {
		if(coll.isEmpty()) return null;
		int num = (int) (Math.random() * coll.size());
	    for(E e: coll) if (--num < 0) return e;
	    throw new AssertionError();
	}
	
	public static <T, S extends OptSolution<T, S>> T randomElmAtPlaceCode(String placeCode, Collection<S> solutions) {
		return getRandom(getElms(placeCode, solutions));
	}
	
	
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
	
	
	public static <T, S extends OptSolution<T, S>> S bestSolution(Collection<S> nearbySolutions) {
		S best = null;
		for(S sp : nearbySolutions) 
			if(best == null) best = sp;
			else
				if(sp.betterThan(best))
					best = sp;
		return best;
	}
	
	public static <T, S extends OptSolution<T, S>>  S worstSolution(Collection<S> nearbySolutions) {
		S worst = null;
		for(S sp : nearbySolutions) 
			if(worst == null) worst = sp;
			else
				if(worst.betterThan(sp))
					worst = sp;
		return worst;
	}
}
