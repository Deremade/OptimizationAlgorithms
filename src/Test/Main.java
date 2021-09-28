package Test;

import Solution.NumericAlgorithm;

/**
 * The Main class for testing
 * 
 * @author David
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumericAlgorithm<Double> na = new NumericAlgorithm<Double>(1, new LocalMaxTest<Double>(), 3, -5.0, 5.0, 1.0);
		na.fillSolutions(5);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
		na.iterations(15);
		System.out.println(na.displaySolutions());
	}

}
