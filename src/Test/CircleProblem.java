package Test;

import Solution.NumericAlgorithm;
import Solution.OptimizationSolution;
import Solution.Problem;

public class CircleProblem<N extends Number> implements Problem<N> {

	@Override
	public double value(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		double error = 0;
		for(Number n : solution) {
			error += Math.pow(n.doubleValue(), 2);
		}
		return 0-Math.pow(error, 0.5);
	}

	@Override
	public String solutionDetails(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		String s = " [ ";
		for(N n : solution) {
			s += n.toString()+",";
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
