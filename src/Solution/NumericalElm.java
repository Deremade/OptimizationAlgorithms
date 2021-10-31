package Solution;

import java.util.Collection;
import java.util.Random;

import staticMethods.NumbersComparitor;

public class NumericalElm<N extends Number> implements ElemType<N> {
	public static Random r = new Random();
	
	public void setBounds(N min, N max, N step) {
		this.min = min;
		this.max = max;
		this.step = step;
	}
	

	@SuppressWarnings("unchecked")
	N min = (N) NumbersComparitor.addNumbers(0, -10);
	@SuppressWarnings("unchecked")
	N max  = (N) NumbersComparitor.addNumbers(0, 10);
	@SuppressWarnings("unchecked")
	N step = (N) NumbersComparitor.addNumbers(0, 1);

	@SuppressWarnings("unchecked")
	N rng() {
		if(min instanceof Integer) {
			int range = (int) NumbersComparitor.addNumbers(max, negate(min));
			return (N) NumbersComparitor.addNumbers(r.nextInt(range+1), min);
		}
		Number range = NumbersComparitor.addNumbers(max, negate(min));
		N point = (N) NumbersComparitor.multiplyNumbers(range, r.nextDouble());
		N result = (N) NumbersComparitor.addNumbers(point, min);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	N wrap(N number) {
		return (N) NumbersComparitor.cycle(min, max, number);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N upCycle(N gene) {
		N up = (N) NumbersComparitor.addNumbers(gene, step);
		up = wrap(up);
		return up;
	}

	@SuppressWarnings("unchecked")
	@Override
	public N singleStep(N gene) {
		// TODO Auto-generated method stub
		N stepped = null;
		if(r.nextBoolean())
			stepped = (N) NumbersComparitor.addNumbers(gene, step);
		else
			stepped = (N) NumbersComparitor.addNumbers(gene, negate(step));
		return wrap(stepped);
	}

	@Override
	public N randomSelect()  {
		return rng();
	}
	
	@SuppressWarnings("unchecked")
	public N negate(N input) {
		return (N) NumbersComparitor.multiplyNumbers(input, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N addElms(Collection<N> elements) {
		N result = (N) NumbersComparitor.addNumbers(0, 0);
		for(N element : elements)
			result = (N) NumbersComparitor.addNumbers(result, element);
		return wrap(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N difference(N elm1, N elm2) {
		return (N) NumbersComparitor.addNumbers(elm1, negate(elm2));
	}

	@SuppressWarnings("unchecked")
	@Override
	public N scale(N elm, double scale) {
		return wrap((N) NumbersComparitor.multiplyNumbers(elm, scale));
	}


	@SuppressWarnings("unchecked")
	@Override
	public N split(Collection<N> genes) {
		N average = (N) NumbersComparitor.addNumbers(0, 0);
		int size = 0;
		for(N gene : genes) {
			size++;
			//Dynamically add to the average
			average = (N) NumbersComparitor.addNumbers(average,
					NumbersComparitor.divideNumbers(
					NumbersComparitor.addNumbers(gene, negate(average))
					, size));
		}
		return average;
	}
}
