package prism14;

public class ScorecardLinearityValues {

	private double actualM1, actualM2,
	actualM3;
	
	private double thresholdM1, thresholdM2,
	thresholdM3;

	public ScorecardLinearityValues(double actualM1, double actualM2,
			double actualM3, double thresholdM1, 
			double thresholdM2, double thresholdM3) {
		this.actualM1 = actualM1;
		this.actualM2 = actualM2;
		this.actualM3 = actualM3;
		this.thresholdM1 = thresholdM1;
		this.thresholdM2 = thresholdM2;
		this.thresholdM3 = thresholdM3;
	}
	
	public double getLeadsM1Actual() {
		return this.actualM1;
	}
	public double getLeadsM2Actual() {
		return this.actualM2;
	}
	public double getLeadsM3Actual() {
		return this.actualM3;
	}

	public double getLeadsM1Threshold() {
		return this.thresholdM1;
	}
	public double getLeadsM2Threshold() {
		return this.thresholdM2;
	}
	public double getLeadsM3Threshold() {
		return this.thresholdM3;
	}

}
