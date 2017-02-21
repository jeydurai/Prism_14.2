package prism14;

public class ScorecardGrowthValues {

	private double actual, threshold;
	
	public ScorecardGrowthValues(double actual, double threshold) {
		this.actual = actual;
		this.threshold = threshold;
	}
	
	public double getActual() {
		return this.actual;
	}

	public double getThreshold() {
		return this.threshold;
	}

}
