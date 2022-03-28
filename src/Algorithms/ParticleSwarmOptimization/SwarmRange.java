package Algorithms.ParticleSwarmOptimization;

import java.util.Collection;

import Solution.OptSolution;
import VectorOps.VectorOps;
import staticMethods.SolutionMethods;

/**
 * @author David
 *
 *Handles Movement of Swarm Particles, based on neighbor particles within a range
 * @param <T>
 * @param <S>
 */
public class SwarmRange<T, S extends OptSolution<T, S>> {
	VectorOps<T, S> vector;
	SRsettings settings;

	/**
	 * @param focus
	 * The solution being focused on
	 * @param solList
	 * List of solutions to be considered
	 * @return Collection of items within range based on settings
	 */
	Collection<S> withinRange(S focus, Collection<S> solList) {
		if(settings.entireSwarm) return solList;
		if(settings.ranged)
			return vector.nearbySolutions(solList, focus, settings.distance);
		else
			return vector.closestItems(solList, focus, settings.items);
	}
	
	/**
	 * @param candidates
	 * @return The best item in collection
	 */
	private S findBestNeighbor(Collection<S> candidates) {
		return SolutionMethods.bestSolution(candidates);
	}
	
	/**
	 * @param candidates
	 * @return Worst item in the collection
	 */
	private S findWorstNeighbor(Collection<S> candidates) {
		return SolutionMethods.worstSolution(candidates);
	}
	
	/**
	 * @param focus
	 * @param solList
	 * @return A solution That represents movement by adding the best and worst solutions by scale
	 */
	S calcMovement(S focus, Collection<S> solList) {
		Collection<S> closeBy = withinRange(focus, solList);
		return vector.add(
				findBestNeighbor(closeBy), settings.bestScale, 
				findWorstNeighbor(closeBy), settings.worstScale);
	}

	public SwarmRange(VectorOps<T, S> vector, SRsettings settings) {
		super();
		this.vector = vector;
		this.settings = settings;
	}
}
