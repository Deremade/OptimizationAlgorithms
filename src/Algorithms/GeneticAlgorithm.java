package Algorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Solution.CountingSolution;
import Solution.Matchingsolution;
import Solution.Mutation;
import Solution.OptimizationSolution;
import Solution.SelectionMethod;
import Solution.Splitting;
import Solution.Mutation.mutate;
import staticMethods.CollectionMethods;
import staticMethods.SolutionMatcher;

public interface GeneticAlgorithm<E> extends Splitting<E>{
	public static Random r = new  Random();

	/**
	 * Enumeration of the different ways to cross two solutions together
	 * @author David
	 * 
	 */
	public enum crossover{
		Random, Crisscross, SplitSection, SplitDifference, randomSplit;
	}
	
	public static crossover getCrossoverType(int num) {
		switch (num) {
			case 0 : return crossover.Random;
			case 1 : return crossover.Crisscross;
			case 2 : return crossover.SplitSection;
			case 3 : return crossover.SplitDifference;
			case 4 : return crossover.randomSplit;
			default : return crossover.Random;
		}
	}

	/**
	 * Returns the result of crossing two or more solutions
	 * @param type
	 * Crossover type
	 * @param parents
	 * a set of solutions to cross
	 * @return the child, the result of crossing over
	 */
	default public OptimizationSolution<E> mate(crossover type, Collection<OptimizationSolution<E>> parents) {
		switch(type) {
		case Crisscross:
			return crisscross(parents);
		case Random:
			return randomCrossover(parents);
		case randomSplit:
			return randomSplit(parents);
		case SplitDifference:
			return splitDifferenceCrossover(parents);
		case SplitSection:
			return splitSection(parents);
		default:
			return null;
		}
	}
	
	/**
	 * For each index of each solution, their elements at each index are randomly selected
	 * the result of splitting the difference is then put in the child solution
	 * @param parents
	 * The solutions being crossed
	 * @return child solution
	 */
	public default OptimizationSolution<E> randomSplit(Collection<OptimizationSolution<E>> parents) {
		OptimizationSolution<E> newSolution = CollectionMethods.random(parents).emptySolution();
		OptimizationSolution<E> fullyMixed = fullMix(parents, newSolution);
		LinkedList<OptimizationSolution<E>> solutions = makeList(parents);
		int size = (int) fullyMixed.size()/parents.size();
		OptimizationSolution<E> currSol = CollectionMethods.random(solutions);
		int index = 0;
		for(E elm : fullyMixed) {
			if(newSolution instanceof Matchingsolution<?>)
				newSolution.add( ((Matchingsolution<E>) newSolution)
						.matchingElement(elm, 
								currSol
								));
			if(newSolution instanceof CountingSolution<?>)
				((CountingSolution<E>) newSolution)
				.addCount(elm, 
						((CountingSolution<E>) currSol)
						.countElm(elm));
			index = (index+1) % parents.size();
			if((index+1) % size == 0) {
				solutions.remove(currSol);
				if(solutions.isEmpty()) solutions = makeList(parents);
				currSol = CollectionMethods.random(solutions);
			}
		}
		return newSolution;
	}

	/**
	 * Splits all parent solutions into sections
	 * Stitches those sections back together to make a child solution
	 * @param parents
	 * @return child solution
	 */
	public default OptimizationSolution<E> splitSection(Collection<OptimizationSolution<E>> parents) {
		OptimizationSolution<E> newSolution = CollectionMethods.random(parents).emptySolution();
		OptimizationSolution<E> fullyMixed = fullMix(parents, newSolution);
		LinkedList<OptimizationSolution<E>> solutions = makeList(parents);
		int size = (int) fullyMixed.size()/parents.size();
		int index = 0;
		for(E elm : fullyMixed) {
			if(newSolution instanceof Matchingsolution<?>)
				newSolution.add( ((Matchingsolution<E>) newSolution)
						.matchingElement(elm, 
								solutions.get(0)
								));
			if(newSolution instanceof CountingSolution<?>)
				((CountingSolution<E>) newSolution)
				.addCount(elm, 
						((CountingSolution<E>) solutions.get(0))
						.countElm(elm));
			index = (index+1) % parents.size();
			if((index+1) % size == 0) solutions.remove(0);
			if(solutions.isEmpty()) solutions = makeList(parents);
		}
		return newSolution;
	}

	/**
	 * Crosses solutions by splitting the difference between each gene index
	 * @param parents
	 * @return new solution
	 */
	public default OptimizationSolution<E> splitDifferenceCrossover(Collection<OptimizationSolution<E>> parents) {
		OptimizationSolution<E> newSolution = CollectionMethods.random(parents).emptySolution();
		OptimizationSolution<E> fullyMixed = fullMix(parents, newSolution);
		for(E elm : fullyMixed) {
			if(newSolution instanceof Matchingsolution<?>)
					newSolution.add( split(
							((Matchingsolution<E>) newSolution).matingElements(elm, parents)
							));
				if(newSolution instanceof CountingSolution<?>)
					((CountingSolution<E>) newSolution).addCount(elm, 
							CollectionMethods.average(((CountingSolution<E>) newSolution).countAllElms(elm, parents))
							);
		 }
		 return newSolution;
	}

