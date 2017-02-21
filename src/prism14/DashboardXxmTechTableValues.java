package prism14;

public class DashboardXxmTechTableValues {

	private double 	rsRev, secRev, ucsRev, ucRev, dcsRev,
	videoRev, wLanRev;

	private double 	rsThreshold, secThreshold, ucsThreshold, ucThreshold, dcsThreshold,
	videoThreshold, wLanThreshold;
	
	public DashboardXxmTechTableValues(double rsRev, double secRev, 
			double ucsRev, double ucRev, double dcsRev, double videoRev, double wLanRev, 
			double rsThreshold, double secThreshold, double ucsThreshold, double ucThreshold, 
			double dcsThreshold, double videoThreshold, double wLanThreshold) {

		this.rsRev = rsRev;
		this.secRev = secRev;
		this.ucsRev = ucsRev;
		this.ucRev = ucRev;
		this.dcsRev = dcsRev;
		this.videoRev = videoRev;
		this.wLanRev = wLanRev;

		this.rsThreshold = rsThreshold;
		this.secThreshold = secThreshold;
		this.ucsThreshold = ucsThreshold;
		this.ucThreshold = ucThreshold;
		this.dcsThreshold = dcsThreshold;
		this.videoThreshold = videoThreshold;
		this.wLanThreshold = wLanThreshold;

	}

	public double getRevRS() {return this.rsRev;}
	public double getRevSec() {return this.secRev;}
	public double getRevUCS() {return this.ucsRev;}
	public double getRevUC() {return this.ucRev;}
	public double getRevDCS() {return this.dcsRev;}
	public double getRevVideo() {return this.videoRev;}
	public double getRevWLAN() {return this.wLanRev;}
	
	public double getThresholdRS() {return this.rsThreshold;}
	public double getThresholdSec() {return this.secThreshold;}
	public double getThresholdUCS() {return this.ucsThreshold;}
	public double getThresholdUC() {return this.ucThreshold;}
	public double getThresholdDCS() {return this.dcsThreshold;}
	public double getThresholdVideo() {return this.videoThreshold;}
	public double getThresholdWLAN() {return this.wLanThreshold;}

	
}
