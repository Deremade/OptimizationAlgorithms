package Algorithms;

import java.util.Collection;

import Solution.ElemType;
import staticMethods.SolutionMatcher;

public class GeneticAlgorithmInstance<E> implements GeneticAlgorithm<E> {
	Crossover crossMethod;
	SelectionMethod selectivePressures;
	
	public GeneticAlgorithmInstance(Crossover crossMethod, SelectionMethod selectivePressures,
			SolutionMatcher matingStrategy, ElemType<E> aA) {
		super();
		this.crossMethod = crossMethod;
		this.selectivePressures = selectivePressures;
		this.matingStrategy = matingStrategy;
		AA = aA;
	}

	SolutionMatcher matingStrategy;
	ElemType<E> AA;

	@Override
	public SelectionMethod selectionMethod() {
		// TODO Auto-generated method stub
		return selectivePressures;
	}

	@Override
	public SolutionMatcher matcher() {
		// TODO Auto-generated method stub
		return matingStrategy;
	}



	@Override
	public ElemType<E> elmType() {
		// TODO Auto-generated method stub
		return AA;
	}

	@Override
	public Crossover crossoverMethod() {
		// TODO Auto-generated method stub
		return crossMethod;
	}

}
