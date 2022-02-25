package Problems;
import java.util.List;

import Solution.OptSolution;

public interface Problem<T, S extends OptSolution<T, S>> {
	
	public boolean isValid(S solution);
	
	public String solutionDetails(S solution);

	int compare(S sol1, S sol2);
}
