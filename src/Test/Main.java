package Test;

import java.time.Instant;
import java.util.Collection;

import Algorithms.AbstractAlgorithm;
import Algorithms.GeneticAlgorithm;
import Algorithms.NumericAlgorithm;
import Solution.Problem;
import Solution.SelectionMethod;
import staticMethods.SolutionMatcher;

/**
 * The Main class for testing
 * 
 * @author David
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		NumericAlgorithm<Integer> na = new NumericAlgorithm<Integer>(1, new CircleProblem<Integer>(), 3);
		na.setAlgType(2);
		na.generateSolutions(10);
		na.setParticleSwarmOpt(1, 0.25, 0.25, 0.25, 0.25);
		
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
				
	}
}
