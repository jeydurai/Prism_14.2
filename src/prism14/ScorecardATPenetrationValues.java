package prism14;

public class ScorecardATPenetrationValues {

	private double actualSalesAT, actualSalesNonAT, 
	thresholdSalesAT, thresholdSalesNonAT;
	
	public ScorecardATPenetrationValues(double actualSalesAT, double actualSalesNonAT, 
			double thresholdSalesAT, double thresholdSalesNonAT) {
		this.actualSalesAT = actualSalesAT;
		this.actualSalesNonAT = actualSalesNonAT;
		this.thresholdSalesAT = thresholdSalesAT;
		this.thresholdSalesNonAT = thresholdSalesNonAT;
	}
	
	public double getActualSalesAT() {
		return this.actualSalesAT;
	}

	public double getActualSalesNonAT() {
		return this.actualSalesNonAT;
	}

	public double getThresholdSalesAT() {
		return this.thresholdSalesAT;
	}

	public double getThresholdSalesNonAT() {
		return this.thresholdSalesNonAT;
	}

}
