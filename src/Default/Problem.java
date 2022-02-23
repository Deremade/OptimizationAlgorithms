package Default;
import java.util.List;

public interface Problem<T, S extends OptSolution<T, S>> {
	
	public boolean isValid(S solution);
	
	public String solutionDetails(S solution);

	int compare(S sol1, S sol2);
}
