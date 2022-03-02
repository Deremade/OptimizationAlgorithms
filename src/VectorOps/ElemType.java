package VectorOps;

public interface ElemType<T> {

	T add(T a, T b);
	
	T difference(T a, T b);
	
	T scale(T elem, double scale);
	
	T randomElm();
}
