package Problems;

import Solution.OptSolution;

public class CircleProblem<S extends OptSolution<Double, S>> implements Problem<Double, S> {

	public double value(S solution) {
		double length = 0;
		for(String code : solution.placeCodes()) {
			length += Math.pow(solution.getElm(code), 2);
		}
		return 10-Math.pow(length, 0.5);
	}

	@Override
	public boolean isValid(S solution) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String solutionDetails(S solution) {
		String coordinates = "[";
		for(String code : solution.placeCodes()) {
			coordinates += (Math.round(solution.getElm(code)*100))/100+",";
		}
		coordinates.substring(0, coordinates.length()-1);
		coordinates+="]";
		return coordinates+"="+Math.round(value(solution)*10);
	}

	@Override
	public int compare(S sol1, S sol2) {
		// TODO Auto-generated method stub
		return sol1.compareTo(sol2);
	}

}
