package Default;
import java.util.Collection;
import java.util.List;

import Problems.Problem;
import Solution.OptSolution;


public interface Optamizer<T, S extends OptSolution<T, S>>  {

	S randomSolution();

	Collection<S> solutions();

	S genericChange(S optSolutionCandidate);

	void iteration();
	
	Problem<T, S> problem();
	
	S newSolution(List<S> placeCodes);
	
	default OptamizingAlgorithm<T, S> genAlgorithm() {
		return new OptamizingAlgorithm<T, S>(this);
	}

	List<T> add(List<T> sol1, List<T> sol2, double scale);

	List<T> difference(List<T> sol1, List<T> sol2);

	double distanceBetween(List<T> sol1, List<T> sol2);

	List<T> origin();
	
}

class OptamizingAlgorithm<T, S extends OptSolution<T, S>> implements OptAlgorithm<T, S> {
	Optamizer<T, S> optimizer;
	
	public OptamizingAlgorithm(Optamizer<T, S> optimizer) {
		super();
		this.optimizer = optimizer;
	}

	@Override
	public S randomSolution() {
		return optimizer.randomSolution();
	}

	@Override
	public Collection<S> solutions() {
		return optimizer.solutions();
	}

	@Override
	public void iteration() {
		optimizer.iteration();
	}
}
