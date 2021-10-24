package Test;

import Algorithms.NumericAlgorithm;
import Solution.OptimizationSolution;
import Solution.Problem;

public class CircleProblem<N extends Number> implements Problem<N> {

	@Override
	public double value(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		double error = 0;
		for(String n : solution.placeCodes()) {
			error += Math.pow(solution.getElm(n).doubleValue(), 2);
		}
		return 0-Math.pow(error, 0.5);
	}

	@Override
	public String solutionDetails(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		String s = " [ ";
		for(String n : solution.placeCodes()) {
			s += solution.getElm(n).toString()+",";
		}
		return s.substring(0, s.length()-1)+" ] "+Math.round(solution.value());
	}

	@Override
	public boolean isValid(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double changeSizeChance() {
		// TODO Auto-generated method stub
		return 0;
	}

}
