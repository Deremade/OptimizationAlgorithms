package Algorithms;

import java.util.Collection;
import java.util.LinkedList;

import Solution.Mutation;
import Solution.Mutation.mutate;
import Solution.OptimizationSolution;
import Solution.VectorOperations;
import staticMethods.SolutionMatcher;
import staticMethods.SolutionMethods;

public abstract class Combat<E> implements CombatRing<E> {
	
}

interface CombatRing<E> {

	/**
	 * @param matches
	 * The collection of matches
	 * @return all the winning solutions
	 */
	public <S extends OptimizationSolution<E>> Collection<S> combat(Collection<Collection<S>> matches);

}

class ToDeath<E> extends Combat<E> {
	//Top winner takes all, other solutions deleted

	@Override
	public <S extends OptimizationSolution<E>> Collection<S> combat(
			 Collection<Collection<S>> matches) {
		// TODO Auto-generated method stub
		Collection<S> survivors = new LinkedList<S>();
		for( Collection<S> match : matches) 
			survivors.add(
					SolutionMethods.bestSolution(match));
		return survivors;
	}
	
}

class FiveStages<E> extends Combat<E> {
	//One-on one fights where the losers go through the 5 stages of grief to attempt to win again
	VectorOperations<E> vectOp;
	Mutation<E> mutation;
	mutate mutationMetod;
	boolean fighting;
	SolutionMatcher sm = SolutionMatcher.roundRobin();

	@Override
	public <S extends OptimizationSolution<E>> Collection<S> combat(Collection<Collection<S>> matches) {
		// TODO Auto-generated method stub
		Collection<S> winners = new LinkedList<S>();
		Collection<LinkedList<S>> fights;
		for(Collection<S> match : matches) {
			fights = sm.genMatches(match);
			for(LinkedList<S> fight : fights)
				winners.add(fight(fight.get(0), fight.get(1), match));
		}
		return winners;
	}
	
	/**
	 * @param sol1
	 * First solution
	 * @param sol2
	 * Second solution
	 * @param match
	 * collection of matches
	 * @return who wins the fight
	 */
	public <S extends OptimizationSolution<E>> S fight(S sol1, S sol2, Collection<S> match) {
		fighting = true;
		S winner = null;
		while(fighting) {
			S loser = null;
			if(sol1.betterThan(sol2)) {
				winner = sol1;
				loser = sol2;
			} else {
				winner = sol2;
				loser = sol1;
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
	public <S extends OptimizationSolution<E>> S anger(S loser, S winner) {
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
	public <S extends OptimizationSolution<E>> S bargening(S loser, S winner) {
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
	public <S extends OptimizationSolution<E>> S depression(S loser, S winner, mutate mutate) {
		S Mutant = (S) mutation.mutatedCopy(loser, mutate);
		if(Mutant.betterThan(winner))
			return Mutant;
		return null;
	}
	
	/**
	 * loser is removed from the match
	 * @param solutions
	 * @param loser
	 */
	public <S extends OptimizationSolution<E>> void acceptance(Collection<S> solutions, S loser) {
		solutions.remove(loser);
		fighting = false;
	}
	
}