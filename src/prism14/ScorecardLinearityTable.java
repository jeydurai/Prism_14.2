package prism14;

public class ScorecardLinearityTable {

	private double valM1, valM2,
	valM3;
	private String node, salesAgent, dataType;
	
	public ScorecardLinearityTable(String node, String salesAgent, String dataType, 
			double valM1, double valM2, double valM3) {
		this.valM1 = valM1;
		this.valM2 = valM2;
		this.valM3 = valM3;
		this.node = node;
		this.salesAgent = salesAgent;
		this.dataType = dataType;
	}
	
	public double getLinearityM1() {
		return this.valM1;
	}
	public double getLinearityM2() {
		return this.valM2;
	}
	public double getLinearityM3() {
		return this.valM3;
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
