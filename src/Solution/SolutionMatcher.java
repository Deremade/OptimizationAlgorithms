package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public abstract class SolutionMatcher implements matchingAlgorithm {
	public static Random r = new Random();
	
	public static randomMatching randomMatching(int perMatch) {
		return new randomMatching(perMatch);
	}
}

interface matchingAlgorithm {
	
	public <E> Collection<LinkedList<OptimizationSolution<E>>> genMatches(Collection<OptimizationSolution<E>> solutions);
}

class randomMatching extends SolutionMatcher{
	/**
	 * Randomly assigns everyone a match
	 * @param perMatch
	 * Number of solutions in a match-up
	 */
	public randomMatching(int perMatch) {
		super();
		this.perMatch = perMatch;
	}

	int perMatch;

	@Override
	public <E> Collection<LinkedList<OptimizationSolution<E>>> genMatches(Collection<OptimizationSolution<E>> solutions) {
		// TODO Auto-generated method stub
		LinkedList<OptimizationSolution<E>> indexedSolutions = new LinkedList<OptimizationSolution<E>>();
		for(OptimizationSolution<E> s : solutions) {
			indexedSolutions.add(s);
		}
		Collection<LinkedList<OptimizationSolution<E>>> matches = new LinkedList<LinkedList<OptimizationSolution<E>>>();
		while(indexedSolutions.size() >= perMatch) {
			LinkedList<OptimizationSolution<E>> match = new LinkedList<OptimizationSolution<E>>();
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