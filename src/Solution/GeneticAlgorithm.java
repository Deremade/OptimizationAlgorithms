package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public interface GeneticAlgorithm<E> {
	public static Random r = new  Random();

	/**
	 * Enumeration of the different ways to cross two solutions together
	 * @author David
	 * 
	 */
	public enum crossover{
		Random, Crisscross, SplitSection, SplitDifference, randomSplit;
	}

	/**
	 * Enumeration of different ways to mutate a solution
	 * @author David
	 *
	 */
	public enum mutate{
		cycle, step, SplitDifference, pureRandom, reRoll
	}
	
	/**
	 * @param type
	 * The type of mutation performed
	 * @param genome
	 * The solution that is being mutated
	 */
	default public void geneticMutation(mutate type, OptimizationSolution<E> genome) {
		switch(type) {
		case cycle:
			cycle(genome);
			break;
		case step:
			step(genome);
			break;
		case SplitDifference:
			splitDifference(genome);
			break;
		case pureRandom:
			random(genome);
			break;
		case reRoll:
			reRoll(genome);
			break;
		default:
			break;
		
		}
	}

	/**
	 * Makes every part of the solution increment once through the cycle described in upCycle();
	 * @param genome
	 */
	public default void cycle(OptimizationSolution<E> genome) {
		for(E gene : genome)
			if(r.nextBoolean())
				gene = upCycle(gene);
	}
	
	/**
	 * @param gene
	 * The gene being modified
	 * @return The modified Gene
	 */
	public E upCycle(E gene);

	/**
	 * Causes every element of the solution to be "stepped" as described in singleStep();
	 * @param genome
	 * The solution being modified
	 */
	public default void step(OptimizationSolution<E> genome) {
		for(E gene : genome)
			if(r.nextBoolean())
				gene = singleStep(gene);
	}
	
	/**
	 * Causes the gene to go through one step
	 * @param gene
	 * The element being Modified
	 * @return the modified element
	 */
	public E singleStep(E gene);
	
	/**
	 * Replaces some of the solution with a random element
	 * each element has a 0.5 chance of being replaced
	 * @param genome
	 * The solution being modified
	 */
	public default void random(OptimizationSolution<E> genome) {
		for(E gene : genome)
			if(r.nextBoolean())
				gene = randomSelect();
	}
	
	/**
	 * Causes the entire solution to be replaced with a randomly generated element
	 * @param genome
	 * The solution being modified
	 */
	public default void reRoll(OptimizationSolution<E> genome) {
		for(E gene : genome)
			gene = randomSelect();
	}
	
	/**
	 * @returna random Element
	 */
	public E randomSelect();
	
	/**
	 * Splits the difference between each element in the solution and a random element
	 * @param genome
	 */
	public default void splitDifference(OptimizationSolution<E> genome) {
		for(E gene : genome)
			if(Math.random() < 1/genome.size())
				gene = split(gene, randomSelect());
	}
	
	/**
	 * performs split() on 2 specific genes
	 * @param gene1
	 * @param gene2
	 * @return a result of splitting the difference
	 */
	public default E split(E gene1, E gene2) {
		LinkedList<E> list = new LinkedList<E>();
		list.add(gene1);
		list.add(gene2);
		return split(list);
	}
	
	/**
	 * Splits the difference between a set of elements
	 * @param genes
	 * the set of elements
	 * @return the element representing splitting the difference between the "genes"
	 */
	public E split(Collection<E> genes);
	
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
				 if (parent.size() <= newSolution.size())
					 if(Math.random() <= 1/parents.size())
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
				int sectionLength = r.nextInt(Math.min(0, parent.size()-newSolution.size()));
				while(sectionLength > 0) {
					newSolution.add(parent.get(newSolution.size()));
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
				 if (parent.size() <= newSolution.size())
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
				 if (parent.size() <= newSolution.size())
					 if(Math.random() <= 1/parents.size())
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
				 if (parent.size() <= newSolution.size())
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
			OptimizationSolution<E> mutant = base.emptySolution();
			base.copyInto(mutant);
			geneticMutation(mutationMethod(), mutant);
			
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
	mutate mutationMethod();
	
	/**
	 * Simulates a generation of the algorithm
	 * @param population
	 */
	default void generation(Collection<OptimizationSolution<E>> population) {
		population.addAll(matingSeason(population));
		subjectToSelection(population);
	}
}
