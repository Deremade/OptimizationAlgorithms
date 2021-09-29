package Test;

import java.util.LinkedList;

import Solution.NumericAlgorithm;
import Solution.OptimizationSolution;
import Solution.SelectionMethod;

/**
 * The Main class for testing
 * 
 * @author David
 */
public class Main {

	public static void main(String[] args) {
		NumericAlgorithm<Integer> na = new NumericAlgorithm<Integer>(1, new CircleProblem<Integer>(), 3, 0, 25, 1);
		
		na.generateSolutions(10);
		
		SelectionMethod sm = SelectionMethod.topSurvivors(5);
		System.out.println(na.displaySolutions());
		sm.subjectToSelection(na.solutions());
		System.out.println(na.displaySolutions());
		
	}

}
