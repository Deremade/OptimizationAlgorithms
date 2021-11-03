package Solution;

import java.util.Random;

import staticMethods.SolutionMethods;

public abstract class Mutator {

}

interface MutationMethod {
	public static Random r = new Random();
	
	public <E, S extends OptimizationSolution<E>> S mutate(S sol, ElemType<E> elmType);
	/**
	 * @param base
	 * The base solution being added to
	 */
	public default <E, S extends OptimizationSolution<E>> void addElement(S base, ElemType<E> elmType) {
		base.placeElm(elmType.randomSelect(), base.emptyPlaceCode());
	}
	
	/**
	 * @param base
	 * the base solution being removed from
	 */
	public default <E> void removeElement(OptimizationSolution<E> base) {
		if(base.placeCodes().size() > 1)
			base.removeItem(
					SolutionMethods.getRandom(base.placeCodes())
					);
	}
	
	/**
	 * add or remove an element from the solution
	 * @param base
	 * The base solution being modified
	 */
	public default <E, S extends OptimizationSolution<E>> void changeSize(S base, ElemType<E> elmType, double mutationChance) {
		if(mutationChance == 0) return;
		if(r.nextDouble() <= mutationChance) {
			if(r.nextBoolean())
				addElement(base, elmType);
			else
				removeElement(base);
		}
	}
	
}

class Cycle implements MutationMethod {

	@Override
	public <E, S extends OptimizationSolution<E>> S mutate(S sol, ElemType<E> elmType) {
		S newGenome = sol.emptySolution();
		for(String gene : sol.placeCodes())
			if(r.nextBoolean())
				newGenome.placeElm(elmType.upCycle(sol.getElm(gene)), gene);
			else newGenome.placeElmFrom(gene, sol);
		return newGenome;
	}
	
}

class Step implements MutationMethod {

	@Override
	public <E, S extends OptimizationSolution<E>> S mutate(S sol, ElemType<E> elmType) {
		S newGenome = sol.emptySolution();
		for(String gene : sol.placeCodes())
			if(r.nextBoolean())
				newGenome.placeElm(elmType.singleStep(sol.getElm(gene)), gene);
			else newGenome.placeElmFrom(gene, sol);
		return newGenome;
	}
	
}

class RandomElms implements MutationMethod {

	@Override
	public <E, S extends OptimizationSolution<E>> S mutate(S sol, ElemType<E> elmType) {
		S newGenome = sol.emptySolution();
		for(String gene : sol.placeCodes())
			if(r.nextBoolean())
				newGenome.placeElm(elmType.randomSelect(), gene);
			else
				newGenome.placeElmFrom(gene, sol);
		return newGenome;
	}
	
}

class RandomSplit implements MutationMethod {

	@Override
	public <E, S extends OptimizationSolution<E>> S mutate(S sol, ElemType<E> elmType) {
		S newGenome = sol.emptySolution();
		for(String gene : sol.placeCodes())
			if(r.nextBoolean())
				newGenome.placeElm(elmType.split(sol.getElm(gene), elmType.randomSelect()), gene);
		return newGenome;
	}
	
}