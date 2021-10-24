package Test;

import Solution.OptimizationSolution;
import Solution.Problem;

public class allTrue implements Problem<Boolean> {

	@Override
	public double value(OptimizationSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		int right = 0;
		for(String p : solution.placeCodes())
			if(solution.getElm(p)) right++;
		return right;
	}

	@Override
	public String solutionDetails(OptimizationSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		return solution.value()+"";
	}

	@Override
	public boolean isValid(OptimizationSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double changeSizeChance() {
		// TODO Auto-generated method stub
		return 0;
	}

}
