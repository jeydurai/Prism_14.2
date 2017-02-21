package prism14;

public class ScorecardLeadsTable {

	private double valWaiting, valAccepted,
	valConverted, valOverall;
	private String node, salesAgent, dataType;
	
	public ScorecardLeadsTable(String node, String salesAgent, String dataType, 
			double valWaiting, double valAccepted, double valConverted, double valOverall) {
		this.valWaiting = valWaiting;
		this.valAccepted = valAccepted;
		this.valConverted = valConverted;
		this.valOverall = valOverall;
		this.node = node;
		this.salesAgent = salesAgent;
		this.dataType = dataType;
	}
	
	public double getLeadsWaiting() {
		return this.valWaiting;
	}
	public double getLeadsAccepted() {
		return this.valAccepted;
	}
	public double getLeadsConverted() {
		return this.valConverted;
	}
	public double getLeadsOverall() {
		return this.valOverall;
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
