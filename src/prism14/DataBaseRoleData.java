package prism14;

public class DataBaseRoleData {
	private String role;
	
	public DataBaseRoleData() {
		
	}
	
	public DataBaseRoleData(String string) {
		setRole(string);
	}
	
	private void setRole(String string) {
		role = string;
	}
	public String getRole() {
		return role;
	}
}
