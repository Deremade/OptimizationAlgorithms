package Default;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Solution.OptSolution;
import staticMethods.SolutionMethods;

public interface OptAlgorithm<T, S extends OptSolution<T, S>> {
	
	public S randomSolution();
	
	public Collection<S> solutions();
	
	public default void generateSolutions(Collection<S> solutions, int numOfsolutions){
		while(solutions.size() < numOfsolutions) {
			solutions.add(randomSolution());
		}
	}
	
	public default void removeInvalid(Collection<S> solutions) {
		for(S os : solutions) {
			if(!os.isValid())
				solutions.remove(os);
		}
	}
	
	public default LinkedList<Map.Entry<S, Double>> sortByValue(HashMap<S, Double> hm)
    {
        // Create a list from elements of HashMap
        LinkedList<Map.Entry<S, Double> > list =
               new LinkedList<Map.Entry<S, Double>>(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<S, Double> >() {
            public int compare(Map.Entry<S, Double> o1,
                               Map.Entry<S, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        return list;
    }
	
	public default List<S> sortedSolutions(Collection<S> solutions){
		return SolutionMethods.sort(solutions);
	}
	
	public default LinkedList<S> mostFit(LinkedList<Entry<S, Double>> rankedPopulation, int size) {
		// TODO Auto-generated method stub
		if(rankedPopulation.size() <= size) { //retunn all solutions if there is less then the number you want
			LinkedList<S> survivors = new LinkedList<S>();
			for(Entry<S, Double> entry : rankedPopulation) {
					survivors.add(entry.getKey());
			}
			return survivors;
		}
		LinkedList<S> survivors = new LinkedList<S>();
		for(int i = rankedPopulation.size()-1; i > Math.max(rankedPopulation.size()-size, 0); i--) {
				survivors.add((S) rankedPopulation.get(i).getKey());
		}
		return survivors;
	}
	
	public default LinkedList<S> mostFit(Collection<S> solutions, int size) {
		return mostFit(sortedSolutions(solutions), size);
	}
	
	public default S mostFitsolution(Collection<S> solutions) {
		return mostFit(sortedSolutions(solutions), 2).get(0);
	}
	
	public default String printSolutions(Collection<S> solutions) {
		String s = "<";
		for(S os : solutions) {
			s += os.solutionDetails()+",";
		}
		return s.substring(0, s.length()-1);
	}
	
	public void iteration();
	
	public default void iteration(int cycles) {
		for(int i = 0; i < cycles; i++)
			iteration();
	}
}
