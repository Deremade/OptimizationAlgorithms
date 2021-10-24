package Solution;

public interface Bag<E> extends CountingSolution<E> {

	@Override
	public default double countElm(E element) {
		double count = 0.0;
		for(String elm : this.placeCodes()) {
			if(getElm(elm).equals(element)) count++;
		}
		return count;
	}
	
}
