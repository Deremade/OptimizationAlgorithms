package Test;

import Solution.OptimizationSolution;
import Solution.Problem;

/**
 * @author David
 *
 * @param <N> Type of number
 * Is used to test for local optimum, or local minimum, as it uses a sun function such that there are a lot of local optimal functions
 */
public class LocalMaxTest<N extends Number> implements Problem<N> {

	@Override
	public double value(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		double error = 0;
		for(String elm : solution.placeCodes()) {
			Number n = solution.getElm(elm);
			error += 2*Math.sin(n.doubleValue()+11/8)-(n.doubleValue()+11.8);
		}
		return error;
	}

	@Override
	public String solutionDetails(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		String s = " [ ";
		for(String elm : solution.placeCodes()) {
			Number n = solution.getElm(elm);
			s += "="+(0-Math.round(Math.pow(n.doubleValue(), 3)+ Math.pow(2*n.doubleValue(), 2)))+",";
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
		return 0.1;
	}

}
