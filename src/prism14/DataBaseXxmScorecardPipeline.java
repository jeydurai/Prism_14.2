package prism14;

public class DataBaseXxmScorecardPipeline {

	private String salesAgentName, salesLevel6, dataType;
	private double q1, q2, q3, q4, all;
	
	public DataBaseXxmScorecardPipeline(String saName, String sl6, String dType, double q1, 
			double q2, double q3, double q4, double all) {
		this.salesAgentName = saName;
		this.salesLevel6 = sl6;
		this.dataType = dType;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
	}
	
	
	public String getSalesAgentName() {
		return this.salesAgentName;
	}
	
	public String getNode() {
		return this.salesLevel6;
	}
	
	public String getDataType() {
		return this.dataType;
	}
	
	public double getValueQ1() {
		return this.q1;
	}

	public double getValueQ2() {
		return this.q2;
	}
	public double getValueQ3() {
		return this.q3;
	}

	public double getValueQ4() {
		return this.q4;
	}

	public double getValueOverall() {
		return this.all;
	}
}
