package Default;
import java.util.Collection;
import java.util.List;


public interface Optamizer<T> {

	OptSolutionCandidate<T> randomSolution();

	Collection<OptSolutionCandidate<T>> solutions();

	OptSolutionCandidate<T> genericChange(OptSolutionCandidate<T> optSolutionCandidate);

	void iteration();
	
	Problem<T, OptSolutionCandidate<T>> problem();
	
	default OptSolutionCandidate<T> newSolution(List<T> solutionCode) {
		return new OptSolutionCandidate<T>(this, solutionCode, problem());
	}
	
	default OptamizingAlgorithm<T> genAlgorithm() {
		return new OptamizingAlgorithm<T>(this);
	}

	List<T> add(List<T> sol1, List<T> sol2, double scale);

	List<T> difference(List<T> sol1, List<T> sol2);

	double distanceBetween(List<T> sol1, List<T> sol2);

	List<T> origin();
	
}

class OptamizingAlgorithm<T> implements OptAlgorithm<T, OptSolutionCandidate<T>> {
	Optamizer<T> optimizer;
	
	public OptamizingAlgorithm(Optamizer<T> optimizer) {
		super();
		this.optimizer = optimizer;
	}

	@Override
	public OptSolutionCandidate<T> randomSolution() {
		return optimizer.randomSolution();
	}

	@Override
	public Collection<OptSolutionCandidate<T>> solutions() {
		return optimizer.solutions();
	}

	@Override
	public void iteration() {
		optimizer.iteration();
	}
}

class OptSolutionCandidate<T> implements OptSolution<T, OptSolutionCandidate<T>> {
	Optamizer<T> optimizer;
	List<T> solutionCode;
	Problem<T, OptSolutionCandidate<T>> problem;
	
	public OptSolutionCandidate(Optamizer<T> optimizer, List<T> solutionCode,
			Problem<T, OptSolutionCandidate<T>> problem) {
		super();
		this.optimizer = optimizer;
		this.solutionCode = solutionCode;
		this.problem = problem;
	}

	@Override
	public boolean isValid() {
		return problem.isValid(this);
	}

	@Override
	public String solutionDetails() {
		return problem.solutionDetails(this);
	}

	@Override
	public OptSolutionCandidate<T> change() {
		return optimizer.genericChange(this);
	}

	public List<T> solutionCode() {
		// TODO Auto-generated method stub
		return solutionCode;
	}

	@Override
	public int compareTo(OptSolutionCandidate<T> otherSol) {
		// TODO Auto-generated method stub
		return problem.compare(this, otherSol);
	}

	@Override
	public void makeInvalid() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OptSolutionCandidate<T> emptySolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElm(T elm, String placeCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeElm(T elm, String placeCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findElm(T elm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getElm(String placeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> placeCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPlaceCode(String placeCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<String> emptyPlaceCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeItem(String placeCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean betterThan(OptSolutionCandidate<T> other) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

class VectorSolutionCandidate<T> implements VectorOps<T, OptSolutionCandidate<T>> {
	OptSolutionCandidate<T> solution;
	
	public VectorSolutionCandidate(Optamizer<T> optimizer, List<T> solutionCode,
			Problem<T, OptSolutionCandidate<T>> problem) {
		super();
		solution = new OptSolutionCandidate<T>(optimizer, solutionCode, problem);
	}
	
	public VectorSolutionCandidate(OptSolutionCandidate<T> enterSolution) {
		super();
		this.solution = enterSolution;
	}

	@Override
	public OptSolutionCandidate<T> midpoint(OptSolutionCandidate<T> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptSolutionCandidate<T> difference(OptSolutionCandidate<T> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptSolutionCandidate<T> add(OptSolutionCandidate<T> sol1, OptSolutionCandidate<T> sol2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptSolutionCandidate<T> add(OptSolutionCandidate<T> sol1, double scale1, OptSolutionCandidate<T> sol2,
			double scale2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptSolutionCandidate<T> scale(OptSolutionCandidate<T> sol, double scale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptSolutionCandidate<T> origin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double distance(OptSolutionCandidate<T> sol1, OptSolutionCandidate<T> sol2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
