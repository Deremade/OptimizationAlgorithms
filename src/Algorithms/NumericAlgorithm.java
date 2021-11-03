package Algorithms;

import java.util.Random;

import Solution.AbstractSolution;
import Solution.ElemType;
import Solution.OptimizationSolution;
import Solution.Problem;
import Solution.NumericalElm;

public class NumericAlgorithm<N extends Number> extends AbstractAlgorithm<N>{
	public static Random r = new Random();
	int initialSize;
	NumericalElm<N> numElm = new NumericalElm<N>();

	public NumericAlgorithm(Problem<N> problem, int initialSize) {
		super(problem);
		this.initialSize = initialSize;
	}
	
	
	
	@Override
	public OptimizationSolution<N> randomSolution() {
		// TODO Auto-generated method stub
		AbstractSolution<N> newSolution = new AbstractSolution<N>(problem, this.elmType());
		int i = 0;
		while(i < initialSize) {
			newSolution.add(elmType().randomSelect());
			i++;
		}
		return newSolution;
	}


	@Override
	protected void change(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		solution = randomSolution();
	}

	@Override
	public <S extends OptimizationSolution<N>> double solutionLength(S solution) {
		// TODO Auto-generated method stub
		double error = 0;
		for(String n : solution.placeCodes()) {
			error += Math.pow(solution.getElm(n).doubleValue(), 2);
		}
		return Math.pow(error, 0.5);
	}

	@Override
	public ElemType<N> elmType() {
		// TODO Auto-generated method stub
		return numElm;
	}

}

