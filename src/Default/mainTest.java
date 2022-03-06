package Default;

import java.util.Random;

import Algorithms.GeneticAlgorithm.MutationMethod;
import Algorithms.GeneticAlgorithm.Mutations;
import Problems.AllTrueTest.AllTrue;
import staticMethods.SolutionMethods;

public class mainTest {
	public static Random RNG = new Random();

	public static void main(String[] args) {
		AllTrue at = new AllTrue();
		at.generateSolutions(10);
		System.out.println(at.solutions());
		System.out.println(at.sortedSolutions(at.solutions()));
		System.out.println(
		at.compare(at.sortedSolutions(at.solutions()).get(0), at.sortedSolutions(at.solutions()).get(5))
		);
		System.out.println(
				SolutionMethods.bestSolution(at.solutions())
				);
		System.out.println(
				SolutionMethods.worstSolution(at.solutions())
				);
		MutationMethod<Boolean> mutate = Mutations.reRollMutation(at);
		System.out.println(
				mutate.mutatedCopy(
						SolutionMethods.worstSolution(at.solutions())
						)
				);
	}

}
