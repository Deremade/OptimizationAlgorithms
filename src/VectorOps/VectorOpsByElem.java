package VectorOps;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Solution.OptSolution;
import staticMethods.SolutionMethods;

public abstract class VectorOpsByElem<T, S extends OptSolution<T, S>> implements VectorOps<T, S> {
	
	abstract ElemType<T> elemType();

	@Override
	public S difference(S sol1, S sol2) {
		//place items into collection
		List<S> items = new LinkedList<S>();
		items.add(sol1);
		items.add(sol2);
		//get place codes
		Queue<String> placeCodes = SolutionMethods.placeCodes(items);
		//create solution
		S difference = sol1.emptySolution();
		
		for(String code : placeCodes) {//for every place code
			//find difference between elements
			T elem = elemType().difference(
					sol1.getElm(code),
					sol2.getElm(code)
			);
			difference.setElm(elem, code);
		}
		return difference;
	}

	@Override
	public S add(S sol1, S sol2) {
		//place items into collection
		List<S> items = new LinkedList<S>();
		items.add(sol1);
		items.add(sol2);
		//get place codes
		Queue<String> placeCodes = SolutionMethods.placeCodes(items);
		//create solution
		S difference = sol1.emptySolution();
				
		for(String code : placeCodes) {//for every place code
			//find the sum of elements
			T elem = elemType().add(
				sol1.getElm(code),
				sol2.getElm(code)
			);
			difference.setElm(elem, code);
		}
		return difference;
	}

	@Override
	public S scale(S sol, double scale) {
		// Create scaled solution
		S scaled = sol.emptySolution();
		//for every place code
		for(String code : sol.placeCodes())
			//set the element to a scaled version
			scaled.setElement(
					elemType().scale(sol.getElm(code), scale), 
					code);
		return scaled;
	}

}
