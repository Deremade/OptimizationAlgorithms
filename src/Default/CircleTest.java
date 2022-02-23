package Default;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CircleTest implements Optamizer<Double> {
	Collection<OptSolutionCandidate<Double>> solutions = new LinkedList<OptSolutionCandidate<Double>>();
	OptamizingAlgorithm<Double> algorithm = genAlgorithm();
	Random r = new Random();
	Problem<Double, OptSolutionCandidate<Double>> problem = new CircleProblem<OptSolutionCandidate<Double>>();

	@Override
	public OptSolutionCandidate<Double> randomSolution() {
		// TODO Auto-generated method stub
		LinkedList<Double> solution = new LinkedList<Double>();
		for(int i = 0; i < 3; i++) {
			solution.add((r.nextDouble()*20)-10);
		}
		return newSolution(solution);
	}

	@Override
	public Collection<OptSolutionCandidate<Double>> solutions() {
		// TODO Auto-generated method stub
		return solutions;
	}

	@Override
	public OptSolutionCandidate<Double> genericChange(OptSolutionCandidate<Double> optSolutionCandidate) {
		// TODO Auto-generated method stub
		List<Double> newCode = new LinkedList<Double>();
		for(Double d : optSolutionCandidate.solutionCode()) {
			if(r.nextBoolean())
				if(r.nextBoolean())
					newCode.add(d+r.nextDouble());
				else
					newCode.add(d-r.nextDouble());
			else
				newCode.add(d);
		}
		return newSolution(newCode);
	}

	@Override
	public void iteration() {
		LinkedList<OptSolutionCandidate<Double>> newEntries = new LinkedList<OptSolutionCandidate<Double>>();
		System.out.println(" ");
		System.out.println(algorithm.printSolutions(solutions));
		for(OptSolutionCandidate<Double> solution : solutions) {
			newEntries.add(solution.change());
		}
		for(OptSolutionCandidate<Double> solution : newEntries) {
			solutions.add(solution);
		}
		solutions = algorithm.mostFit(solutions, 10);
		System.out.println(algorithm.printSolutions(solutions));
	}

	@Override
	public Problem<Double, OptSolutionCandidate<Double>> problem() {
		// TODO Auto-generated method stub
		return problem;
	}

	public CircleTest() {
		super();
		for(int i = 0; i < 10; i++) {
			solutions.add(randomSolution());
		}
	}

	@Override
	public List<Double> add(List<Double> sol1, List<Double> sol2, double scale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Double> difference(List<Double> sol1, List<Double> sol2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double distanceBetween(List<Double> sol1, List<Double> sol2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Double> origin() {
		// TODO Auto-generated method stub
		return null;
	}

}
