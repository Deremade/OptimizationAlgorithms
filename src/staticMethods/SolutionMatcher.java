package staticMethods;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Solution.OptimizationSolution;
import Solution.VectorOperations;

public abstract class SolutionMatcher implements matchingAlgorithm {
	public static Random r = new Random();
	
	public static RandomMatching randomMatching(int perMatch) {
		return new RandomMatching(perMatch);
	}
	
	public static BestWorst bestWorst() {
		return new BestWorst();
	}
	
	public static <E> WithinDist withinDist(VectorOperations<E> vector, double distance) {
		return new WithinDist(vector, distance);
	}
	
	public static <E> Closest closest(VectorOperations<E> vector, int groupSize) {
		return new Closest(vector, groupSize);
	}
}

interface matchingAlgorithm {
	
	public <E, S extends OptimizationSolution<E>> Collection<LinkedList<S>> genMatches(Collection<S> solutions);
}

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
	public <E, S extends OptimizationSolution<E>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		// TODO Auto-generated method stub
		LinkedList<S> indexedSolutions = new LinkedList<S>();
		for(S s : solutions) {
			indexedSolutions.add(s);
		}
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		while(indexedSolutions.size() >= perMatch) {
			LinkedList<S> match = new LinkedList<S>();
			while(match.size() < perMatch) {
				int index = r.nextInt(Math.min(perMatch, (indexedSolutions.size())));
				match.add(indexedSolutions.get(index));
				indexedSolutions.remove(index);
			}
			matches.add(match);
		}
		return matches;
	}
}

class BestWorst extends SolutionMatcher{
	/**
	 * Matches the worst solutions with the best solutions and second best wiht second worst etc.
	 */
	public BestWorst() {
		super();
	}

	@Override
	public <E, S extends OptimizationSolution<E>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		// TODO Auto-generated method stub
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		List<S> solutionList = SolutionMethods.sort(solutions);
		int numOfSol = solutionList.size();
		for(int i =0; i < numOfSol/2; i++) {
			LinkedList<S> match = new LinkedList<S>();
			match.add(solutionList.get(i));
			match.add(solutionList.get(numOfSol-i-1));
			matches.add(match);
		}
		return matches;
	}
}

class WithinDist extends SolutionMatcher{
	VectorOperations<?> vector;
	double distance;
	
	/**
	 * Groups members by finding those within a certain distance from each other
	 * @param vector
	 * the Vector Operations - determines how "distance" is defined
	 * @param distance
	 * The distance from each other that would qualify them as a group
	 */
	public <E> WithinDist(VectorOperations<E> vector, double distance) {
		super();
		this.vector = vector;
		this.distance = distance;
	}
	
	@SuppressWarnings("unchecked")
	public <E> VectorOperations<E> vector() {
		return (VectorOperations<E>) vector;
	}

	@SuppressWarnings("hiding")
	@Override
	public <E, S extends OptimizationSolution<E>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		// TODO Auto-generated method stub
		Collection<S> solList = new LinkedList<S>();
		solList.addAll(solutions);
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		VectorOperations<E> vectOp = vector();
				
		while(!solList.isEmpty()) {
			S focus = CollectionMethods.random(solList);
			Collection<S> match = vectOp.nearbySolutions(solList, focus, distance);
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

class Closest extends SolutionMatcher {
	VectorOperations<?> vector;
	int num;
	
	/**
	 * Groups elements by finding the closest elements to eachother
	 * @param vector
	 * the Vector Operations - determines how "distance" is defined
	 * @param size
	 * The size of the groups
	 */
	public <E> Closest(VectorOperations<E> vector, int size) {
		super();
		this.vector = vector;
		this.num = size-1;
	}
	

	@SuppressWarnings("unchecked")
	public <E> VectorOperations<E> vector() {
		return (VectorOperations<E>) vector;
	}
	
	@Override
	public <E, S extends OptimizationSolution<E>> Collection<LinkedList<S>> genMatches(Collection<S> solutions) {
		// TODO Auto-generated method stub
		Collection<S> solList = new LinkedList<S>();
		solList.addAll(solutions);
		Collection<LinkedList<S>> matches = new LinkedList<LinkedList<S>>();
		VectorOperations<E> vectOp = vector();
		
		while(!solList.isEmpty()) {
			S focus = CollectionMethods.random(solList);
			Collection<S> match = vectOp.closestItems(solList, focus, num);
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