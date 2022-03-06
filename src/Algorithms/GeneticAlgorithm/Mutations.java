package Algorithms.GeneticAlgorithm;

import Algorithms.OptAlgorithm;
import Solution.OptSolution;
import VectorOps.ElemType;

public class Mutations {
	
	public static <T> reRollMutation<T> reRollMutation(OptAlgorithm<T, ?> alg){
		return new reRollMutation<T>(alg);
	}
}

class reRollMutation<T> implements MutationMethod<T>{
	public reRollMutation(OptAlgorithm<T, ?> alg) {
		super();
		this.alg = alg;
	}

	OptAlgorithm<T, ?> alg;

	@SuppressWarnings("unchecked")
	@Override
	public <S extends OptSolution<T, S>> S mutatedCopy(S original) {
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
		return mutationChance;
	}

	public ElemType<T> elemType() {
		return elementType;
	}

	@Override
	public T mutateElem(T elem) {
		return elemType().randomElm();
	}


	@Override
	public ChangeSize changeSize() {
		return changeSize;
	}

}