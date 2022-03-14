package Default;

import java.util.LinkedList;
import java.util.Random;

import Algorithms.GeneticAlgorithm.CrossoverMethod;
import Algorithms.GeneticAlgorithm.MutationMethod;
import Algorithms.GeneticAlgorithm.Mutations;
import Problems.AllTrueTest.AllTrue;
import Problems.AllTrueTest.TestSolution;
import Solution.ListSolution;
import Solution.OptSolution;
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
		System.out.println("--------");
		CrossoverMethod cross = CrossoverMethod.crissCross();
		LinkedList<ListSolution<Boolean>> list = new LinkedList<ListSolution<Boolean>>();
		list.add((TestSolution) SolutionMethods.worstSolution(at.solutions()));
		list.add((TestSolution) SolutionMethods.bestSolution(at.solutions()));
		System.out.println(at.mostFit(at.solutions(), 3));
		System.out.println(cross.crossover(at.mostFit(at.solutions(), 3)));
		System.out.println(cross.crossover(at.mostFit(at.solutions(), 3)));
		
	}

}
