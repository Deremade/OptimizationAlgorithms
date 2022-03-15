package Default;

import java.util.LinkedList;
import java.util.Random;

import Algorithms.GeneticAlgorithm.CrossoverMethod;
import Algorithms.GeneticAlgorithm.Mutation.MutationMethod;
import Algorithms.GeneticAlgorithm.Mutation.Mutations;
import Problems.AllTrueTest.ATTGenetics;
import Problems.AllTrueTest.AllTrue;
import Problems.AllTrueTest.TestSolution;
import Solution.ListSolution;
import Solution.OptSolution;
import staticMethods.SolutionMethods;

public class mainTest {
	public static Random RNG = new Random();

	public static void main(String[] args) {
		ATTGenetics at = new ATTGenetics();
		at.generateSolutions(10);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
	}

}
