package prism14;

public class DashboardXxmSalesTableValues {

	private double 	q1Rev, q2Rev, h1Rev, q3Rev, q4Rev, h2Rev,
	ytdRev;

	private double 	q1Threshold, q2Threshold, h1Threshold, q3Threshold, q4Threshold, h2Threshold,
	ytdThreshold;

	public DashboardXxmSalesTableValues(double q1Rev, double q2Rev, double h1Rev, double q3Rev, double q4Rev, double h2Rev,
			double ytdRev, double q1Threshold, double q2Threshold, double h1Threshold, double q3Threshold, 
			double q4Threshold, double h2Threshold,	double ytdThreshold) {

		this.q1Rev = q1Rev;
		this.q2Rev = q2Rev;
		this.h1Rev = h1Rev;
		this.q3Rev = q3Rev;
		this.q4Rev = q4Rev;
		this.h2Rev = h2Rev;
		this.ytdRev = ytdRev;

		this.q1Threshold = q1Threshold;
		this.q2Threshold = q2Threshold;
		this.h1Threshold = h1Threshold;
		this.q3Threshold = q3Threshold;
		this.q4Threshold = q4Threshold;
		this.h2Threshold = h2Threshold;
		this.ytdThreshold = ytdThreshold;
		
	}

	public double getRevQ1() {return this.q1Rev;}
	public double getRevQ2() {return this.q2Rev;}
	public double getRevH1() {return this.h1Rev;}
	public double getRevQ3() {return this.q3Rev;}
	public double getRevQ4() {return this.q4Rev;}
	public double getRevH2() {return this.h2Rev;}
	public double getRevYTD() {return this.ytdRev;}

	public double getThresholdQ1() {return this.q1Threshold;}
	public double getThresholdQ2() {return this.q2Threshold;}
	public double getThresholdH1() {return this.h1Threshold;}
	public double getThresholdQ3() {return this.q3Threshold;}
	public double getThresholdQ4() {return this.q4Threshold;}
	public double getThresholdH2() {return this.h2Threshold;}
	public double getThresholdYTD() {return this.ytdThreshold;}
	

	
}
