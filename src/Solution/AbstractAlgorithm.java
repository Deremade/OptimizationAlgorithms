package Solution;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import Solution.GeneticAlgorithm.crossover;

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
			evolutionary_algorithm.generation(solutions);
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

}

class AbstractSolution<E> extends LinkedList<E> implements OptimizationSolution<E> {
	boolean autoRemove = false;
	Problem<E> problem;
	AbstractAlgorithm<E> AA;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractSolution(Problem<E> problem, AbstractAlgorithm<E> aA) {
		super();
		this.problem = problem;
		AA = aA;
	}

	@Override
	public double value() {
		// TODO Auto-generated method stub
		return problem.value(this);
	}

	@Override
	public String solutionDetails() {
		// TODO Auto-generated method stub
		return problem.solutionDetails(this);
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		if(autoRemove) return false;
		return problem.isValid(this);
	}

	@Override
	public void makeInvalid() {
		// TODO Auto-generated method stub
		autoRemove = true;
	}

	@Override
	public OptimizationSolution<E> emptySolution() {
		// TODO Auto-generated method stub
		return new AbstractSolution<E>(problem, AA);
	}
	
}