package Algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import Algorithms.GeneticAlgorithm.crossover;
import Solution.AbstractSolution;
import Solution.Mutation;
import Solution.OptimizationSolution;
import Solution.Problem;
import Solution.SelectionMethod;
import Solution.VectorOperations;
import Solution.Mutation.mutate;
import staticMethods.SolutionMatcher;

public abstract class AbstractAlgorithm<E> implements OptAlgorithm<E>, Mutation<E> {
	Collection<OptimizationSolution<E>> solutions = new LinkedList<OptimizationSolution<E>>();
	mutate mutationType;
	Problem<E> problem;
	GeneticAlgorithmInstance<E> evolutionary_algorithm = null;
	int algType = 0;
	
	
	public void setGeneticAlgorithm(crossover crossMethod, SelectionMethod selectivePressures, SolutionMatcher matingStrategy) {
		evolutionary_algorithm = new GeneticAlgorithmInstance<E>(crossMethod, selectivePressures,
				matingStrategy, this);
	}

	public void setGeneticAlgorithm(int crossoverType, SelectionMethod selectivePressures, SolutionMatcher matingStrategy) {
		// TODO Auto-generated method stub
		setGeneticAlgorithm(GeneticAlgorithm.getCrossoverType(crossoverType), selectivePressures, matingStrategy);
	}

	public double changeSizeChance() {
		return problem.changeSizeChance();
	}
	
	@Override
	public mutate mutationMethod() {
		// TODO Auto-generated method stub
		return mutationType;
	}
	
	@Override
	public Collection<OptimizationSolution<E>> solutions() {
		// TODO Auto-generated method stub
		return solutions;
	}
	

	public AbstractAlgorithm(mutate algTp, Problem<E> problem) {
		super();
		this.mutationType = algTp;
		this.problem = problem;
	}
	
	public AbstractAlgorithm(int algTp, Problem<E> problem) {
		super();
		this.mutationType = Mutation.getMutationmType(algTp);
		this.problem = problem;
	}


	@Override
	public void iteration() {
		removeInvalid(solutions);
		// TODO Auto-generated method stub
		if(algType == 0)
			simpleChange();
		if(algType == 1)
			evolutionary_algorithm.generation(solutions, this);
	}
	
	public void setAlgType(int i) {
		algType = i;
	}
	
	public void simpleChange() {
		HashMap<OptimizationSolution<E>, OptimizationSolution<E>> hm = new HashMap<OptimizationSolution<E>, OptimizationSolution<E>>();
		for(OptimizationSolution<E> solution : solutions) {
			OptimizationSolution<E> newSolution = mutatedCopy(solution, mutationMethod());
			if(solution.value() < newSolution.value()) {
				if(newSolution.isValid())
					hm.put(solution, newSolution);
			}
		}
		for(Entry<OptimizationSolution<E>, OptimizationSolution<E>> replace : hm.entrySet()) {
			solutions.remove(replace.getKey());
			solutions.add(replace.getValue());
		}
	}

	@Override
	public void fillSolutions(int size) {
		// TODO Auto-generated method stub
		while(size > 0) {
			solutions.add(randomSolution());
			size--;
		}
	}

	protected abstract void change(OptimizationSolution<E> solution);

	public abstract E add(Collection<E> elements);

	public abstract E difference(E elm1, E elm2);

	public abstract E scale(E elm, double scale);

	public abstract double distance(OptimizationSolution<E> elm1, OptimizationSolution<E> elm2);

}


class VectorOpInstance<E> implements VectorOperations<E> {
	AbstractAlgorithm<E> AA;

	@Override
	public E add(Collection<E> elements) {
		// TODO Auto-generated method stub
		return AA.add(elements);
	}

	@Override
	public E difference(E elm1, E elm2) {
		// TODO Auto-generated method stub
		return AA.difference(elm1, elm2);
	}

	@Override
	public E scale(E elm, double scale) {
		// TODO Auto-generated method stub
		return AA.scale(elm, scale);
	}

	@Override
	public double distance(OptimizationSolution<E> elm1, OptimizationSolution<E> elm2) {
		// TODO Auto-generated method stub
		return AA.distance(elm1, elm2);
	}
	
}