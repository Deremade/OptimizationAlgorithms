package Problems.AllTrueTest;

import java.util.LinkedList;
import java.util.List;

import Problems.Problem;
import Solution.ListSolution;

public class TestSolution implements ListSolution<Boolean> {
	LinkedList<Boolean> solutionList = new LinkedList<Boolean>();
	Problem<Boolean, ListSolution<Boolean>> problem;
	
	public TestSolution(Problem<Boolean, ListSolution<Boolean>> p) {
		problem = p;
	}
	
	@Override
	public void makeInvalid() {
		// TODO Auto-generated method stub

	}

	@Override
	public ListSolution<Boolean> emptySolution() {
		// TODO Auto-generated method stub
		return new TestSolution(problem());
	}

	@Override
	public ListSolution<Boolean> change() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Boolean> solutionList() {
		// TODO Auto-generated method stub
		return solutionList;
	}

	@Override
	public Problem<Boolean, ListSolution<Boolean>> problem() {
		// TODO Auto-generated method stub
		return problem;
	}

	@Override
	public boolean madeInvalid() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return this.solutionDetails();
	}
	
	public TestSolution clone() {
		TestSolution ts = new TestSolution(problem);
		for(String placeCode : placeCodes())
			ts.placeElm(getElm(placeCode), placeCode);
		return ts;
	}

}
