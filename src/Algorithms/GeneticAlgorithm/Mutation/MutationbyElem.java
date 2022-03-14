package Algorithms.GeneticAlgorithm.Mutation;

import Solution.OptSolution;
import VectorOps.ElemType;

public interface MutationbyElem<T> extends MutationMethod<T> {

	double mutationChance();

	ElemType<T> elemType();
	
	default <S extends OptSolution<T, S>> S mutate(S original) {
		//Create mutant solution
		S mutant = original.clone();
		for(String placeCode : original.placeCodes()) //for every place code
			if(Math.random() <= mutationChance()) //at the mutation rate
				mutant.setElement( //set the element to it's mutant version
					mutateElem(original.getElm(placeCode))
					, placeCode);
		return mutant;
	}
	
	T mutateElem(T elem);
	
	ChangeSize changeSize();

	@Override
	public default <S extends OptSolution<T, S>> S mutatedCopy(S original) {
		// TODO Auto-generated method stub
			S mutant = mutate(original);
			mutant = changeSize().changeSize(mutant, elemType());
			return mutant;
	}
}
