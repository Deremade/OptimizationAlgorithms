package Solution;

import java.util.Collection;
import java.util.Random;

public interface GeneticAlgorithm<E> {
	public static Random r = new  Random();

	public enum crossover{
		Random, SplitSection, SplitDifference, Crisscross;
	}

	public enum mutate{
		SplitDifference, pureRandom, step, reRoll
	}
	
	default public void geneticMutation(mutate type, OptimizationSolution<E> genome) {
		switch(type) {
		case SplitDifference:
			splitDifference(genome);
			break;
		case pureRandom:
			random(genome);
			break;
		case reRoll:
			reRoll(genome);
			break;
		case step:
			step(genome);
			break;
		default:
			break;
		
		}
	}

	public default void step(OptimizationSolution<E> genome) {
		for(E gene : genome)
			if(r.nextBoolean())
				gene = singleStep(gene);
	}
	
	public E singleStep(E gene);
	
	public default void random(OptimizationSolution<E> genome) {
		for(E gene : genome)
			if(r.nextBoolean())
				gene = randomSelect();
	}
	
	public default void reRoll(OptimizationSolution<E> genome) {
		for(E gene : genome)
			gene = randomSelect();
	}
	
	public E randomSelect();
	
	public default void splitDifference(OptimizationSolution<E> genome) {
		for(E gene : genome)
			gene = split(gene, randomSelect());
	}
	
	public E split(E gene1, E gene2);
	
	default public void mate(crossover type, Collection<OptimizationSolution<E>> parents) {
		switch(type) {
		case Crisscross:
			crisscross(parents);
			break;
		case Random:
			randomCrossover(parents);
			break;
		case SplitDifference:
			splitDifferenceCrossover(parents);
			break;
		case SplitSection:
			splitSection(parents);
			break;
		default:
			break;
		}
	}

	public void splitSection(Collection<OptimizationSolution<E>> parents);

	public void splitDifferenceCrossover(Collection<OptimizationSolution<E>> parents);

	public void randomCrossover(Collection<OptimizationSolution<E>> parents);

	public void crisscross(Collection<OptimizationSolution<E>> parents);
}
