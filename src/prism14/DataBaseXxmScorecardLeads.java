package prism14;

public class DataBaseXxmScorecardLeads {

	private String salesAgentName, salesLevel6, dataType;
	private double waitingValue, acceptedValue, convertedValue, 
	totalValue;
	
	public DataBaseXxmScorecardLeads(String saName, String sl6, String dType, 
			double waitingValue, double acceptedValue, double convertedValue, 
			double totalValue) {
		this.salesAgentName = saName;
		this.salesLevel6 = sl6;
		this.dataType = dType;
		this.waitingValue = waitingValue;
		this.acceptedValue = acceptedValue;
		this.convertedValue = convertedValue;
		this.totalValue = totalValue;
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
	
	public double getLeadsWaiting() {
		return this.waitingValue;
	}

	public double getLeadsAccepted() {
		return this.acceptedValue;
	}

	public double getLeadsConverted() {
		return this.convertedValue;
	}

	public double getLeadsTotal() {
		return this.totalValue;
	}
	
}
