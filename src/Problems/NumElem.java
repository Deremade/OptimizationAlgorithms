package Problems;

import VectorOps.ElemType;
import staticMethods.NumbersComparitor;
import staticMethods.RNG;

public class NumElem implements ElemType<Number> {
	Number min;
	Number max;

	@Override
	public Number add(Number a, Number b) {
		// TODO Auto-generated method stub
		return NumbersComparitor.addNumbers(a, b);
	}

	@Override
	public Number difference(Number a, Number b) {
		// TODO Auto-generated method stub
		if(NumbersComparitor.moreThan(a, b))
			return NumbersComparitor.addNumbers(a,
					NumbersComparitor.multiplyNumbers(b, -1));
		else
			return NumbersComparitor.addNumbers(b,
					NumbersComparitor.multiplyNumbers(a, -1));
	}

	@Override
	public Number scale(Number elem, double scale) {
		// TODO Auto-generated method stub
		return NumbersComparitor.multiplyNumbers(elem, scale);
	}

	@Override
	public Number randomElm() {
		// TODO Auto-generated method stub
		Number range = NumbersComparitor.multiplyNumbers(RNG.randDouble(), 
				difference(min, max));
		return NumbersComparitor.addNumbers(range, min);
	}

}
