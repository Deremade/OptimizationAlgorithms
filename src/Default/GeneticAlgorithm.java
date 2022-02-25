package Default;
import java.util.Collection;
import java.util.LinkedList;

import Algorithms.OptAlgorithm;

/**
 * @author David
 *
 *An Optimization algorithm inspired by Darwinism, and Natural selection/Genetic mutation and cross-mating
 *
 * @param <T> Element Type
 * @param <S> Solution
 */
public interface GeneticAlgorithm<T, S extends Genome<T, S>> extends OptAlgorithm<T, S> {
	
	/**An OptAlgorithm that holds delegated Methods
	 * @return optAlgorithm
	 */
	OptAlgorithm<T, S> optAlgorithm();

	/* (non-Javadoc)
	 * @see Default.OptAlgorithm#randomSolution()
	 */
	@Override
	default public S randomSolution() {
		// TODO Auto-generated method stub
		return optAlgorithm().randomSolution();
	}

	/* (non-Javadoc)
	 * @see Default.OptAlgorithm#solutions()
	 */
	@Override
	default public Collection<S> solutions() {
		// TODO Auto-generated method stub
		return optAlgorithm().solutions();
	}

	/* (non-Javadoc)
	 * @see Default.OptAlgorithm#iteration()
	 */
	@Override
	public default void iteration() {
		solutions().addAll(matingSeason());
		subjecttoSelection();
	}
	
	/**
	 * @return The population Capacity
	 */
	public int capacity();

	SelectionMethod<T, S> selectionMethod();
	
	/**
	 * Subject to selection such that only a number of solutions equal to the Capacity remains
	 * @return What remains from the selection Methods
	 */
	public default Collection<S> subjecttoSelection() {
		return selectionMethod().subjecttoSelection(solutions(), capacity());
	}
	
	/**
	 * @param parents
	 * The parents of the child
	 * @return a "Child"
	 */
	public S genChild(Collection<S> parents);
	
	/*
	 * Return all parents and children of said parents from mating
	 */
	public Collection<S> matingSeason();
	
	/**
	 * Remove invalid solutions
	 */
	public default void removeInvalid() {
		removeInvalid(solutions());
	}
	
	/**
	 * Find the most fit solutions and return them in a list at Capacity size
	 * @return the most fit solutions
	 */
	public default LinkedList<S> mostFit() {
		return mostFit(solutions(), capacity());
	}
}
