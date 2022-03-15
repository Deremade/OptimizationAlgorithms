package Problems.AllTrueTest;

import java.util.Collection;
import java.util.LinkedList;

import Algorithms.OptAlgorithm;
import Algorithms.GeneticAlgorithm.CrossoverMethod;
import Algorithms.GeneticAlgorithm.GeneticAlgorithm;
import Algorithms.GeneticAlgorithm.Mutation.ChangeSize;
import Algorithms.GeneticAlgorithm.Mutation.MutationMethod;
import Algorithms.GeneticAlgorithm.Mutation.Mutations;
import Default.SelectionMethod;
import Solution.ListSolution;
import staticMethods.SolutionMatcher;

public class ATTGenetics implements GeneticAlgorithm<Boolean, ListSolution<Boolean>> {
	AllTrue at = new AllTrue();
	SelectionMethod<Boolean, ListSolution<Boolean>> sm = SelectionMethod.survivalOfThefittest();
	MutationMethod<Boolean> mutate = Mutations.randomMutations(new BoolElem(), ChangeSize.rubberBandbyInverse(10, 20), 0.2);
	CrossoverMethod cm = CrossoverMethod.crissCross();
	SolutionMatcher solMatch = SolutionMatcher.randomMatching(3);
	
	@Override
	public OptAlgorithm<Boolean, ListSolution<Boolean>> optAlgorithm() {
		// TODO Auto-generated method stub
		return at;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public SelectionMethod<Boolean, ListSolution<Boolean>> selectionMethod() {
		// TODO Auto-generated method stub
		return sm;
	}

	@Override
	public MutationMethod<Boolean> mutationMethod() {
		// TODO Auto-generated method stub
		return mutate;
	}

	@Override
	public Collection<ListSolution<Boolean>> matingSeason() {
		LinkedList<ListSolution<Boolean>> children = new LinkedList<ListSolution<Boolean>>();
		for(LinkedList<ListSolution<Boolean>> match : 
			solMatch.genMatches(solutions())) {
			children.add(genChild(match));
		}
		return children;
	}

	@Override
	public CrossoverMethod crossoverMethod() {
		// TODO Auto-generated method stub
		return cm;
	}

}
