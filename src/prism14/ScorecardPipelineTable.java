package prism14;

public class ScorecardPipelineTable {

	private double valQ1, valQ2,
	valQ3, valQ4, valOverall;
	private String node, salesAgent, dataType;
	
	public ScorecardPipelineTable(String node, String salesAgent, String dataType, 
			double valQ1, double valQ2, double valQ3, double valQ4, double valOverall) {
		this.valQ1 = valQ1;
		this.valQ2 = valQ2;
		this.valQ3 = valQ3;
		this.valQ4 = valQ4;
		this.valOverall = valOverall;
		this.node = node;
		this.salesAgent = salesAgent;
		this.dataType = dataType;
	}
	
	public double getQ1() {
		return this.valQ1;
	}
	public double getQ2() {
		return this.valQ2;
	}
	public double getQ3() {
		return this.valQ3;
	}
	public double getQ4() {
		return this.valQ4;
	}
	public double getOverall() {
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
