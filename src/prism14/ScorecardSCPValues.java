package prism14;

public class ScorecardSCPValues {

	private double accountPenetration, rev, techs, bnet, blist, totalAccounts;
	
	public ScorecardSCPValues(double accountPenetration, double rev, 
			double techs, double bnet, double blist, double totalAccounts) {
		this.accountPenetration = accountPenetration;
		this.rev = rev;
		this.techs = techs;
		this.bnet = bnet;
		this.blist = blist;
		this.totalAccounts = totalAccounts;
	}
	
	public double getActualAccountPenetration() {
		return this.accountPenetration;
	}

	public double getActualRevenue() {
		return this.rev;
	}

	public double getActualTechnologyCount() {
		return this.techs;
	}
	public double getActualBookingNet() {
		return this.bnet;
	}
	public double getActualBookingList() {
		return this.blist;
	}
	public double getThresholdTotalAccounts() {
		return this.totalAccounts;
	}

}
