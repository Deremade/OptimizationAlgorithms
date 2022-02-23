package Default;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public interface GeneticAlgorithm<T, S extends Genome<T, S>> extends OptAlgorithm<T, S> {
	
	OptAlgorithm<T, S> optAlgorithm();

	@Override
	default public S randomSolution() {
		// TODO Auto-generated method stub
		return optAlgorithm().randomSolution();
	}

	@Override
	default public Collection<S> solutions() {
		// TODO Auto-generated method stub
		return optAlgorithm().solutions();
	}

	@Override
	public default void iteration() {
		solutions().addAll(matingSeason());
		subjecttoSelection();
	}
	
	public int capacity();

	public default Collection<S> subjecttoSelection() {
		removeInvalid(solutions());
		return mostFit(solutions(), capacity());
	}
	
	public S findMate(S seeker);
	
	public S genChild(S parent1, S parent2);
	public S genChild(Collection<S> parents);
	
	/*
	 * Return all parents and children of said parents from mating
	 */
	public Collection<S> matingSeason();
	
	public default void removeInvalid() {
		removeInvalid(solutions());
	}
	
	public default LinkedList<S> mostFit() {
		return mostFit(solutions(), capacity());
	}
}
