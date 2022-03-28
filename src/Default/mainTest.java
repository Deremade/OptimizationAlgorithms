package Default;

import Problems.AllTrueTest.ATTGenetics;

public class mainTest {

	public static void main(String[] args) {
		ATTGenetics at = new ATTGenetics();
		at.generateSolutions(10);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
		at.iteration(25);
		System.out.println(at.solutions());
	}

}
