package Algorithms.ParticleSwarmOptimization;

public class SRsettings {
	public double bestScale, worstScale, distance;
	public int items;
	public boolean ranged;
	
	boolean entireSwarm = false;
	
	public SRsettings(double best, double worst) {
		entireSwarm = true;
		bestScale = best;
		worstScale = worst;
	}
	
	public SRsettings(double best, double worst, double range) {
		ranged = true;
		bestScale = best;
		worstScale = worst;
		distance = range;
	}
	
	public SRsettings(double best, double worst, int itemCount) {
		ranged = false;
		bestScale = best;
		worstScale = worst;
		items = itemCount;
	}
}
