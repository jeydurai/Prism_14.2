package prism14;

public class DataBaseAdminPartner {

	private String subSCMS, node, salesAgent, partner;
	
	public DataBaseAdminPartner(String sub, String n, String s, String p) {
		setSubSCMS(sub);
		setSalesLevel6(n);
		setSalesAgent(s);
		setPartner(p);
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
	
	public void setPartner(String s) {
		partner = s;
	}
	public String getPartner() {
		return partner;
	}
}
