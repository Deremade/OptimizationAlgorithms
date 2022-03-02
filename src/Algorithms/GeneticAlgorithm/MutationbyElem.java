package Algorithms.GeneticAlgorithm;

import VectorOps.ElemType;
import staticMethods.CollectionMethods;

public interface MutationbyElem<T, S extends Genome<T, S>> extends MutationMethod<T, S> {

	double mutationChance();

	ElemType<T> elemType();
	
	default S mutatedCopy(S original) {
		S mutant = mutate(original);
		mutant = changeSize(mutant);
		return mutant;
	}
	
	default S mutate(S original) {
		//Create mutant solution
		S mutant = original.clone();
		for(String placeCode : original.placeCodes()) //for every place code
			if(Math.random() <= mutationChance()) //at the mitation rate
				mutant.setElement( //set the element to it's mutant version
					mutateElem(original.getElm(placeCode))
					, placeCode);
		return mutant;
	}
	
	default S changeSize(S original) {
		S mutant = original.clone();
		//Check for growth
		while(willGrow(mutant))
			mutant.placeElm(elemType().randomElm(), mutant.emptyPlaceCode());
		//Check for shrinking
		while(willSrhink(mutant))
			mutant.removeItem(CollectionMethods.random(mutant.placeCodes()));
		return mutant;
	}
	
	boolean willGrow(S solution);
	
	boolean willSrhink(S solution);
	
	T mutateElem(T elem);
}
