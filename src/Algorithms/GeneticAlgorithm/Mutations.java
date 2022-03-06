package Algorithms.GeneticAlgorithm;

import Algorithms.OptAlgorithm;
import VectorOps.ElemType;

public class Mutations {
	
}

class reRollMutation<T> implements MutationMethod<T>{
	OptAlgorithm<T, ?> alg;

	@SuppressWarnings("unchecked")
	@Override
	public <S extends Genome<T, S>> S mutatedCopy(S original) {
		// TODO Auto-generated method stub
		return (S) alg.randomSolution();
	}
	
}

class randomMutations<T> implements MutationbyElem<T> {
	ElemType<T> elementType;
	ChangeSize changeSize;
	double mutationChance;

	@Override
	public double mutationChance() {
		// TODO Auto-generated method stub
		return mutationChance;
	}

	public ElemType<T> elemType() {
		// TODO Auto-generated method stub
		return elementType;
	}

	@Override
	public T mutateElem(T elem) {
		// TODO Auto-generated method stub
		return elemType().randomElm();
	}


	@Override
	public ChangeSize changeSize() {
		// TODO Auto-generated method stub
		return changeSize;
	}

}