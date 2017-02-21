package prism14;

public class DataBaseAdminCustomer {

	private String subSCMS, node, salesAgent, customer;
	
	public DataBaseAdminCustomer(String sub, String n, String s, String c) {
		setSubSCMS(sub);
		setSalesLevel6(n);
		setSalesAgent(s);
		setCustomer(c);
	}

	public void setSubSCMS(String s) {
		subSCMS = s;
	}
	public String getSubSCMS() {
		return subSCMS;
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
	
	public void setCustomer(String s) {
		customer = s;
	}
	public String getCustomer() {
		return customer;
	}
	
}
