package prism14;

public class ScorecardGrowthTable {

	private double val;
	private String node, salesAgent, dataType;
	
	public ScorecardGrowthTable(double val, String node, String salesAgent, String dataType) {
		this.val = val;
		this.node = node;
		this.salesAgent = salesAgent;
		this.dataType = dataType;
	}
	
	public double getValue() {
		return this.val;
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
