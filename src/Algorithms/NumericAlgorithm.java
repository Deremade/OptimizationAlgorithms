package Algorithms;

import java.util.Collection;
import java.util.Random;

import Solution.AbstractSolution;
import Solution.ElemType;
import Solution.Mutation;
import Solution.OptimizationSolution;
import Solution.Problem;
import Solution.Mutation.mutate;
import Test.CircleProblem;
import staticMethods.NumbersComparitor;

public class NumericAlgorithm<N extends Number> extends AbstractAlgorithm<N>{
	public static Random r = new Random();
	int initialSize;

	public NumericAlgorithm(int algTp, Problem<N> problem, int initialSize) {
		super(algTp, problem);
		this.initialSize = initialSize;
	}
	
	public NumericAlgorithm(Mutation.mutate algTp, Problem<N> problem, int initialSize) {
		super(algTp, problem);
		this.initialSize = initialSize;
	}
	
	
	
	@Override
	public OptimizationSolution<N> randomSolution() {
		// TODO Auto-generated method stub
		AbstractSolution<N> newSolution = new AbstractSolution<N>(problem, this);
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

}

