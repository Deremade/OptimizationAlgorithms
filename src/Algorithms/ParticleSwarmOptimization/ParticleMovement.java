package Algorithms.ParticleSwarmOptimization;

import java.util.Collection;
import java.util.LinkedList;

import Solution.OptSolution;
import VectorOps.VectorOps;

public class ParticleMovement<T, S extends OptSolution<T, S>> {
	Collection<SwarmRange<T, S>> swarmRanges;
	VectorOps<T, S> vector;
	Collection<S> solList;
	
	S totalMovement(S focus) {
		//Create Movement
		S movement = focus.clone();
		//Add up all Vectors
		for(SwarmRange<T, S> range : swarmRanges) {
			S difference = vector.difference(focus, range.calcMovement(focus, solList));
			movement = vector.add(movement, difference);
		}
		//Return sum of Vectors
		return movement;
	}
	
	Collection<S> moveAll(Collection<S> solList){
		//New Solutions
		Collection<S> newSolList = new LinkedList<S>();
		//For every solution passed into the function
		for(S focus : solList)
			//Find their "movement"
			newSolList.add(totalMovement(focus));
		return newSolList;
	}
}
