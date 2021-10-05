package Algorithms;

import java.util.Collection;

import Solution.Mutation;
import Solution.SelectionMethod;
import Solution.Mutation.mutate;
import staticMethods.SolutionMatcher;

public class GeneticAlgorithmInstance<E> implements GeneticAlgorithm<E> {
	crossover crossMethod;
	SelectionMethod selectivePressures;
	
	public GeneticAlgorithmInstance(crossover crossMethod, SelectionMethod selectivePressures,
			SolutionMatcher matingStrategy, AbstractAlgorithm<E> aA) {
		super();
		this.crossMethod = crossMethod;
		this.selectivePressures = selectivePressures;
		this.matingStrategy = matingStrategy;
		AA = aA;
	}
	
	public GeneticAlgorithmInstance(int crossMethod, SelectionMethod selectivePressures,
			SolutionMatcher matingStrategy, AbstractAlgorithm<E> aA) {
		super();
		this.crossMethod = GeneticAlgorithm.getCrossoverType(crossMethod);
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
	public crossover crossoverMethod() {
		// TODO Auto-generated method stub
		return crossMethod;
	}

	@Override
	public E split(Collection<E> genes) {
		// TODO Auto-generated method stub
		return AA.split(genes);
	}

	@Override
	public mutate mutationMethod() {
		// TODO Auto-generated method stub
		return AA.mutationMethod();
	}

}
