package staticMethods;

import java.util.Collection;

public class CollectionMethods {
	
	public static <T> T random(Collection<T> coll) {
	    int num = (int) (Math.random() * coll.size());
	    for(T t: coll) if (--num < 0) return t;
	    throw new AssertionError();
	}
	
	public static double average(Collection<Double> coll) {
		double avrg = 0;
		for(Double d : coll)
			avrg += d/coll.size();
		return avrg;
	}
	
	public static double sum(Collection<Double> coll) {
		double avrg = 0;
		for(Double d : coll)
			avrg += d;
		return avrg;
	}
}
