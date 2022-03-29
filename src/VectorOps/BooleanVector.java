package VectorOps;

import Problems.Problem;
import Problems.AllTrueTest.BoolElem;
import Problems.AllTrueTest.TestSolution;
import Solution.ListSolution;
import Solution.OptSolution;

public class BooleanVector extends VectorOpsByElem<Boolean, ListSolution<Boolean>> {
	ElemType<Boolean> elemType = new BoolElem();
	Problem<Boolean, ListSolution<Boolean>> problem;
	public BooleanVector(Problem<Boolean, ListSolution<Boolean>> problem) {
		super();
		this.problem = problem;
	}

	@Override
	public TestSolution origin() {
		// TODO Auto-generated method stub
		return new TestSolution(problem);
	}

	@Override
	ElemType<Boolean> elemType() {
		// TODO Auto-generated method stub
		return elemType;
	}

}
