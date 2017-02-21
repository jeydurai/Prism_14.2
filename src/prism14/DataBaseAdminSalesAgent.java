package prism14;

public class DataBaseAdminSalesAgent {

	private String region, node, salesAgent;
	
	public DataBaseAdminSalesAgent(String reg, String n, String s) {
		setRegion(reg);
		setSalesLevel6(n);
		setSalesAgent(s);
	}
	
	public void setRegion(String s) {
		region = s;
	}
	public String getRegion() {
		return region;
	}

	public void setSalesLevel6(String s) {
		node = s;
	}
	public String getSalesLevel6() {
		return node;
	}
	
	public void setSalesAgent(String s) {
		salesAgent = s;
	}
	
	public String getSalesAgent() {
		return salesAgent;
	}
}
