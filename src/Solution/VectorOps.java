package Solution;
import java.util.Collection;
import java.util.LinkedList;

public interface VectorOps<T, S extends OptSolution<T, S>>{
	
	public S midpoint(S sol1, S sol2);
	
	public S difference(S sol1, S sol2);
	
	public S add(S sol1, S sol2);
	
	public S add(S sol1, double scale1, S sol2, double scale2);
	
	public S scale(S sol, double scale);
	
	public S origin();
	
	public default double length(S sol) {
		return distance(origin(), sol);
	}
	
	public default double distance(S sol1, S sol2) {
		return length(difference(sol1, sol2));
	}
	
	public default S closestItem(Collection<S> s, S focus) {
		double min = 0;
		S closestSol = null;
		for(S sol : s) {
			if(closestSol == null) {
				min = distance(sol, focus);
				closestSol = sol;
			}
			if(distance(sol, focus) < min && distance(sol, focus) != 0) {
				min = distance(sol, focus);
				closestSol = sol;
			}
		}
		return closestSol;
	}

	default Collection<S> closestItems(Collection<S> solList, S focus, int num) {
		Collection<S> copy = new LinkedList<S>();
		copy.addAll(solList);
		Collection<S> returnList = new LinkedList<S>();
		while(returnList.size() < num) {
			S closest = closestItem(copy, focus);
			returnList.add(closest);
			copy.remove(closest);
		}
		return returnList;
	}

	default Collection<S> nearbySolutions(Collection<S> solList, S focus, double distance) {
		Collection<S> returnList = new LinkedList<S>();
		for(S solution : solList)
			if(distance(solution, focus) <= distance)
				returnList.add(solution);
		return returnList;
	}
}
