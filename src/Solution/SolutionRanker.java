package Solution;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Ranks solutions by their value
 * 
 * @author David
 *
 */
public class SolutionRanker {
	
	/**
	 * Maps solutions to their value()
	 * 
	 * @param 
	 * solutions A set of solutions to be mapped
	 * @return valuedSolutions,
	 * all solutions mapped to their value()
	 */
	public static <T> HashMap<OptimizationSolution<T>, Double> valuedsolutions(Collection<OptimizationSolution<T>> solutions) {
		HashMap<OptimizationSolution<T>, Double> valuedSolutions = new HashMap<OptimizationSolution<T>, Double>();
		for(OptimizationSolution<T> os : solutions) {
			valuedSolutions.put(os, os.value());
		}
		return valuedSolutions;
	}
	
	/**
	 * Ranks Solutions to their value()
	 * 
	 * @param hm 
	 * a HashMap with solutions mapped to their value
	 * @return list of all solutions ranked by their value()
	 */
	public static <T> LinkedList<Map.Entry<OptimizationSolution<T>, Double>> sortByValue(HashMap<OptimizationSolution<T>, Double> hm)
    {
        // Create a list from elements of HashMap
        LinkedList<Map.Entry<OptimizationSolution<T>, Double> > list =
               new LinkedList<Map.Entry<OptimizationSolution<T>, Double>>(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<OptimizationSolution<T>, Double> >() {
            public int compare(Map.Entry<OptimizationSolution<T>, Double> o1,
                               Map.Entry<OptimizationSolution<T>, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        return list;
    }

	
	/**
	 * Sorts a collection of solutions by their value()
	 * 
	 * @param solutions 
	 * a list of solutions to be mapped to their value()
	 * @return a HashMap of solutions mapped to their value()
	 */
	public static <T> LinkedList<Entry<OptimizationSolution<T>, Double>> sortByValue(Collection<OptimizationSolution<T>> solutions) {
		return sortByValue(valuedsolutions(solutions));
	}
	
	/**
	 * Prunes solutions down to a few top solutions
	 * 
	 * @param linkedList 
	 * a list of solutions Mapped to their value(), ranked in order
	 * @param size 
	 * how many survivors there will be
	 * @return survivors, the top {size} amoung the solutions
	 */
	public static <T> LinkedList<OptimizationSolution<T>> mostFit(LinkedList<Entry<OptimizationSolution<T>, Double>> linkedList, int size) {
		// TODO Auto-generated method stub
		if(linkedList.size() <= size) { //retunn all solutions if there is less then the number you want
			LinkedList<OptimizationSolution<T>> survivors = new LinkedList<OptimizationSolution<T>>();
			for(Entry<OptimizationSolution<T>, Double> entry : linkedList) {
					survivors.add(entry.getKey());
			}
			return survivors;
		}
		LinkedList<OptimizationSolution<T>> survivors = new LinkedList<OptimizationSolution<T>>();
		for(int i = linkedList.size()-1; i > Math.max(linkedList.size()-size, 0); i--) {
				survivors.add((OptimizationSolution<T>) linkedList.get(i).getKey());
		}
		return survivors;
	}
	
	/**
	 * Prunes solutions down to a few top solutions
	 * 
	 * @param solutions
	 * a list of solutions to be pruned
	 * @param size 
	 * how many survivors there will be
	 * @return survivors, the top {size} amoung the solutions
	 */
	public static <T> LinkedList<OptimizationSolution<T>> mostFit(Collection<OptimizationSolution<T>> solutions, int size) {
		return mostFit(sortByValue(solutions), size);
	}
	
	/**
	 * Returns the highest value() solution
	 * 
	 * @param solutions 
	 * candidates to be selected from
	 * @return highest value() solution
	 */
	public static <T> OptimizationSolution<T> mostFitsolution(Collection<OptimizationSolution<T>> solutions) {
		return mostFit(sortByValue(solutions), 2).get(0);
	}
}
