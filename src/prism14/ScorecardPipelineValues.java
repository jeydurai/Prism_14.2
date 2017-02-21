package prism14;

public class ScorecardPipelineValues {

	private double actualQ1, actualQ2,
	actualQ3, actualQ4, actualOverall;
	
	private double thresholdQ1, thresholdQ2,
	thresholdQ3, thresholdQ4, thresholdOverall;

	public ScorecardPipelineValues(double actualQ1, double actualQ2,
			double actualQ3, double actualQ4, double actualOverall, double thresholdQ1, 
			double thresholdQ2, double thresholdQ3, double thresholdQ4, double thresholdOverall) {
		this.actualQ1 = actualQ1;
		this.actualQ2 = actualQ2;
		this.actualQ3 = actualQ3;
		this.actualQ4 = actualQ4;
		this.actualOverall = actualOverall;
		this.thresholdQ1 = thresholdQ1;
		this.thresholdQ2 = thresholdQ2;
		this.thresholdQ3 = thresholdQ3;
		this.thresholdQ4 = thresholdQ4;
		this.thresholdOverall = thresholdOverall;
	}
	
	public double getActualQ1() {
		return this.actualQ1;
	}
	public double getActualQ2() {
		return this.actualQ2;
	}
	public double getActualQ3() {
		return this.actualQ3;
	}
	public double getActualQ4() {
		return this.actualQ4;
	}
	public double getActualOverall() {
		return this.actualOverall;
	}

	public double getThresholdQ1() {
		return this.thresholdQ1;
	}
	public double getThresholdQ2() {
		return this.thresholdQ2;
	}
	public double getThresholdQ3() {
		return this.thresholdQ3;
	}
	public double getThresholdQ4() {
		return this.thresholdQ4;
	}
	public double getThresholdOverall() {
		return this.thresholdOverall;
	}

}
