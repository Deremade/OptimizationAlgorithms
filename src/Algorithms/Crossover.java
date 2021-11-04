package Algorithms;

import java.util.Collection;
import java.util.LinkedList;

import Solution.ElemType;
import Solution.OptimizationSolution;
import staticMethods.SolutionMethods;

public interface Crossover {

	public <E, S extends OptimizationSolution<E>> S crossover(Collection<S> solutions);
	
	
	public default <E, S extends OptimizationSolution<E>> LinkedList<S> makeList(Collection<S> solutions) {
		LinkedList<S> list = new LinkedList<S>();
		for(S solution : solutions) {
			list.add(solution);
		}
		return list;
	}
	
}

class CrissCross implements Crossover {

	@Override
	public <E, S extends OptimizationSolution<E>> S crossover(Collection<S> solutions) {
		S newSolution = SolutionMethods.getRandom(solutions).emptySolution();
		 LinkedList<S> ll = makeList(solutions);
		 int index = 0;
		 S currSolution = ll.get(0);
		 Collection<String> codes = SolutionMethods.placeCodes(solutions);
		 for(String code : codes) {
			 currSolution = ll.get(index % ll.size());
			 while(!currSolution.hasPlaceCode(code)) {
				 ll.remove(currSolution);
				 if(ll.isEmpty()) ll = makeList(solutions);
				 currSolution = ll.get(index % ll.size());
			 }
			newSolution.setElm(currSolution.getElm(code), code);
			index++;
		 }
		 return newSolution;
	}

}

class SplitSection implements Crossover {

	@Override
	public <E, S extends OptimizationSolution<E>> S crossover(Collection<S> solutions) {
		S newSolution = SolutionMethods.getRandom(solutions).emptySolution();
		Collection<String> codes = SolutionMethods.placeCodes(solutions);
		LinkedList<S> ll = makeList(solutions);
		int splitSize = (int) Math.ceil(codes.size()/solutions.size());
		int index = 0;
		 for(String code : codes) {
			 newSolution.placeElm(ll.get((int) Math.floorDiv(index, splitSize)).getElm(code), code);
			 index++;
		 }
		 return newSolution;
	}
	
}

class RandomCross implements Crossover {

	@Override
	public <E, S extends OptimizationSolution<E>> S crossover(Collection<S> solutions) {
		S newSolution = SolutionMethods.getRandom(solutions).emptySolution();
		 for(String code : SolutionMethods.placeCodes(solutions))
			 newSolution.setElm(SolutionMethods.randomElmAtPlaceCode(code, solutions), code);
		 return newSolution;
	}
	
}

class Split implements Crossover {
	ElemType<?> et;
	
	public <E> Split(ElemType<?> elmType){
		et = elmType;
	}
	
	@SuppressWarnings("unchecked")
	<E> ElemType<E> elmType(){
		return (ElemType<E>) et;	
	}
	
	@Override
	public <E, S extends OptimizationSolution<E>> S crossover(Collection<S> solutions) {
		S newSolution = SolutionMethods.getRandom(solutions).emptySolution();
		ElemType<E> et = elmType();
		 for(String code : SolutionMethods.placeCodes(solutions))
			 newSolution.setElm(et.split(SolutionMethods.getElms(code, solutions)), code);
		 return newSolution;
	}
	
}

class RandomSplit implements Crossover {
	ElemType<?> et;
	
	public <E> RandomSplit(ElemType<?> elmType){
		et = elmType;
	}
	
	@SuppressWarnings("unchecked")
	<E> ElemType<E> elmType(){
		return (ElemType<E>) et;	
	}

	@Override
	public <E, S extends OptimizationSolution<E>> S crossover(Collection<S> solutions) {
		S newSolution = SolutionMethods.getRandom(solutions).emptySolution();
		ElemType<E> et = elmType();
		 for(String code : SolutionMethods.placeCodes(solutions)) {
			 Collection<E> elms = SolutionMethods.getElms(code, solutions);
			 Collection<E> splitElms = new LinkedList<E>();
			 while(splitElms.isEmpty())
				 for(E elm : elms)
					 if(Math.random() > 0.5)
						 splitElms.add(elm);
			 newSolution.setElm(et.split(splitElms), code);
		 }
		 return newSolution;
	}
	
}