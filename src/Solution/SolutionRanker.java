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
	public static HashMap<OptimizationSolution<?>, Double> valuedsolutions(Collection<OptimizationSolution<?>> solutions) {
		HashMap<OptimizationSolution<?>, Double> valuedSolutions = new HashMap<OptimizationSolution<?>, Double>();
		for(OptimizationSolution<?> os : solutions) {
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
	public static LinkedList<Map.Entry<OptimizationSolution<?>, Double>> sortByValue(HashMap<OptimizationSolution<?>, Double> hm)
    {
        // Create a list from elements of HashMap
        LinkedList<Map.Entry<OptimizationSolution<?>, Double> > list =
               new LinkedList<Map.Entry<OptimizationSolution<?>, Double>>(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<OptimizationSolution<?>, Double> >() {
            public int compare(Map.Entry<OptimizationSolution<?>, Double> o1,
                               Map.Entry<OptimizationSolution<?>, Double> o2)
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
	public static LinkedList<Entry<OptimizationSolution<?>, Double>> sortByValue(Collection<OptimizationSolution<?>> solutions) {
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
	public static LinkedList<OptimizationSolution<?>> mostFit(LinkedList<Entry<OptimizationSolution<?>, Double>> linkedList, int size) {
		// TODO Auto-generated method stub
		if(linkedList.size() <= size) { //retunn all solutions if there is less then the number you want
			LinkedList<OptimizationSolution<?>> survivors = new LinkedList<OptimizationSolution<?>>();
			for(Entry<OptimizationSolution<?>, Double> entry : linkedList) {
					survivors.add(entry.getKey());
			}
			return survivors;
		}
		LinkedList<OptimizationSolution<?>> survivors = new LinkedList<OptimizationSolution<?>>();
		for(int i = linkedList.size()-1; i > Math.max(linkedList.size()-size, 0); i--) {
				survivors.add((OptimizationSolution<?>) linkedList.get(i).getKey());
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
	public static LinkedList<OptimizationSolution<?>> mostFit(Collection<OptimizationSolution<?>> solutions, int size) {
		return mostFit(sortByValue(solutions), size);
	}
	
	/**
	 * Returns the highest value() solution
	 * 
	 * @param solutions 
	 * candidates to be selected from
	 * @return highest value() solution
	 */
	public static OptimizationSolution<?> mostFitsolution(Collection<OptimizationSolution<?>> solutions) {
		return mostFit(sortByValue(solutions), 2).get(0);
	}
}
