package Test;

import java.util.LinkedList;

import Solution.OptimizationSolution;
import Solution.Problem;

public class PackingProblem implements Problem<Boolean> {
	public static LinkedList<String> item;
	public static LinkedList<Integer> values;
	public static LinkedList<Double> weights;
	public static double carryingCap = 3000;
	
	public static void fillItems() {
		item = new LinkedList<String>();
		values = new LinkedList<Integer>();
		weights = new LinkedList<Double>();
		
		addItem("laptop", 500, 2200);
		addItem("phone", 500, 200);
		addItem("tissues", 15, 80);
		addItem("mug", 60, 350);
		addItem("notepad", 100, 500);
		addItem("headphone", 150, 160);
		addItem("cap", 100, 70);
		addItem("socks", 60, 350);
		addItem("Book 1", 60, 500);
		addItem("Book 2", 50, 500);
		addItem("Book 3", 40, 500);
	}
	
	public static void addItem(String name, Integer value, double weight) {
		item.add(name);
		values.add(value);
		weights.add(weight);
	}

	public double value(OptimizationSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		double value = 0;
		for(int i = 0; i < values.size(); i++)
			if(solution.getElm(""+i)) value += values.get(i);
		return value;
	}

	@Override
	public String solutionDetails(OptimizationSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		String s = "[";
		for(int i = 0; i < item.size(); i++)
			if(solution.getElm(""+i)) s += item.get(i);
		return s.substring(0, s.length()-1)+" ] "+Math.round(solution.value());
	}

	@Override
	public boolean isValid(OptimizationSolution<Boolean> solution) {
		// TODO Auto-generated method stub
		int value = 0;
		for(int i = 0; i < weights.size(); i++)
			if(solution.getElm(""+i)) value += weights.get(i);
		return value <= carryingCap;
	}

	@Override
	public double changeSizeChance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends OptimizationSolution<Boolean>> boolean compare(S sol0, S sol1) {
		// TODO Auto-generated method stub
		return value(sol0) > value(sol1);
	}

}
