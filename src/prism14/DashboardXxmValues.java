package prism14;

public class DashboardXxmValues {

	private DashboardXxmOtherTableValues otherTableValues;
	private DashboardXxmSalesTableValues salesTableValues;
	private DashboardXxmTechTableValues techTableValues;
	
	public DashboardXxmValues(DashboardXxmOtherTableValues otherTableValues,
			DashboardXxmSalesTableValues salesTableValues,
			DashboardXxmTechTableValues techTableValues) {
		this.otherTableValues = otherTableValues;
		this.salesTableValues = salesTableValues;
		this.techTableValues = techTableValues;
	}

	public DashboardXxmOtherTableValues getOtherTableValues() {
		return this.otherTableValues;}
	
	public DashboardXxmSalesTableValues getSalesTableValues() {
		return this.salesTableValues;}

	public DashboardXxmTechTableValues getTechTableValues() {
		return this.techTableValues;}
}
