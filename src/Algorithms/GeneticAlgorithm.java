package Algorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Solution.ElemType;
import Solution.Mutation;
import Solution.OptimizationSolution;
import Solution.Splitting;
import Solution.Mutation.mutate;
import staticMethods.SolutionMatcher;
import staticMethods.SolutionMethods;

public interface GeneticAlgorithm<E>{
	public static Random r = new  Random();
	
	ElemType<E> elmType();

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

	public default <S extends OptimizationSolution<E>> LinkedList<S> makeList(Collection<S> parents) {
		LinkedList<S> list = new LinkedList<S>();
		for(S solution : parents) {
			list.add(solution);
		}
		return list;
	}
	
	/**
	 * For each index of each solution, their elements at each index are randomly selected
	 * the result of splitting the difference is then put in the child solution
	 * @param parents
	 * The solutions being crossed
	 * @return child solution
	 */
	public default <S extends OptimizationSolution<E>> S randomSplit(Collection<S> parents) {
		S newSolution = SolutionMethods.getRandom(parents).emptySolution();
		 for(String code : SolutionMethods.placeCodes(parents)) {
			 Collection<E> elms = SolutionMethods.getElms(code, parents);
			 Collection<E> splitElms = new LinkedList<E>();
			 while(splitElms.isEmpty())
				 for(E elm : elms)
					 if(Math.random() > 0.5)
						 splitElms.add(elm);
			 newSolution.setElm(elmType().split(splitElms), code);
		 }
		 return newSolution;
	}

	/**
	 * Splits all parent solutions into sections
	 * Stitches those sections back together to make a child solution
	 * @param parents
	 * @return child solution
	 */
	public default <S extends OptimizationSolution<E>> S splitSection(Collection<S> parents) {
		S newSolution = SolutionMethods.getRandom(parents).emptySolution();
		Collection<String> codes = SolutionMethods.placeCodes(parents);
		LinkedList<S> ll = makeList(parents);
		int splitSize = (int) Math.ceil(codes.size()/parents.size());
		int index = 0;
		 for(String code : codes) {
			 newSolution.placeElm(ll.get((int) Math.floorDiv(index, splitSize)).getElm(code), code);
			 index++;
		 }
		 return newSolution;
	}

	/**
	 * Crosses solutions by splitting the difference between each gene index
	 * @param parents
	 * @return new solution
	 */
	public default <S extends OptimizationSolution<E>> S splitDifferenceCrossover(Collection<S> parents) {
		S newSolution = SolutionMethods.getRandom(parents).emptySolution();
		 for(String code : SolutionMethods.placeCodes(parents))
			 newSolution.setElm(elmType().split(SolutionMethods.getElms(code, parents)), code);
		 return newSolution;
	}

	/**
	 * Randomly selects a parent at each index to give a child solution that parent's index's element
	 * @param parents
	 * @return child
	 */
	public default OptimizationSolution<E> randomCrossover(Collection<OptimizationSolution<E>> parents)  {
		 OptimizationSolution<E> newSolution = SolutionMethods.getRandom(parents).emptySolution();
		 for(String code : SolutionMethods.placeCodes(parents))
			 newSolution.setElm(SolutionMethods.randomElmAtPlaceCode(code, parents), code);
		 return newSolution;
	}

	/**
	 * Generates a child solution by cycling through each parent
	 * the index incremented each step though the cycle to give the child node one element from each parent each cycle
	 * @param parents
	 * @return 
	 * @return
	 */
	public default <S extends OptimizationSolution<E>> S crisscross(Collection<S> parents) {
		 S newSolution = SolutionMethods.getRandom(parents).emptySolution();
		 LinkedList<S> ll = makeList(parents);
		 int index = 0;
		 S currSolution = ll.get(0);
		 Collection<String> codes = SolutionMethods.placeCodes(parents);
		 for(String code : codes) {
			 currSolution = ll.get(index % ll.size());
			 while(!currSolution.hasPlaceCode(code)) {
				 ll.remove(currSolution);
				 if(ll.isEmpty()) ll = makeList(parents);
				 currSolution = ll.get(index % ll.size());
			 }
			newSolution.setElm(currSolution.getElm(code), code);
			index++;
		 }
		 return newSolution;
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
