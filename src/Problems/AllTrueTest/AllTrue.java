package Problems.AllTrueTest;

import java.util.Collection;
import java.util.LinkedList;

import Algorithms.OptAlgorithm;
import Default.mainTest;
import Problems.ValueProblem;
import Solution.ListSolution;

public class AllTrue implements ValueProblem<Boolean, ListSolution<Boolean>> , OptAlgorithm<Boolean, ListSolution<Boolean>>{
	Collection<ListSolution<Boolean>> solutions = new LinkedList<ListSolution<Boolean>>();
	int size = 5;
	@Override
	public boolean isValid(ListSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String solutionDetails(ListSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		return ""+solution.solutionList();
	}

	@Override
	public Number value(ListSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		int i = 0;
		for(Boolean b : solution.solutionList())
			if(b) i++;
		return i;
	}

	@Override
	public ListSolution<Boolean> randomSolution() {
		ListSolution<Boolean> rSolution = new TestSolution(this);
		while(rSolution.solutionList().size() < size)
			rSolution.placeElm(mainTest.RNG.nextBoolean(), rSolution.emptyPlaceCode());
		return rSolution;
	}

	@Override
	public Collection<ListSolution<Boolean>> solutions() {
		// TODO Auto-generated method stub
		return solutions;
	}

	@Override
	public void iteration() {
		// TODO Auto-generated method stub
		
	}

}
