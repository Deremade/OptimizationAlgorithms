package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public interface Mutation<E> {
	public static Random r = new Random();

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
	default public void solutionMutation(mutate type, OptimizationSolution<E> genome) {
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
		changeSize(genome);
	}
	
	/**
	 * @param base
	 * The solution to be copied and mutated
	 * @param method
	 * the method of mutation
	 * @return A new mutated copy seperate from the original
	 */
	public default OptimizationSolution<E> mutatedCopy(OptimizationSolution<E> base, mutate method) {
		OptimizationSolution<E> mutant = base.emptySolution();
		for(E element : base)
			mutant.add(element);
		solutionMutation(method, mutant);
		return mutant;
	}
	
	/**
	 * @param base
	 * The base solution being added to
	 */
	public default void addElement(OptimizationSolution<E> base) {
		base.add(r.nextInt(base.size()), randomSelect());
	}
	
	/**
	 * @param base
	 * the base solution being removed from
	 */
	public default void removeElement(OptimizationSolution<E> base) {
		if(base.size() > 1)
			base.remove(r.nextInt(base.size()));
	}
	
	/**
	 * @return the probability of the solution changing it's size during mutation
	 */
	public double changeSizeChance();
	
	/**
	 * add or remove an element from the solution
	 * @param base
	 * The base solution being modified
	 */
	public default void changeSize(OptimizationSolution<E> base) {
		if(r.nextDouble() <= changeSizeChance()) {
			if(r.nextBoolean())
				addElement(base);
			else
				removeElement(base);
		}
	}
	
	/**
	 * @param num
	 * ID of Algorithm Type
	 * @return the AlgorithmType
	 */
	public static mutate getMutationmType(int num) {
		switch (num) {
			case 0 : return mutate.reRoll;
			case 1 : return mutate.pureRandom;
			case 2 : return mutate.SplitDifference;
			case 3 : return mutate.step;
			case 4 : return mutate.cycle;
			default : return mutate.reRoll;
		}
	}
	/**
	 * Makes every part of the solution increment once through the cycle described in upCycle();
	 * @param genome
	 */
	public default void cycle(OptimizationSolution<E> genome) {
		OptimizationSolution<E> newGenome = genome.emptySolution();
		for(E gene : genome)
			if(r.nextBoolean())
				newGenome.add(upCycle(gene));
			else newGenome.add(gene);
		genome.clear();
		genome.addAll(newGenome);
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
		OptimizationSolution<E> newGenome = genome.emptySolution();
		for(E gene : genome)
			if(r.nextBoolean())
				newGenome.add(singleStep(gene));
			else newGenome.add(gene);
		genome.clear();
		genome.addAll(newGenome);
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
		OptimizationSolution<E> newGenome = genome.emptySolution();
		for(E gene : genome)
			if(r.nextBoolean())
				newGenome.add(randomSelect());
			else
				newGenome.add(gene);
		genome.clear();
		genome.addAll(newGenome);
	}
	
	/**
	 * Causes the entire solution to be replaced with a randomly generated element
	 * @param genome
	 * The solution being modified
	 */
	public default void reRoll(OptimizationSolution<E> genome) {
		OptimizationSolution<E> newGenome = genome.emptySolution();
		for(E gene : genome)
			newGenome.add(randomSelect());
		genome.clear();
		genome.addAll(newGenome);
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
}
