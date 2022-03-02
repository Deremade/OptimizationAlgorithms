package Problems;

import Solution.OptSolution;
import staticMethods.NumbersComparitor;

public interface ValueProblem<T, S extends OptSolution<T, S>> extends Problem<T, S> {

	Number value(S solution);
	
	/**
	 * @param sol1
	 * @param sol2
	 * @return 
	 * 1 = sol1 is better
	 * -1 = sol2 is better
	 */
	default int compare(S sol1, S sol2) {
		if(NumbersComparitor.moreThan(value(sol1), value(sol2)))
			return 1;
		else
			return -1;
	}
}
