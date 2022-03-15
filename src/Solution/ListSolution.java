package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Problems.Problem;

public interface ListSolution<T> extends OptSolution<T, ListSolution<T>> {

	/**
	 * @return A list representing the solution
	 */
	List<T> solutionList();

	Problem<T, ListSolution<T>> problem();
	
	/* (non-Javadoc)
	 * @see Solution.OptSolution#solutionDetails()
	 */
	public default String solutionDetails() {
		return problem().solutionDetails(this);
	}

	/* (non-Javadoc)
	 * @see Solution.OptSolution#isValid()
	 */
	public default boolean isValid() {
		if(madeInvalid()) return false;
		return problem().isValid(this);
	}

	boolean madeInvalid();

	/* (non-Javadoc)
	 * @see Solution.OptSolution#setElement(java.lang.Object, java.lang.String)
	 */
	public default void setElement(T elm, String placeCode) {
		solutionList().set(Integer.parseInt(placeCode), elm);
	}

	/* (non-Javadoc)
	 * @see Solution.OptSolution#placeElm(java.lang.Object, java.lang.String)
	 */
	public default void placeElm(T elm, String placeCode) {
		if(!emptyPlaceCodes().contains(placeCode))
			solutionList().add(elm);
		else
			solutionList().add(Integer.parseInt(placeCode), elm);
	}

	/* (non-Javadoc)
	 * @see Solution.OptSolution#findElm(java.lang.Object)
	 */
	public default String findElm(T elm) {
		return ""+solutionList().indexOf(elm);
	}

	/* (non-Javadoc)
	 * @see Solution.OptSolution#getElm(java.lang.String)
	 */
	public default T getElm(String placeCode) {
		return solutionList().get(Integer.parseInt(placeCode));
	}

	/* (non-Javadoc)
	 * @see Solution.OptSolution#placeCodes()
	 */
	public default Collection<String> placeCodes() {
		LinkedList<String> codes = new LinkedList<String>();
		for(int i = 0; i < solutionList().size(); i++)
			codes.add(""+i);
		return codes;
	}


	/* (non-Javadoc)
	 * @see Solution.OptSolution#emptyPlaceCodes()
	 */
	public default Collection<String> emptyPlaceCodes() {
		LinkedList<String> codes = new LinkedList<String>();
		codes.add(""+(solutionList().size()));
		return codes;
	}

	/* (non-Javadoc)
	 * @see Solution.OptSolution#removeItem(java.lang.String)
	 */
	public default boolean removeItem(String placeCode) {
		if(this.hasPlaceCode(placeCode)) {
			solutionList().remove(Integer.parseInt(placeCode));
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see Solution.OptSolution#betterThan(Solution.OptSolution)
	 */
	public default boolean betterThan(ListSolution<T> other) {
		return problem().compare(this, other) == 1;
	}

}
