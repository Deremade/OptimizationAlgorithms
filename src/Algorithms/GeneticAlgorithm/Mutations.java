package Algorithms.GeneticAlgorithm;

import Algorithms.OptAlgorithm;
import VectorOps.ElemType;

public class Mutations {

}

class reRollMutation<T, S extends Genome<T, S>> implements MutationMethod<T, S>{
	OptAlgorithm<T, S> alg;

	@Override
	public S mutatedCopy(S original) {
		// TODO Auto-generated method stub
		return alg.randomSolution();
	}
	
}

class randomMutations<T, S extends Genome<T, S>> implements MutationbyElem<T, S> {
	int minSize, maxSize;
	ElemType<T> elementType;
	double mutationChance;

	@Override
	public double mutationChance() {
		// TODO Auto-generated method stub
		return mutationChance;
	}

	@Override
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
	public boolean willGrow(S solution) {
		//If it can grow
		if(solution.placeCodes().size() < maxSize)
			// 1/size chance of growing
			return Math.random() < (1/solution.placeCodes().size());
		return false;
	}

	@Override
	public boolean willSrhink(S solution) {
		//If it can shrink
		if(solution.placeCodes().size() > minSize)
			//Max chance
			return Math.random() < 0.51-(1/solution.placeCodes().size());
		return false;
	}
	
}