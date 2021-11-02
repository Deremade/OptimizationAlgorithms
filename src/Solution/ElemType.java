package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import staticMethods.NumbersComparitor;

public interface ElemType<E> extends Splitting<E>{
	
	
	/** Returns an element that represents the difference between two elements
	 * @param elm1
	 * @param elm2
	 * @return the difference between the elms
	 */
	public E difference(E elm1, E elm2);

	/**
	 * @param elm
	 * The element being scaled
	 * @param scale
	 * The value it is scaled by
	 * @return The resulting scaled Element
	 */
	public E scale(E elm, double scale);
	
	/**
	 * @param elements
	 * The elements being added
	 * @return resulting sum
	 */
	public E addElms(Collection<E> elements);
	
	/**
	 * @param elm1
	 * @param elm2
	 * @return the sum of the elements
	 */
	public default E addElms(E elm1, E elm2) {
		LinkedList<E> ll = new LinkedList<E>();
		ll.add(elm1);
		ll.add(elm2);
		return  addElms(ll);
	}
	
	/**
	 * @return a random element
	 */
	public E randomSelect();
	
	/**
	 * The elm goes up the "cycle" 1 step
	 * 
	 * @param elm
	 * The element being Modified
	 * @return the modified element
	 */
	public E singleStep(E elm);
	
	/**
	 * The elm goes in some direction of the "cycle" 1 step
	 * 
	 * @param elm
	 * The elm being modified
	 * @return The modified Gene
	 */
	public E upCycle(E elm);

}
