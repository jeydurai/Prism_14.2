package prism14;

public class ScorecardSCPTable {

	private double accountPenetration, rev, techs, bnet, blist, totalAccounts; 
	private String node, salesAgent, dataType;
	
	public ScorecardSCPTable(String node, String salesAgent, String dataType, 
			double accountPenetration, double rev, double techs, double bnet, double blist, double totalAccounts) {
		this.accountPenetration = accountPenetration;
		this.rev = rev;
		this.techs = techs;
		this.bnet = bnet;
		this.blist = blist;
		this.totalAccounts = totalAccounts;
		this.node = node;
		this.salesAgent = salesAgent;
		this.dataType = dataType;
	}
	
	public double getAccountPenetration() {
		return this.accountPenetration;
	}

	public double getRevenue() {
		return this.rev;
	}

	public double getTechnologyCount() {
		return this.techs;
	}
	public double getBookingNet() {
		return this.bnet;
	}
	public double getBookingList() {
		return this.blist;
	}
	public double getTotalAccounts() {
		return this.totalAccounts;
	}

	public String getNode() {
		return this.node;
	}
	
	public String getSalesAgent() {
		return this.salesAgent;
	}
	
	public String getDataType() {
		return this.dataType;
	}

}
