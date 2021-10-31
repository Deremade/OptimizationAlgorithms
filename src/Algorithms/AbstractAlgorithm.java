package Algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import Algorithms.GeneticAlgorithm.crossover;
import Solution.ElemType;
import Solution.Mutation;
import Solution.OptimizationSolution;
import Solution.Problem;
import Solution.VectorOperations;
import staticMethods.SolutionMatcher;

public abstract class AbstractAlgorithm<E> implements OptAlgorithm<E>, Mutation<E>, VectorOperations<E> {
	Collection<OptimizationSolution<E>> solutions = new LinkedList<OptimizationSolution<E>>();
	mutate mutationType;
	public Problem<E> problem;
	public GeneticAlgorithmInstance<E> evolutionary_algorithm = null;
	PSOalgorithmInstance<E> pos_algorithm;
	public VectorOperations<E> vo = new VectorOpInstance<E>(this);
	int algType = 0;
	
	public void setParticleSwarmOpt(double nearDist, double localBestScale, double overallBestscale, double localWorstScale, double curVelScale) {
		pos_algorithm = new PSOalgorithmInstance<E>(nearDist, localBestScale,overallBestscale,localWorstScale, curVelScale, vo);
	}
	
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
		if(algType == 2)
			pos_algorithm.changeAll(solutions);
	}
	
	public void setAlgType(int i) {
		algType = i;
	}
	
	public void simpleChange() {
		HashMap<OptimizationSolution<E>, OptimizationSolution<E>> hm = new HashMap<OptimizationSolution<E>, OptimizationSolution<E>>();
		for(OptimizationSolution<E> solution : solutions) {
			OptimizationSolution<E> newSolution = mutatedCopy(solution, mutationMethod());
			if(newSolution.betterThan(solution)) {
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


	@Override
	public ElemType<E> elmType() {
		// TODO Auto-generated method stub
		return null;
	}
}


class VectorOpInstance<E> implements VectorOperations<E> {
	public VectorOpInstance(AbstractAlgorithm<E> aA) {
		super();
		AA = aA;
	}

	AbstractAlgorithm<E> AA;

	@Override
	public <S extends OptimizationSolution<E>> double solutionLength(S solution) {
		// TODO Auto-generated method stub
		return AA.solutionLength(solution);
	}

	@Override
	public ElemType<E> elmType() {
		// TODO Auto-generated method stub
		return AA.elmType();
	}
	
}