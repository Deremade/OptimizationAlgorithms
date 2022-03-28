package Problems.AllTrueTest;

import VectorOps.ElemType;
import staticMethods.RNG;

public class BoolElem implements ElemType<Boolean> {

	@Override
	public Boolean add(Boolean a, Boolean b) {
		// TODO Auto-generated method stub
		return a || b;
	}

	@Override
	public Boolean difference(Boolean a, Boolean b) {
		// TODO Auto-generated method stub
		return !(a || b);
	}

	@Override
	public Boolean scale(Boolean elem, double scale) {
		return !(elem ^ (scale > 0));
	}

	@Override
	public Boolean randomElm() {
		return RNG.randBoolean();
	}

}
