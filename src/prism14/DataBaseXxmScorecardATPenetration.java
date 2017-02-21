package prism14;

public class DataBaseXxmScorecardATPenetration {

	private String salesAgentName, salesLevel6, dataType;
	private double atValue, nonATValue;
	
	public DataBaseXxmScorecardATPenetration(String saName, String sl6, String dType, 
			double atValue, double nonATValue) {
		this.salesAgentName = saName;
		this.salesLevel6 = sl6;
		this.dataType = dType;
		this.atValue = atValue;
		this.nonATValue = nonATValue;
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
	
	public double getSalesAT() {
		return this.atValue;
	}

	public double getSalesNonAT() {
		return this.nonATValue;
	}

}
