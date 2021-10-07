package staticMethods;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import Solution.OptimizationSolution;

public class SolutionMethods {

	public static <E, S extends OptimizationSolution<E>> Queue<String> placeCodes(Collection<S> solutions) {
		Queue<String> placeCodes = new ArrayDeque<String>();
		for(S sol : solutions)
			for(String s : sol.placeCodes())
				if(!placeCodes.contains(s))
					placeCodes.add(s);
		return placeCodes;
	}
	
	public static <E, S extends OptimizationSolution<E>> List<E> getElms(String placeCode, Collection<S> solutions){
		List<E> elms = new LinkedList<E>();
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
	
	public static <E, S extends OptimizationSolution<E>> E randomElmAtPlaceCode(String placeCode, Collection<S> solutions) {
		return getRandom(getElms(placeCode, solutions));
	}
}
