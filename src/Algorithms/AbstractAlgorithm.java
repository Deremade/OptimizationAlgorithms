package Algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import Solution.ElemType;
import Solution.MutationMethod;
import Solution.OptimizationSolution;
import Solution.Problem;
import Solution.VectorOperations;
import staticMethods.SolutionMatcher;

public abstract class AbstractAlgorithm<E> implements OptAlgorithm<E>, VectorOperations<E> {
	Collection<OptimizationSolution<E>> solutions = new LinkedList<OptimizationSolution<E>>();
	public Problem<E> problem;
	public GeneticAlgorithmInstance<E> evolutionary_algorithm = null;
	PSOalgorithmInstance<E> pos_algorithm;
	public VectorOperations<E> vo = new VectorOpInstance<E>(this);
	int algType = 0;
	MutationMethod mutator = MutationMethod.cycle();
	
	public void setParticleSwarmOpt(double nearDist, double localBestScale, double overallBestscale, double localWorstScale, double curVelScale) {
		pos_algorithm = new PSOalgorithmInstance<E>(nearDist, localBestScale,overallBestscale,localWorstScale, curVelScale, vo);
	}
	
	public void setGeneticAlgorithm(Crossover crossMethod, SelectionMethod selectivePressures, SolutionMatcher matingStrategy) {
		evolutionary_algorithm = new GeneticAlgorithmInstance<E>(crossMethod, selectivePressures,
				matingStrategy, vo.elmType());
	}

	public double changeSizeChance() {
		return problem.changeSizeChance();
	}
	
	@Override
	public Collection<OptimizationSolution<E>> solutions() {
		// TODO Auto-generated method stub
		return solutions;
	}
	

	public AbstractAlgorithm(Problem<E> problem) {
		super();
		this.problem = problem;
	}


	@Override
	public void iteration() {
		removeInvalid(solutions);
		// TODO Auto-generated method stub
		if(algType == 0)
			simpleChange();
		if(algType == 1)
			evolutionary_algorithm.generation(solutions, mutator);
		if(algType == 2)
			pos_algorithm.changeAll(solutions);
	}
	
	public void setAlgType(int i) {
		algType = i;
	}
	
	public void simpleChange() {
		HashMap<OptimizationSolution<E>, OptimizationSolution<E>> hm = new HashMap<OptimizationSolution<E>, OptimizationSolution<E>>();
		for(OptimizationSolution<E> solution : solutions) {
			OptimizationSolution<E> newSolution = randomSolution();
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