package Default;

import java.util.Random;
import Problems.AllTrueTest.ATTGenetics;
import Problems.AllTrueTest.AllTrue;
import VectorOps.BooleanVector;
import staticMethods.SolutionMethods;

public class mainTest {
	public static Random RNG = new Random();

	public static void main(String[] args) {
		ATTGenetics at = new ATTGenetics();
		at.generateSolutions(10);
		BooleanVector bv = new BooleanVector(new AllTrue());
		System.out.println(
				SolutionMethods.bestSolution(at.solutions())
				);
		System.out.println(
				SolutionMethods.worstSolution(at.solutions())
				);
		System.out.println(
				bv.difference(
						SolutionMethods.bestSolution(at.solutions()), 
						SolutionMethods.worstSolution(at.solutions()))
				);
		System.out.println(
				bv.add(
						SolutionMethods.bestSolution(at.solutions()), 
						SolutionMethods.worstSolution(at.solutions()))
				);
		at.iteration(10);
		System.out.println(
				SolutionMethods.bestSolution(at.solutions())
				);
		System.out.println(
				SolutionMethods.worstSolution(at.solutions())
				);
		System.out.println(
				bv.difference(
						SolutionMethods.bestSolution(at.solutions()), 
						SolutionMethods.worstSolution(at.solutions()))
				);
		System.out.println(
				bv.add(
						SolutionMethods.bestSolution(at.solutions()), 
						SolutionMethods.worstSolution(at.solutions()))
				);
	}

}
