package staticMethods;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Default.OptAlgorithm;
import Solution.OptSolution;
import Solution.VectorOps;

public abstract class SolutionMatcher implements matchingAlgorithm {
	public static Random r = new Random();
	
	public static RandomMatching randomMatching(int perMatch) {
		return new RandomMatching(perMatch);
	}
	
	public static BestWorst bestWorst() {
		return new BestWorst();
	}
	
	public static <T, S extends OptSolution<T, S>> WithinDist withinDist(VectorOps<T, S> vector, double distance) {
		return new WithinDist(vector, distance);
	}
	
	public static <T, S extends OptSolution<T, S>> Closest closest(VectorOps<T, S> vector, int groupSize) {
		return new Closest(vector, groupSize);
	}
	
	public static <E> RoundRobin roundRobin() {
		return new RoundRobin();
	}
}

interface matchingAlgorithm {
	
	public <T, S extends OptSolution<T, S>> Collection<LinkedList<S>> genMatches(Collection<S> solutions);
}

/**
 * @author David
 *
	 * Randomly assigns everyone a match
	 * @param perMatch
	 * Number of solutions in a match-up
 */
class RandomMatching extends SolutionMatcher{
	/**
	 * Randomly assigns everyone a match
	 * @param perMatch
	 * Number of solutions in a match-up
	 */
	public RandomMatching(int perMatch) {
		super();
		this.perMatch = perMatch;
	}

	int perMatch;

	@Override
	public <T, S extends OptSolution<T, S>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		// list of indexed solutions
		LinkedList<S> indexedSolutions = new LinkedList<S>();
		for(S s : solutions) { //place all solutions intot he list to index them
			indexedSolutions.add(s);
		}
		//Collection of Matches
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		while(indexedSolutions.size() >= perMatch) {//while matches need to be made
			LinkedList<S> match = new LinkedList<S>(); //create match
			while(match.size() < perMatch) {//while match needs to be filled
				//find random index
				int index = r.nextInt(Math.min(perMatch, (indexedSolutions.size())));
				//add to match
				match.add(indexedSolutions.get(index));
				//remove from indexed solutions
				indexedSolutions.remove(index);
			}
			matches.add(match);
		}
		return matches;
	}
}

/**
 * @author David
 * Matches the worst solutions with the best solutions and second best with second worst etc.
 */
class BestWorst extends SolutionMatcher{
	/**
	 * Matches the worst solutions with the best solutions and second best with second worst etc.
	 */
	public BestWorst() {
		super();
	}

	@Override
	public <T, S extends OptSolution<T, S>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		//Create a collection of matches
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		//sort the solutions
		List<S> solutionList = SolutionMethods.sort(solutions);
		int numOfSol = solutionList.size();
		//loop through solutions
		for(int i =0; i < numOfSol/2; i++) {
			LinkedList<S> match = new LinkedList<S>();
			match.add(solutionList.get(i)); //first in list
			match.add(solutionList.get(numOfSol-i-1));//last in list
			matches.add(match);
		}
		return matches;
	}
}

class WithinDist  extends SolutionMatcher{
	VectorOps<?,?> vector;
	double distance;
	
	/**
	 * Groups members by finding those within a certain distance from each other
	 * @param vector
	 * the Vector Operations - determines how "distance" is defined
	 * @param distance
	 * The distance from each other that would qualify them as a group
	 */
	public <T, S extends OptSolution<T, S>>  WithinDist(VectorOps<T,S>  vector, double distance) {
		super();
		this.vector = vector;
		this.distance = distance;
	}
	
	@SuppressWarnings("unchecked")
	public <T, S extends OptSolution<T, S>> VectorOps<T,S> vector() {
		return (VectorOps<T,S>) vector;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T, S extends OptSolution<T, S>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		//Create collection of matches
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		//copy list of solutions
		Collection<S> solList = new LinkedList<S>();
		solList.addAll(solutions);
		//While the copy list is not empty
		while(!solList.isEmpty()) {
			//Find random focus
			S focus = CollectionMethods.random(solList);
			@SuppressWarnings("unchecked")
			Collection<S> match = ((VectorOps<T,S>) vector()).nearbySolutions(solList, focus, distance);
			//turn the match into a linked list
			LinkedList<S> ll = new LinkedList<S>();
			for(S col : match)
				ll.add(col);
			//add the list to Matches
			matches.add(ll);
			//remove all items from the solList
			System.out.println(solList.size());
			solList.removeAll(ll);
		}
		return matches;
	}

}

/**
 * @author David
 * 
  * Groups elements by finding the closest elements to eachother
	 * @param vector
	 * the Vector Operations - determines how "distance" is defined
	 * @param size
	 * The size of the groups
 *
 */
class Closest extends SolutionMatcher {
	VectorOps<?,?> vector;
	int num;
	
	/**
	 * Groups elements by finding the closest elements to eachother
	 * @param vector
	 * the Vector Operations - determines how "distance" is defined
	 * @param size
	 * The size of the groups
	 */
	public <T, S extends OptSolution<T, S>> Closest(VectorOps<T,S> vector, int size) {
		super();
		this.vector = vector;
		this.num = size-1;
	}
	

	@SuppressWarnings("unchecked")
	public <T, S extends OptSolution<T, S>> VectorOps<T,S> vector() {
		return (VectorOps<T,S>) vector;
	}
	
	@Override
	public <T, S extends OptSolution<T, S>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		// Create a copy list
		Collection<S> solList = new LinkedList<S>();
		solList.addAll(solutions);
		//Collection of matches
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		
		while(!solList.isEmpty()) { //while the copy list is not empty
			S focus = CollectionMethods.random(solList); //find a random focus element
			@SuppressWarnings("unchecked")
			//Find closest items
			Collection<S> match = ((VectorOps<T,S>) vector()).closestItems(solList, focus, num);
			match.add(focus);
			//turn the match into a linked list
			LinkedList<S> ll = new LinkedList<S>();
			for(S col : match)
				ll.add(col);
			//add the list to Matches
			matches.add(ll);
			//remove all items from the solList
			solList.removeAll(ll);
		}
		return matches;
	}
	
}

/**
 * @author David
 * every solution is matched with every other solution
 */
class RoundRobin extends SolutionMatcher {

	@Override
	public <T, S extends OptSolution<T, S>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		// TODO Auto-generated method stub
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		for(S sol : solutions)
			for(S sol1 : solutions) {
				LinkedList<S> match = new LinkedList<S>();
				match.add(sol);
				match.add(sol1);
				matches.add(match);
			}
		return matches;
	}
	
}