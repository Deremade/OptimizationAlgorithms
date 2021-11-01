package Algorithms;

import java.util.Collection;
import java.util.Random;

import Solution.ElemType;

public class BooleanElm implements ElemType<Boolean> {
	public static Random r = new Random();

	@Override
	public Boolean split(Collection<Boolean> genes) {
		// TODO Auto-generated method stub
		int truth = 0;
		for(Boolean b : genes)
			if(b) truth++;
		return truth > (genes.size()/2);
	}

	@Override
	public Boolean difference(Boolean elm1, Boolean elm2) {
		// TODO Auto-generated method stub
		return !(elm1 == elm2);
	}

	@Override
	public Boolean scale(Boolean elm, double scale) {
		// TODO Auto-generated method stub
		if(scale < 0) return !elm;
		return elm;
	}

	@Override
	public Boolean addElms(Collection<Boolean> elements) {
		// TODO Auto-generated method stub
		boolean result = true;
		for(Boolean b : elements)
			if(!b) result = !result;
		return result;
	}

	@Override
	public Boolean randomSelect() {
		// TODO Auto-generated method stub
		return r.nextBoolean();
	}

	@Override
	public Boolean singleStep(Boolean elm) {
		// TODO Auto-generated method stub
		return !elm;
	}

	@Override
	public Boolean upCycle(Boolean elm) {
		// TODO Auto-generated method stub
		return !elm;
	}

}
