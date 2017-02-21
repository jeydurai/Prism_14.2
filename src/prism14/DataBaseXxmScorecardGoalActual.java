package prism14;

public class DataBaseXxmScorecardGoalActual {

	private String salesAgentName, salesLevel6, dataType;
	private double value;
	
	public DataBaseXxmScorecardGoalActual(String saName, String sl6, String dType, double val) {
		this.salesAgentName = saName;
		this.salesLevel6 = sl6;
		this.dataType = dType;
		this.value = val;
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
	
	public double getValue() {
		return this.value;
	}
}
