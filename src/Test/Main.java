package Test;

import java.time.Instant;

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
		for(int i = 0; i <= 4; i++)
			for(int j = 0; j <= 4; j++) {
				System.out.println("CircleTest");
				runTest(i, j, new CircleProblem<Integer>());
				System.out.println("LocalMax");
				runTest(i, j, new LocalMaxTest<Integer>());
			}
				
	}
	
	/**
	 * Print the results of a test
	 * @param mutationType
	 * @param crossoverType
	 */
	public static <N extends Number> void runTest(int mutationType, int crossoverType, Problem<N> problem) {
		NumericAlgorithm<N> optimizer = new NumericAlgorithm<N>(mutationType, problem, 10);
		optimizer.generateSolutions(10);
		optimizer.setGeneticAlgorithm(crossoverType, SelectionMethod.topSurvivors(10), SolutionMatcher.randomMatching(3));
		optimizer.setAlgType(1);
		long[] time = new long[51];
		int[] iterations = new int[51];
		for(int i = 0; i < 51; i++) {
			long[] data = getData(optimizer);
			iterations[i] = (int) data[0];
			time[i] = data[1];
		}
		double[] timeData = avrgVar(time);
		double[] iterationData = avrgVar(iterations);
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println(optimizer.mutationMethod() + " "+ GeneticAlgorithm.getCrossoverType(crossoverType));
		System.out.println("Iterations :    Average : "+Math.round(iterationData[0]) + " Variance : "+Math.round(iterationData[1]));
		System.out.println("Time (ms)  :    Average : "+Math.round(timeData[0]) + " Variance : "+Math.round(timeData[1]));
		System.out.println("----------------------------------------------------------------------------------------------");
		
	}
	
	public static double[] avrgVar(int[] data) {
		double average = 0;
		for(int i = 0; i < data.length; i++) {
			average += (data[i] - average)/(i+1);
		}
		double variance = 0;
		for(int i = 0; i < data.length; i++) {
			variance += (Math.abs(data[i]-average) - variance)/(i+1);
		}
		return new double[] {average, variance};
	}
	
	public static double[] avrgVar(long[] data) {
		double average = 0;
		for(int i = 0; i < data.length; i++) {
			average += (data[i] - average)/(i+1);
		}
		double variance = 0;
		for(int i = 0; i < data.length; i++) {
			variance += (Math.abs(data[i]-average) - variance)/(i+1);
		}
		return new double[] {average, variance};
	}
	
	public static <T> long[] getData(AbstractAlgorithm<T> optimizer) {
		Instant start = Instant.now();
		int iterations = optimizer.interationsToScore(-0.01);
		Instant end = Instant.now();
		int time = (int) ((end.getNano()*0.001) - (start.getNano()*0.001));
		return new long[] {iterations, time};
	}
}
