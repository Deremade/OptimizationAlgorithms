package Solution;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author David
 * 
 * An interface for defining Vector operations between solutions
 *
 * @param <T>
 * @param <S>
 */
public interface VectorOps<T, S extends OptSolution<T, S>>{
	
	/**
	 * The solution that is in between two other solutions
	 * @param sol1
	 * @param sol2
	 * @return The "midpoint" between two solutions
	 */
	public default S midpoint(S sol1, S sol2) {
		return add(sol1, 0.5, sol2, 0.5);
	}
	
	/**
	 * @param sol1
	 * @param sol2
	 * @return A solution defined by the difference between the two solutions
	 */
	public S difference(S sol1, S sol2);
	
	/**
	 * @param sol1
	 * @param sol2
	 * @return The result of summing the solutions
	 */
	public S add(S sol1, S sol2);
	
	/**
	 * @param sol1
	 * @param scale1
	 * @param sol2
	 * @param scale2
	 * @return A solution defined by scaling and then adding two solutions
	 */
	public default S add(S sol1, double scale1, S sol2, double scale2) {
		return add(scale(sol1, scale1), scale(sol2, scale2));
	}
	
	/**
	 * @param sol
	 * @param scale
	 * @return "Scales" a solution
	 */
	public S scale(S sol, double scale);
	
	/**
	 * @return the "origin" solution
	 */
	public S origin();
	
	/**
	 * @param sol
	 * @return the distance between this solution and the "origin"
	 */
	public default double length(S sol) {
		return distance(origin(), sol);
	}
	
	/**
	 * @param sol1
	 * @param sol2
	 * @return The "distance" between the two solutions
	 */
	public default double distance(S sol1, S sol2) {
		return length(difference(sol1, sol2));
	}
	
	/**
	 * @param s
	 * Solutions to be considered
	 * @param focus
	 * @return the closest solution to the "focus"
	 */
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

	/**
	 * @param solList
	 * @param focus
	 * @param num
	 * @return A collection of the [num] closest solutions from solList to focus
	 */
	default Collection<S> closestItems(Collection<S> solList, S focus, int num) {
		//Make a copy list so the original is unmodified
		Collection<S> copy = new LinkedList<S>();
		copy.addAll(solList);
		//Create a return list
		Collection<S> returnList = new LinkedList<S>();
		while(returnList.size() < num) { //While looking for items
			S closest = closestItem(copy, focus); //find the closest item
			returnList.add(closest); //add them to the return list
			copy.remove(closest); //remove from the copy
		}
		return returnList;
	}

	/**
	 * @param solList
	 * @param focus
	 * @param distance
	 * @return A collection of solutions that were within "distance" of the focus
	 */
	default Collection<S> nearbySolutions(Collection<S> solList, S focus, double distance) {
		//list of Solutions to be returned
		Collection<S> returnList = new LinkedList<S>();
		for(S solution : solList) //For every solution
			if(distance(solution, focus) <= distance) //if within distance
				returnList.add(solution); //add to list
		return returnList;
	}
}