	/**
	 * Randomly selects a parent at each index to give a child solution that parent's index's element
	 * @param parents
	 * @return child
	 */
	public default OptimizationSolution<E> randomCrossover(Collection<OptimizationSolution<E>> parents)  {
		 OptimizationSolution<E> newSolution = CollectionMethods.random(parents).emptySolution();
		 OptimizationSolution<E> fullyMixed = fullMix(parents, newSolution);
		 for(E elm : fullyMixed) {
			 if(newSolution instanceof Matchingsolution<?>)
					newSolution.add(((Matchingsolution<E>) newSolution).randomMatch(elm, parents));
				if(newSolution instanceof CountingSolution<?>)
					((CountingSolution<E>) newSolution).addCount(elm, 
							((CountingSolution<E>) newSolution).randomCount(elm, parents));
		 }
		 return newSolution;
	}

	/**
	 * Generates a child solution by cycling through each parent
	 * the index incremented each step though the cycle to give the child node one element from each parent each cycle
	 * @param parents
	 * @return
	 */
	public default OptimizationSolution<E> crisscross(Collection<OptimizationSolution<E>> parents) {
		OptimizationSolution<E> newSolution = CollectionMethods.random(parents).emptySolution();
		OptimizationSolution<E> fullyMixed = fullMix(parents, newSolution);
		LinkedList<OptimizationSolution<E>> solutions = makeList(parents);
		int index = 0;
		for(E elm : fullyMixed) {
			if(newSolution instanceof Matchingsolution<?>)
				newSolution.add( ((Matchingsolution<E>) newSolution)
						.matchingElement(elm, 
								solutions.get(index)
								));
			if(newSolution instanceof CountingSolution<?>)
				((CountingSolution<E>) newSolution)
				.addCount(elm, 
						((CountingSolution<E>) solutions.get(index))
						.countElm(elm));
			index = (index+1) % parents.size();
		}
		return newSolution;
	}
	
	static <E> OptimizationSolution<E> fullMix(Collection<OptimizationSolution<E>> solutions, OptimizationSolution<E> solution){
		if(solution instanceof Matchingsolution<?>)
			return ((Matchingsolution<E>) solution).fullyMatching(solutions);
		if(solution instanceof CountingSolution<?>)
			return ((CountingSolution<E>) solution).allElms(solutions);
		return null;
	}

	public default LinkedList<OptimizationSolution<E>> makeList(Collection<OptimizationSolution<E>> parents) {
		LinkedList<OptimizationSolution<E>> list = new LinkedList<OptimizationSolution<E>>();
		for(OptimizationSolution<E> solution : parents) {
			list.add(solution);
		}
		return list;
	}
	
	/**
	 * @return a selection method to be used in the evolutionary selection process
	 */
	SelectionMethod selectionMethod();
	
	/**
	 * @return a matcher to be used in matching for mating
	 */
	SolutionMatcher matcher();
	
	/**
	 * Uses the matcher() to generate a list of match-ups for the solutions
	 * @param solutions
	 * @return a collection of their matches
	 */
	default Collection<LinkedList<OptimizationSolution<E>>> genMatches(Collection<OptimizationSolution<E>> solutions) {
		return matcher().genMatches(solutions);
	}
	
	/**
	 * Uses the selectionMethod() function to trim the population via the selection process
	 * @param population
	 */
	default void subjectToSelection(Collection<OptimizationSolution<E>> population) {
		selectionMethod().subjectToSelection(population);
	}
	
	/**
	 * Simulates "Mating season" by crossing solutions with their match-ups
	 * @param solutions
	 * @return children, all newly produced solutions
	 */
	default Collection<OptimizationSolution<E>> matingSeason(Collection<OptimizationSolution<E>> solutions){
		Collection<LinkedList<OptimizationSolution<E>>> matches = genMatches(solutions);
		LinkedList<OptimizationSolution<E>> children = new LinkedList<OptimizationSolution<E>>();
		for(LinkedList<OptimizationSolution<E>> parents : matches) 
			children.add(mate(crossoverMethod(), parents));
		return children;
	}
	
	/**
	 * @return the crossover method to be used in this algorithm
	 */
	crossover crossoverMethod();
	
	/**
	 * @return the mutation method to be used in this algorithm
	 */
	Mutation.mutate mutationMethod();
	
	/**
	 * Simulates a generation of the algorithm
	 * @param population
	 */
	default void generation(Collection<OptimizationSolution<E>> population, Mutation<E> mutation) {
		population.addAll(matingSeason(population));
		population.addAll(mutation.mutateAll(population, mutationMethod()));
		subjectToSelection(population);
	}
}
