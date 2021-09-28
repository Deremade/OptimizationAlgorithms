package Test;

import Solution.OptimizationSolution;
import Solution.Problem;

public class LocalMaxTest<N extends Number> implements Problem<N> {

	@Override
	public double value(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		double error = 0;
		for(Number n : solution) {
			error += Math.pow(n.doubleValue(), 3)+ Math.pow(2*n.doubleValue(), 2);
		}
		return 0-error;
	}

	@Override
	public String solutionDetails(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		String s = " [ ";
		for(N n : solution) {
			s += "="+(0-Math.round(Math.pow(n.doubleValue(), 3)+ Math.pow(2*n.doubleValue(), 2)))+",";
		}
		return s.substring(0, s.length()-1)+" ] "+Math.round(solution.value());
	}

	@Override
	public boolean isValid(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		return true;
	}

}
