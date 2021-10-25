package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import staticMethods.SolutionMethods;

public interface Mutation<E> extends Splitting<E>{
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
		for(String gene : base.placeCodes())
			mutant.placeElmFrom(gene, base);
		solutionMutation(method, mutant);
		return mutant;
	}

	
	default Collection<OptimizationSolution<E>> mutateAll(Collection<OptimizationSolution<E>> solutions, mutate method){
		LinkedList<OptimizationSolution<E>> newSolutions = new LinkedList<OptimizationSolution<E>>();
		for(OptimizationSolution<E> base : solutions) {
			newSolutions.add(mutatedCopy(base, method));
		}
		return newSolutions;
	}
	/**
	 * @param base
	 * The base solution being added to
	 */
	public default <S extends OptimizationSolution<E>> void addElement(S base) {
		base.placeElm(randomSelect(), base.emptyPlaceCode());
	}
	
	/**
	 * @param base
	 * the base solution being removed from
	 */
	public default void removeElement(OptimizationSolution<E> base) {
		if(base.placeCodes().size() > 1)
			base.removeItem(
					SolutionMethods.getRandom(base.placeCodes())
					);
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
		for(String gene : genome.placeCodes())
			if(r.nextBoolean())
				newGenome.placeElm(upCycle(genome.getElm(gene)), gene);
			else newGenome.placeElmFrom(gene, genome);
		genome = newGenome;
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
		for(String gene : genome.placeCodes())
			if(r.nextBoolean())
				newGenome.placeElm(singleStep(genome.getElm(gene)), gene);
			else newGenome.placeElmFrom(gene, genome);
		genome = newGenome;
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
		for(String gene : genome.placeCodes())
			if(r.nextBoolean())
				newGenome.placeElm(randomSelect(), gene);
			else
				newGenome.placeElmFrom(gene, genome);
		genome = newGenome;
	}
	
	/**
	 * Causes the entire solution to be replaced with a randomly generated element
	 * @param genome
	 * The solution being modified
	 */
	public default void reRoll(OptimizationSolution<E> genome) {
		OptimizationSolution<E> newGenome = genome.emptySolution();
		for(String gene : genome.placeCodes())
			newGenome.placeElm(randomSelect(), gene);
		genome = newGenome;
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
		for(String gene : genome.placeCodes())
			if(Math.random() < 1/genome.placeCodes().size())
				genome.setElm( split(genome.getElm(gene), randomSelect()), gene);
	}
}
