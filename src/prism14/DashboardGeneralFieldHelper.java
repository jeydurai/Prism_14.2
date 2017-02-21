package prism14;

public class DashboardGeneralFieldHelper {

	private PicturedPanel panel;
	private boolean isAttempted;
	
	public DashboardGeneralFieldHelper(PicturedPanel p, boolean bool) {
		this.panel = p;
		this.isAttempted = bool;
	}
	
	public PicturedPanel getPanel() {
		return panel;
	}
	
	public boolean isCompareYearAttempted() {
		return isAttempted;
	}
}
