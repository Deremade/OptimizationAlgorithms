package Algorithms.GeneticAlgorithm;

import java.util.Collection;
import java.util.LinkedList;

import Solution.OptSolution;
import VectorOps.ElemType;

public interface MutationMethod<T> {
	
	<S extends OptSolution<T, S>> S mutatedCopy(S original);
	
	default <S extends OptSolution<T, S>> 
	Collection<S> mutateAll(Collection<S> genePool){
		//Mutant list
		Collection<S> mutants = new LinkedList<S>();
		for(S genome : genePool) //For every item
			mutants.add(mutatedCopy(genome)); //add a mutant copy
		return mutants;
	}
}
