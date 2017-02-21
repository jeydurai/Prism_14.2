package prism14;

public class DataBaseXxmScorecardLinearity {

	private String salesAgentName, salesLevel6, dataType;
	private double m1, m2, m3;
	
	public DataBaseXxmScorecardLinearity(String saName, String sl6, String dType, double m1, 
			double m2, double m3) {
		this.salesAgentName = saName;
		this.salesLevel6 = sl6;
		this.dataType = dType;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
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
	
	public double getValueM1() {
		return this.m1;
	}

	public double getValueM2() {
		return this.m2;
	}
	public double getValueM3() {
		return this.m3;
	}
}
