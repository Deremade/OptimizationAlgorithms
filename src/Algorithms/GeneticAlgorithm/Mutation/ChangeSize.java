package Algorithms.GeneticAlgorithm.Mutation;

import Solution.OptSolution;
import VectorOps.ElemType;
import staticMethods.CollectionMethods;
import staticMethods.RNG;

public interface ChangeSize {
	
	default <T, S extends OptSolution<T, S>> S changeSize(S original, ElemType<T> elemType) {
		S mutant = original.clone();
		//Check for growth
		while(willGrow(mutant))
			mutant.placeElm(elemType.randomElm(), mutant.emptyPlaceCode());
		//Check for shrinking
		while(willShrink(mutant))
			mutant.removeItem(CollectionMethods.random(mutant.placeCodes()));
		return mutant;
	}
	
	<T, S extends OptSolution<T, S>> boolean  willGrow(S solution);
	
	<T, S extends OptSolution<T, S>> boolean willShrink(S solution);

	public static wontChange wontChange() {
		return new wontChange();
	}
	
	public static rubberBandbyInverse rubberBandbyInverse() {
		return new rubberBandbyInverse();
	}
	
	public static rubberBandbyInverse rubberBandbyInverse(int minSize, int maxSize) {
		return new rubberBandbyInverse(minSize, maxSize);
	}
}

class wontChange implements ChangeSize{

	@Override
	public <T, S extends OptSolution<T, S>> boolean willGrow(S solution) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T, S extends OptSolution<T, S>> boolean willShrink(S solution) {
		// TODO Auto-generated method stub
		return false;
	}
}

class rubberBandbyInverse implements ChangeSize {
	int minSize, maxSize;

	public rubberBandbyInverse(int minSize, int maxSize) {
		super();
		this.minSize = minSize;
		this.maxSize = maxSize;
	}
	
	public rubberBandbyInverse() {
		super();
		this.minSize = 1;
		this.maxSize = Integer.MAX_VALUE;
	}

	@Override
	public <T, S extends OptSolution<T, S>> boolean willGrow(S solution) {
		//If it can grow
		if(solution.placeCodes().size() < maxSize)
			// 1/size chance of growing
			return RNG.randDouble() < Math.pow(solution.placeCodes().size(), -1);
		return false;
	}

	@Override
	public <T, S extends OptSolution<T, S>> boolean willShrink(S solution) {
		//If it can shrink
		if(solution.placeCodes().size() > minSize)
			//Max chance
			return Math.random() < 0.50-Math.pow(solution.placeCodes().size(), -1);
		return false;
	}
}
