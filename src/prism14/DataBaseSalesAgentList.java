package prism14;

public class DataBaseSalesAgentList {

	private String salesAgentName, salesLevel6, salesAgentType, executionUnit, region, userName;
	
	public DataBaseSalesAgentList(String salesAgentName, String salesLevel6, String salesAgentType, String executionUnit, String region, String userName) {
		
		setSalesAgentName(salesAgentName);
		setSalesLevel6(salesLevel6);
		setSalesAgentType(salesAgentType);
		setExecutionUnit(executionUnit);
		setRegion(region);
		setUserName(userName);
	}
	
	private void setSalesAgentName(String text) {
		this.salesAgentName = text;
	}

	public String getSalesAgentName() {
		return this.salesAgentName;
	}

	private void setSalesLevel6(String text) {
		this.salesLevel6 = text;
	}

	public String getSalesLevel6() {
		return this.salesLevel6;
	}
	
	private void setSalesAgentType(String text) {
		this.salesAgentType = text;
	}

	public String getSalesAgentType() {
		return this.salesAgentType;
	}

	private void setExecutionUnit(String text) {
		this.executionUnit = text;
	}

	public String getExecutionUnit() {
		return this.executionUnit;
	}

	private void setRegion(String text) {
		this.region = text;
	}

	public String getRegion() {
		return this.region;
	}

	private void setUserName(String text) {
		this.userName = text;
	}

	public String getUserName() {
		return this.userName;
	}

}
