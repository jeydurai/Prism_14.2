package prism14;

public class DataBaseUserModuleAccess {

	private String role;
	private boolean adminModule, tbmvbmModule, generalDashboard, 
	leadingIndicator, stackRanking, atDashboard;
	
	public DataBaseUserModuleAccess(boolean admin, boolean tbm, boolean general,
			boolean leading, boolean stack, boolean at) {
		this.adminModule = admin;
		this.tbmvbmModule = tbm;
		this.generalDashboard = general;
		this.leadingIndicator = leading;
		this.stackRanking = stack;
		this.atDashboard = at;
	}
	
	public boolean isAdminModuleAccessible() {
		return this.adminModule;
	}
	
	public boolean isTBMVBMModuleAccessible() {
		return this.tbmvbmModule;
	}
	
	public boolean isGeneralDashboardAccessible() {
		return this.generalDashboard;
	}
	
	public boolean isLeadingIndicatorAccessible() {
		return this.leadingIndicator;
	}
	
	public boolean isStackRankingAccessible() {
		return this.stackRanking;
	}

	public boolean isATDashboardAccessible() {
		return this.atDashboard;
	}
}
