package Algorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Solution.Mutation;
import Solution.OptimizationSolution;
import Solution.VectorOperations;
import Solution.Mutation.mutate;
import staticMethods.SolutionMatcher;
import staticMethods.SolutionMethods;

public abstract class SelectionMethod implements Selector{

	public static SelectionMethod topSurvivors(int capacity) {
		return new TopSurvivors(capacity);
	}
	
	public static TopGoruped topGroups(int winners, SolutionMatcher solutionMatcher) {
		return new TopGoruped(winners, solutionMatcher);
	}
}

interface Selector {
	
	/**
	 * Determines which members of the population survive the selection method
	 * 
	 * @param population
	 * The sample of all solutions being considered
	 */
	public <E, S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population);
	
}

class TopSurvivors extends SelectionMethod {
	int capacity;
	
	/**
	 * @param capacity
	 */
	public TopSurvivors(int capacity) {
		super();
		this.capacity = capacity+1;
	}

	public <E, S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population) {
		Collection<S> survivors = mostFit(population, capacity);
		population.clear();
		population.addAll(survivors);
	}

	private <E, S extends OptimizationSolution<E>> Collection<S> mostFit(Collection<S> population, int capacity) {
		// TODO Auto-generated method stub
		List<S> solutions = SolutionMethods.sort(population);
		while(solutions.size() > capacity)
			solutions.remove(0);
		return solutions;
	}

}

class TopGoruped extends SelectionMethod {
	int winners;
	SolutionMatcher matcher;
	
	/**
	 * @param winners
	 * @param solutionMatcher
	 */
	public TopGoruped(int winners, SolutionMatcher solutionMatcher) {
		super();
		this.winners = winners;
		this.matcher = solutionMatcher;
	}

	public <E, S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population) {
		Collection<S> survivors = new LinkedList<S>();
		for(Collection<S> group : matcher.genMatches(population)) 
			survivors.addAll(mostFit(group, winners));
		population.clear();
		population.addAll(survivors);
	}

	private <E, S extends OptimizationSolution<E>> Collection<S> mostFit(Collection<S> population, int capacity) {
		// TODO Auto-generated method stub
		List<S> solutions = SolutionMethods.sort(population);
		while(solutions.size() > capacity)
			solutions.remove(0);
		return solutions;
	}

}

class FiveStages extends SelectionMethod {
	//One-on one fights where the losers go through the 5 stages of grief to attempt to win again
	VectorOperations<?> vectOp;
	Mutation<?> mutation;
	mutate mutationMetod;
	boolean fighting;
	SolutionMatcher breakIntoTwo = SolutionMatcher.roundRobin();
	SolutionMatcher solMatch;

	
	public <E, S extends OptimizationSolution<E>> Collection<S> combat(Collection<LinkedList<S>> collection) {
		// TODO Auto-generated method stub
		Collection<S> winners = new LinkedList<S>();
		Collection<LinkedList<S>> fights;
		for(Collection<S> match : collection) {
			fights = breakIntoTwo.genMatches(match);
			for(LinkedList<S> fight : fights)
				winners.add(fight(fight.get(0), fight.get(1), match));
		}
		return winners;
	}
	
	@SuppressWarnings("unchecked")
	public <E> VectorOperations<E> vector() {
		return (VectorOperations<E>) vectOp;
	}
	
	@SuppressWarnings("unchecked")
	public <E> Mutation<E> mutation() {
		return (Mutation<E>) mutation;
	}
	
	/**
	 * @param s
	 * First solution
	 * @param s2
	 * Second solution
	 * @param match
	 * collection of matches
	 * @return who wins the fight
	 */
	public <E, S extends OptimizationSolution<E>> S fight(S s, S s2, Collection<S> match) {
		fighting = true;
		S winner = null;
		while(fighting) {
			S loser = null;
			if(s.betterThan(s2)) {
				winner = s;
				loser = s2;
			} else {
				winner = s2;
				loser = s;
			}
			if(anger(loser, winner) != null)
				winner = anger(loser, winner);
			else if (bargening(loser, winner) != null)
				winner = bargening(loser, winner);
			else {
				S depression = depression(loser, winner, mutationMetod);
				if(depression != null)
					winner = depression;
				else acceptance(match, loser);
			}
		}
		return winner;
	}
	
	/**
	 * loser radicalized(scales by 1,5) to see if it wins
	 * (goes at it with full force)
	 * @param loser
	 * @param winner
	 * @return null if they still lose, and the scaled solution if they win
	 */
	@SuppressWarnings("unchecked")
	
	public <E, S extends OptimizationSolution<E>> S anger(S loser, S winner) {
		VectorOperations<E> vectOp = vector();
		if(vectOp.scaleSolution(loser, 1.5).betterThan(winner))
			return (S) vectOp.scaleSolution(loser, 1.5);
		return null;
	}
	
	/** 
	 * loser combines with the winner (bargains) to attempt to win
	 * (both solutions scaled by 0.5 and added
	 * @param loser
	 * @param winner
	 * @return null if they still lose, and the scaled solution if they win
	 */
	public <E, S extends OptimizationSolution<E>> S bargening(S loser, S winner) {
		VectorOperations<E> vectOp = vector();
		Collection<S> solutions = new LinkedList<S>();
		solutions.add(loser);
		solutions.add(winner);
		S bargin = vectOp.addSolutions(solutions);
		if(bargin.betterThan(winner))
			return bargin;
		return null;
	}
	
	/**
	 * Loser will mutate (randomly change) to see if it can help thme win
	 * @param loser
	 * @param winner
	 * @param mutate
	 * @return null if they still lose, and the scaled solution if they win
	 */
	@SuppressWarnings("unchecked")
	public <E, S extends OptimizationSolution<E>> S depression(S loser, S winner, mutate mutate) {
		VectorOperations<E> vectOp = vector();
		Mutation<E> mutationMethod = mutation();
		S Mutant = (S) mutationMethod.mutatedCopy(loser, mutate);
		if(Mutant.betterThan(winner))
			return Mutant;
		return null;
	}
	
	/**
	 * loser is removed from the match
	 * @param solutions
	 * @param loser
	 */
	public <E, S extends OptimizationSolution<E>> void acceptance(Collection<S> solutions, S loser) {
		solutions.remove(loser);
		fighting = false;
	}

	@Override
	public <E, S extends OptimizationSolution<E>> void subjectToSelection(Collection<S> population) {
		// TODO Auto-generated method stub
		Collection<S> winners = new LinkedList<S>();
		Collection<LinkedList<S>> fights = null;
		for(Collection<S> match : solMatch.genMatches(population)) {
			fights = breakIntoTwo.genMatches(match);
			for(LinkedList<S> fight : fights)
				winners.add(fight(fight.get(0), fight.get(1), match));
			fights.clear();
		}
		population.clear();
		population.addAll(winners);
	}
	
}