package prism14;

public class ScorecardLeadsValues {

	private double actualWaiting, actualAccepted,
	actualConverted, actualOverall;
	
	private double thresholdWaiting, thresholdAccepted,
	thresholdConverted, thresholdOverall;

	public ScorecardLeadsValues(double actualWaiting, double actualAccepted,
			double actualConverted, double actualOverall, double thresholdWaiting, 
			double thresholdAccepted, double thresholdConverted, double thresholdOverall) {
		this.actualWaiting = actualWaiting;
		this.actualAccepted = actualAccepted;
		this.actualConverted = actualConverted;
		this.actualOverall = actualOverall;
		this.thresholdWaiting = thresholdWaiting;
		this.thresholdAccepted = thresholdAccepted;
		this.thresholdConverted = thresholdConverted;
		this.thresholdOverall = thresholdOverall;
	}
	
	public double getLeadsWaitingActual() {
		return this.actualWaiting;
	}
	public double getLeadsAcceptedActual() {
		return this.actualAccepted;
	}
	public double getLeadsConvertedActual() {
		return this.actualConverted;
	}
	public double getLeadsOverallActual() {
		return this.actualOverall;
	}

	public double getLeadsWaitingThreshold() {
		return this.thresholdWaiting;
	}
	public double getLeadsAcceptedThreshold() {
		return this.thresholdAccepted;
	}
	public double getLeadsConvertedThreshold() {
		return this.thresholdConverted;
	}
	public double getLeadsOverallThreshold() {
		return this.thresholdOverall;
	}

}
