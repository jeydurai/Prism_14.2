package prism14;

public class ScorecardATPenetrationTable {

	private double salesAT, salesNonAT; 
	private String node, salesAgent, dataType;
	
	public ScorecardATPenetrationTable(String node, String salesAgent, String dataType, 
			double salesAT, double salesNonAT) {
		this.salesAT = salesAT;
		this.salesNonAT = salesNonAT;
		this.node = node;
		this.salesAgent = salesAgent;
		this.dataType = dataType;
	}
	
	public double getSalesAT() {
		return this.salesAT;
	}

	public double getSalesNonAT() {
		return this.salesNonAT;
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
