package Solution;

import java.util.Random;

public class NumericAlgorithm<N extends Number> extends AbstractAlgorithm<N>{
	public static Random r = new Random();
	int initialSize;
	public NumericAlgorithm(int initialSize, N min, N max, N step) {
		super();
		this.initialSize = initialSize;
		this.min = min;
		this.max = (N) NumbersComparitor.addNumbers(max, 1);
		this.step = step;
	}

	N min;
	N max;
	N step;
	
	@Override
	public OptimizationSolution<N> randomSolution() {
		// TODO Auto-generated method stub
		AbstractSolution<N> newSolution = new AbstractSolution<N>(null, this);
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
			return (N) NumbersComparitor.addNumbers(r.nextInt(range), min);
		}
		Number range = NumbersComparitor.addNumbers(max, NumbersComparitor.multiplyNumbers(min, -1));
		System.out.println(range);
		N point = (N) NumbersComparitor.multiplyNumbers(range, r.nextDouble());
		System.out.println(point);
		N result = (N) NumbersComparitor.addNumbers(point, min);
		System.out.println(result);
		return result;
	}
	
	N wrap(N number) {
		if(NumbersComparitor.lessThan(number, min)) return min;
		if(NumbersComparitor.moreThan(number, max)) return max;
		return number;
	}
	
	
}

