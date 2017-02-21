package prism14;

import java.util.List;

public class DashboardXxmDataset {

	private List<DataBaseXxmDashOtherTableModified> otherTableData;
	private List<DataBaseXxmDashSalesTable> salesTableData;
	private List<DataBaseXxmDashTechTable> techTableData;
	
	public DashboardXxmDataset(List<DataBaseXxmDashOtherTableModified> d1, 
			List<DataBaseXxmDashSalesTable> d2, List<DataBaseXxmDashTechTable> d3) {
		this.otherTableData = d1;
		this.salesTableData = d2;
		this.techTableData = d3;
	}
	
	public List<DataBaseXxmDashOtherTableModified> getOtherTableData() {
		return this.otherTableData;
	}

	public List<DataBaseXxmDashSalesTable> getSalesTableData() {
		return this.salesTableData;
	}

	public List<DataBaseXxmDashTechTable> getTechTableData() {
		return this.techTableData;
	}
	
}
