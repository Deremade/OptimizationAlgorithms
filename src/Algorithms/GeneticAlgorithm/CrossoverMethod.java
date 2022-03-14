package Algorithms.GeneticAlgorithm;

import java.util.Collection;
import java.util.List;

import Solution.OptSolution;
import staticMethods.SolutionMethods;

public interface CrossoverMethod {

	<T, S extends OptSolution<T, S>> S crossover(Collection<S> original);
	
	/**
	 * @return a solutions with random elements from each "parent"
	 */
	public static randomElems randomElms() {
		return new randomElems();
	}
	
	/**
	 * Cycles through "parents" to grab an element from each solution and place it in the "child"
	 * @return the "child"
	 */
	public static crissCross crissCross() {
		return new crissCross();
	}
}

class randomElems implements CrossoverMethod {

	@Override
	public <T, S extends OptSolution<T, S>> S crossover(Collection<S> original) {
		//Create child solution
		S child = SolutionMethods.sort(original).get(0).emptySolution();
		//for every place code
		for(String code : SolutionMethods.placeCodes(original))
			child.setElm( //find random element to fill place code
					SolutionMethods.randomElmAtPlaceCode(code, original), code);
		return child;
	}
	
}

class crissCross implements CrossoverMethod {

	@Override
	public <T, S extends OptSolution<T, S>> S crossover(Collection<S> original) {
		//Place parents in a list
		List<S> items = SolutionMethods.sort(original);
		//Create child
		S child = items.get(0).emptySolution();
		int curSol = 0; //Indicate current solution in cycle
		//for every place code
		for(String code : SolutionMethods.placeCodes(original)) {
			//Get element from current solution
			if(items.get(curSol).hasPlaceCode(code))
				child.setElmFrom(code, items.get(curSol));
			//Cycle through solutions
			curSol = (curSol + 1) % items.size();
		}
		return child;
	}
	
}