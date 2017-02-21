package prism14;

public class DataBaseAdminNode {

	private String region, node;
	
	public DataBaseAdminNode(String reg, String nod) {
		
		setRegion(reg);
		setSalesLevel6(nod);
	}
	
	public void setRegion(String reg) {
		region = reg;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setSalesLevel6(String nod) {
		node = nod;
	}
	
	public String getSalesLevel6() {
		return node;
	}
}
