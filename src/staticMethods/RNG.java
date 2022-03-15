package staticMethods;

import java.util.Random;

public class RNG extends Random {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static RNG RNG = new RNG();

	/**
	 * @return Random selection of True or False
	 */
	public static boolean randBoolean() {
		return RNG.nextBoolean();
	}
	
	/**
	 * @param bound
	 * @return random int from 0-bound
	 */
	public static int randInt(int bound) {
		return RNG.nextInt(bound);
	}
	
	/**
	 * @return random decimal from 0 to 1
	 */
	public static double randDouble() {
		return RNG.nextDouble();
	}
}
