package Algorithms;

import java.util.Collection;

import Solution.ElemType;
import staticMethods.SolutionMatcher;

public class GeneticAlgorithmInstance<E> implements GeneticAlgorithm<E> {
	Crossover crossMethod;
	SelectionMethod selectivePressures;
	
	public GeneticAlgorithmInstance(Crossover crossMethod, SelectionMethod selectivePressures,
			SolutionMatcher matingStrategy, AbstractAlgorithm<E> aA) {
		super();
		this.crossMethod = crossMethod;
		this.selectivePressures = selectivePressures;
		this.matingStrategy = matingStrategy;
		AA = aA;
	}

	SolutionMatcher matingStrategy;
	AbstractAlgorithm<E> AA;

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
		return null;
	}

	@Override
	public Crossover crossoverMethod() {
		// TODO Auto-generated method stub
		return crossMethod;
	}

}
