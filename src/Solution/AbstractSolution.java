package Solution;

import java.util.Collection;
import java.util.LinkedList;

import Algorithms.AbstractAlgorithm;

public class AbstractSolution<E> extends LinkedList<E> implements OptimizationSolution<E> {
	boolean autoRemove = false;
	private Problem<E> problem;
	public AbstractAlgorithm<E> AA;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractSolution(Problem<E> problem, AbstractAlgorithm<E> aA) {
		super();
		this.setProblem(problem);
		AA = aA;
	}

	@Override
	public double value() {
		return getProblem().value(this);
	}

	@Override
	public String solutionDetails() {
		return getProblem().solutionDetails(this);
	}

	@Override
	public boolean isValid() {
		if(autoRemove) return false;
		return getProblem().isValid(this);
	}

	@Override
	public void makeInvalid() {
		autoRemove = true;
	}

	@Override
	public OptimizationSolution<E> emptySolution() {
		return new AbstractSolution<E>(getProblem(), AA);
	}

	public Problem<E> getProblem() {
		return problem;
	}

	public void setProblem(Problem<E> problem) {
		this.problem = problem;
	}

	@Override
	public void setElm(E elm, String placeCode) {
		int index = Integer.parseInt(placeCode);
		while(!hasPlaceCode(placeCode))
			this.add(AA.randomSelect());
		this.set(index, elm);
	}

	@Override
	public void placeElm(E elm, String placeCode) {
		int index = Integer.parseInt(placeCode);
		this.add(index, elm);
		
	}

	@Override
	public String findElm(E elm) {
		return ""+this.indexOf(elm);
	}

	@Override
	public E getElm(String placeCode) {
		int index = Integer.parseInt(placeCode);
		return this.get(index);
	}

	@Override
	public Collection<String> placeCodes() {
		LinkedList<String> ll = new LinkedList<String>();
		for(E elm : this)
			ll.add(""+this.indexOf(elm));
		return ll;
	}

	@Override
	public boolean hasPlaceCode(String placeCode) {
		int index = Integer.parseInt(placeCode);
		return this.size() > index;
	}

	@Override
	public Collection<String> emptyPlaceCodes() {
		LinkedList<String> ll = new LinkedList<String>();
		ll.add(""+this.size());
		return ll;
	}
	
}
