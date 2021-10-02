package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Solution.Mutation.mutate;

public interface GeneticAlgorithm<E> extends Mutation<E>{
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
		 int maxSize = 0;
		 OptimizationSolution<E> newSolution = null;
		 for(OptimizationSolution<E> parent : parents) {
			 if (parent.size() > maxSize)
				 maxSize = parent.size();
			 if(newSolution == null) newSolution = parent.emptySolution();
		 }
		 while(newSolution.size() < maxSize) {
			 LinkedList<E> list = new LinkedList<E>();
			 for(OptimizationSolution<E> parent : parents) {
				 if (parent.size() > newSolution.size())
					 if(Math.random()*parents.size() >= 1)
						 list.add(parent.get(newSolution.size()));
			 }
			 if(!list.isEmpty())
				 newSolution.add(split(list));
		 }
		 return newSolution;
	}

	public default LinkedList<OptimizationSolution<E>> makeList(Collection<OptimizationSolution<E>> parents) {
		LinkedList<OptimizationSolution<E>> list = new LinkedList<OptimizationSolution<E>>();
		for(OptimizationSolution<E> solution : parents) {
			list.add(solution);
		}
		return list;
	}

	/**
	 * Splits all parent solutions into sections
	 * Stitches those sections back together to make a child solution
	 * @param parents
	 * @return child solution
	 */
	public default OptimizationSolution<E> splitSection(Collection<OptimizationSolution<E>> parents) {
		 int maxSize = 0;
		 OptimizationSolution<E> newSolution = null;
		 for(OptimizationSolution<E> parent : parents) {
			 if (parent.size() > maxSize)
				 maxSize = parent.size();
			 if(newSolution == null) newSolution = parent.emptySolution();
		 }
		 while(newSolution.size() < maxSize) {
			 for(OptimizationSolution<E> parent : parents) {
				int sectionLength = r.nextInt(Math.max(1, parent.size()+(2-newSolution.size())));
				while(sectionLength > 1) {
					newSolution.add(parent.get(newSolution.size()));
					sectionLength--;
				}
			 }
		 }
		 return newSolution;
	}

	/**
	 * Crosses solutions by splitting the difference between each gene index
	 * @param parents
	 * @return new solution
	 */
	public default OptimizationSolution<E> splitDifferenceCrossover(Collection<OptimizationSolution<E>> parents) {
		 int maxSize = 0;
		 OptimizationSolution<E> newSolution = null;
		 for(OptimizationSolution<E> parent : parents) {
			 if (parent.size() > maxSize)
				 maxSize = parent.size();
			 if(newSolution == null) newSolution = parent.emptySolution();
		 }
		 while(newSolution.size() < maxSize) {
			 LinkedList<E> list = new LinkedList<E>();
			 for(OptimizationSolution<E> parent : parents) {
				 if (parent.size() > newSolution.size())
					list.add(parent.get(newSolution.size()));
			 }
			 newSolution.add(split(list));
		 }
		 return newSolution;
	}

	/**
	 * Randomly selects a parent at each index to give a child solution that parent's index's element
	 * @param parents
	 * @return child
	 */
	public default OptimizationSolution<E> randomCrossover(Collection<OptimizationSolution<E>> parents)  {
		 int maxSize = 0;
		 OptimizationSolution<E> newSolution = null;
		 for(OptimizationSolution<E> parent : parents) {
			 if (parent.size() > maxSize)
				 maxSize = parent.size();
			 if(newSolution == null) newSolution = parent.emptySolution();
		 }
		 while(newSolution.size() < maxSize) {
			 for(OptimizationSolution<E> parent : parents) {
				 if (parent.size() > newSolution.size())
					 if(Math.random()*parents.size() >= 1)
					 	newSolution.add(parent.get(newSolution.size()));
			 }
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
		 int maxSize = 0;
		 OptimizationSolution<E> newSolution = null;
		 for(OptimizationSolution<E> parent : parents) {
			 if (parent.size() > maxSize)
				 maxSize = parent.size();
			 if(newSolution == null) newSolution = parent.emptySolution();
		 }
		 while(newSolution.size() < maxSize) {
			 for(OptimizationSolution<E> parent : parents) {
				 if (parent.size() > newSolution.size())
					 newSolution.add(parent.get(newSolution.size()));
			 }
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
	
	default Collection<OptimizationSolution<E>> mutateAll(Collection<OptimizationSolution<E>> solutions){
		LinkedList<OptimizationSolution<E>> newSolutions = new LinkedList<OptimizationSolution<E>>();
		for(OptimizationSolution<E> base : solutions) {
			newSolutions.add(mutatedCopy(base, mutationMethod()));
		}
		return newSolutions;
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
	default void generation(Collection<OptimizationSolution<E>> population) {
		population.addAll(matingSeason(population));
		population.addAll(mutateAll(population));
		subjectToSelection(population);
	}
}
