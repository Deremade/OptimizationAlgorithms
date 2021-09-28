package Solution;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import Solution.OptAlgorithm.AlgorithmType;

public abstract class AbstractAlgorithm<E> implements OptAlgorithm<E> {
	Collection<OptimizationSolution<E>> solutions = new LinkedList<OptimizationSolution<E>>();
	AlgorithmType algType;
	Problem<E> problem;

	@Override
	public Collection<OptimizationSolution<E>> solutions() {
		// TODO Auto-generated method stub
		return solutions;
	}
	

	public AbstractAlgorithm(AlgorithmType algTp, Problem<E> problem) {
		super();
		this.algType = algTp;
		this.problem = problem;
	}
	
	public AbstractAlgorithm(int algTp, Problem<E> problem) {
		super();
		this.algType = OptAlgorithm.getAlgorithmType(algTp);
		this.problem = problem;
	}


	@Override
	public void iteration(AlgorithmType at) {
		removeInvalid(solutions);
		// TODO Auto-generated method stub
		if(at == AlgorithmType.SimpleChange)
			simpleChange();
	}
	
	public void simpleChange() {
		HashMap<OptimizationSolution<E>, OptimizationSolution<E>> hm = new HashMap<OptimizationSolution<E>, OptimizationSolution<E>>();
		for(OptimizationSolution<E> solution : solutions) {
			OptimizationSolution<E> newSolution = solution.emptySolution();
			solution.copyAndChange(newSolution);
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
	public AlgorithmType algorithmType() {
		// TODO Auto-generated method stub
		return algType;
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
	public void change() {
		// TODO Auto-generated method stub
		AA.change(this);
	}

	@Override
	public OptimizationSolution<E> emptySolution() {
		// TODO Auto-generated method stub
		return new AbstractSolution<E>(problem, AA);
	}
	
}