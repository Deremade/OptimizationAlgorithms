package staticMethods;

import java.util.Collection;

/**
 * @author David
 *
 *Methods that can be used on Collections
 */
public class CollectionMethods {
	
	/**
	 * @param coll
	 * @return a random item from the collection
	 */
	public static <T> T random(Collection<T> coll) {
	    int num = (int) (Math.random() * coll.size());
	    for(T t: coll) if (--num < 0) return t;
	    throw new AssertionError();
	}
	
	/**
	 * @param coll
	 * @return The average number from a list of numbers
	 */
	public static Number average(Collection<Number> coll) {
		Number sum = sum(coll);
		return NumbersComparitor.divideNumbers(sum, coll.size());
	}
	
	/**
	 * @param coll
	 * @return the sum of all numbers
	 */
	public static Number sum(Collection<Number> coll) {
		Number sum = 0;
		for(Number n : coll)
			sum = NumbersComparitor.addNumbers(n, sum);
		return sum;
	}
}
