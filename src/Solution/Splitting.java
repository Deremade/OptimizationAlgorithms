package Solution;

import java.util.Collection;
import java.util.LinkedList;

public interface Splitting<E> {

	
	/**
	 * performs split() on 2 specific genes
	 * @param gene1
	 * @param gene2
	 * @return a result of splitting the difference
	 */
	public default E split(E gene1, E gene2) {
		LinkedList<E> list = new LinkedList<E>();
		list.add(gene1);
		list.add(gene2);
		return split(list);
	}
	
	/**
	 * Splits the difference between a set of elements
	 * @param genes
	 * the set of elements
	 * @return the element representing splitting the difference between the "genes"
	 */
	public E split(Collection<E> genes);
}
