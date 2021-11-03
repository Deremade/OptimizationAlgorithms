package Test;

import java.time.Instant;
import java.util.Collection;

import Algorithms.AbstractAlgorithm;
import Algorithms.GeneticAlgorithm;
import Algorithms.NumericAlgorithm;
import Algorithms.SelectionMethod;
import Solution.Problem;
import staticMethods.SolutionMatcher;

/**
 * The Main class for testing
 * 
 * @author David
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		NumericAlgorithm<Double> na = new NumericAlgorithm<Double>(new CircleProblem<Double>(), 3);
		na.setAlgType(1);
		na.generateSolutions(10);
		na.setParticleSwarmOpt(1, 0.25, 0.25, 0.25, 0.25);
		na.setGeneticAlgorithm(1, SelectionMethod.topGroups(1, SolutionMatcher.bestWorst()), SolutionMatcher.randomMatching(2));
		
		
		System.out.println(na.displaySolutions());
			na.iteration();
		System.out.println(na.displaySolutions());
	}
}
