package Algorithms.GeneticAlgorithm;

import VectorOps.ElemType;

public interface MutationbyElem<T> extends MutationMethod<T>, ChangeSize {

	double mutationChance();

	ElemType<T> elemType();
	
	default <S extends Genome<T, S>> S mutate(S original) {
		//Create mutant solution
		S mutant = original.clone();
		for(String placeCode : original.placeCodes()) //for every place code
			if(Math.random() <= mutationChance()) //at the mitation rate
				mutant.setElement( //set the element to it's mutant version
					mutateElem(original.getElm(placeCode))
					, placeCode);
		return mutant;
	}
	
	T mutateElem(T elem);
	
	ChangeSize changeSize();


	@Override
	public default <T, S extends Genome<T, S>> boolean willGrow(S solution) {
		// TODO Auto-generated method stub
		return changeSize().willGrow(solution);
	}

	@Override
	public default <T, S extends Genome<T, S>> boolean willShrink(S solution) {
		// TODO Auto-generated method stub
		return changeSize().willGrow(solution);
	}

	@Override
	public default <S extends Genome<T, S>> S mutatedCopy(S original) {
		// TODO Auto-generated method stub
			S mutant = mutate(original);
			mutant = changeSize(mutant, elemType());
			return mutant;
	}
}
