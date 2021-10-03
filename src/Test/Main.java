package Test;

import java.time.Instant;
import java.util.Collection;

import Algorithms.AbstractAlgorithm;
import Algorithms.GeneticAlgorithm;
import Algorithms.NumericAlgorithm;
import Algorithms.SwarmParticle;
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
		na.setGeneticAlgorithm(1, SelectionMethod.topSurvivors(10), SolutionMatcher.randomMatching(3));
		na.setBounds(-10, 10, 1);
		na.setAlgType(1);
		na.generateSolutions(10);
		
		System.out.println(na.displaySolutions());
		na.iteration();
		System.out.println(na.displaySolutions());
				
	}
}
