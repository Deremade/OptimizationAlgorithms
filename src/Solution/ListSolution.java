package Solution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Problems.Problem;

public interface ListSolution<T> extends OptSolution<T, ListSolution<T>> {

	List<T> solutionList();
	
	Problem<T, ListSolution<T>> problem();
	
	public default String solutionDetails() {
		// TODO Auto-generated method stub
		return problem().solutionDetails(this);
	}

	public default boolean isValid() {
		if(madeInvalid()) return false;
		return problem().isValid(this);
	}

	boolean madeInvalid();

	public default void setElement(T elm, String placeCode) {
		// TODO Auto-generated method stub
		solutionList().set(Integer.parseInt(placeCode), elm);
	}

	public default void placeElm(T elm, String placeCode) {
		// TODO Auto-generated method stub
		solutionList().add(Integer.parseInt(placeCode), elm);
	}

	public default String findElm(T elm) {
		// TODO Auto-generated method stub
		return ""+solutionList().indexOf(elm);
	}

	public default T getElm(String placeCode) {
		// TODO Auto-generated method stub
		return solutionList().get(Integer.parseInt(placeCode));
	}

	public default Collection<String> placeCodes() {
		LinkedList<String> codes = new LinkedList<String>();
		for(int i = 0; i < solutionList().size(); i++)
			codes.add(""+i);
		return codes;
	}


	public default Collection<String> emptyPlaceCodes() {
		LinkedList<String> codes = new LinkedList<String>();
		codes.add(""+(solutionList().size()+1));
		return codes;
	}

	public default boolean removeItem(String placeCode) {
		// TODO Auto-generated method stub
		if(this.hasPlaceCode(placeCode)) {
			solutionList().remove(Integer.parseInt(placeCode));
			return true;
		}
		return false;
	}

	public default boolean betterThan(ListSolution<T> other) {
		// TODO Auto-generated method stub
		return problem().compare(this, other) == 1;
	}

}
