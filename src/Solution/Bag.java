package Solution;

public interface Bag<E> extends CountingSolution<E> {

	@Override
	public default double countElm(E element) {
		double count = 0.0;
		for(E elm : this) {
			if(elm.equals(element)) count++;
		}
		return count;
	}
	
}
