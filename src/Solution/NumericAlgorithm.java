package Solution;

import java.util.Random;

import Solution.OptAlgorithm.AlgorithmType;
import Test.CircleProblem;

public class NumericAlgorithm<N extends Number> extends AbstractAlgorithm<N>{
	public static Random r = new Random();
	int initialSize;

	public NumericAlgorithm(int algTp, Problem<N> problem, int initialSize, N min, N max, N step) {
		super(algTp, problem);
		this.initialSize = initialSize;
		this.min = min;
		this.max = max;
		this.step = step;
	}
	
	public NumericAlgorithm(AlgorithmType algTp, Problem<N> problem, int initialSize, N min, N max, N step) {
		super(algTp, problem);
		this.initialSize = initialSize;
		this.min = min;
		this.max = max;
		this.step = step;
	}
	

	N min;
	N max;
	N step;
	
	@Override
	public OptimizationSolution<N> randomSolution() {
		// TODO Auto-generated method stub
		AbstractSolution<N> newSolution = new AbstractSolution<N>(problem, this);
		int i = 0;
		while(i < initialSize) {
			newSolution.add(rng());
			i++;
		}
		return newSolution;
	}


	@Override
	protected void change(OptimizationSolution<N> solution) {
		// TODO Auto-generated method stub
		solution.clear();;
		solution.addAll(randomSolution());
	}

	@SuppressWarnings("unchecked")
	N rng() {
		if(min instanceof Integer) {
			int range = (int) NumbersComparitor.addNumbers(max, NumbersComparitor.multiplyNumbers(min, -1));
			return (N) NumbersComparitor.addNumbers(r.nextInt(range+1), min);
		}
		Number range = NumbersComparitor.addNumbers(max, NumbersComparitor.multiplyNumbers(min, -1));
		N point = (N) NumbersComparitor.multiplyNumbers(range, r.nextDouble());
		N result = (N) NumbersComparitor.addNumbers(point, min);
		return result;
	}
	
	N wrap(N number) {
		if(NumbersComparitor.lessThan(number, min)) return min;
		if(NumbersComparitor.moreThan(number, max)) return max;
		return number;
	}
	
	
}

