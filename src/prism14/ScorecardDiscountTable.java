package prism14;

public class ScorecardDiscountTable {

	private double bookENTNW, bookSecurity, bookCollab, bookDCV, 
	listENTNW, listSecurity, listCollab, listDCV;
	private String node, salesAgent, dataType;
	
	public ScorecardDiscountTable(String node, String salesAgent, String dataType, double bookENTNW, double bookSecurity, double bookCollab, 
			double bookDCV, double listENTNW, double listSecurity, 
			double listCollab, double listDCV) {
		this.node = node;
		this.salesAgent = salesAgent;
		this.dataType = dataType;
		this.bookENTNW = bookENTNW;
		this.bookSecurity = bookSecurity;
		this.bookCollab = bookCollab;
		this.bookDCV = bookDCV;
		this.listENTNW = listENTNW;
		this.listSecurity = listSecurity;
		this.listCollab = listCollab;
		this.listDCV  = listDCV;
	}
	
	public String getNode() {
		return this.node;
	}
	
	public String getSalesAgent() {
		return this.salesAgent;
	}
	
	public String getDataType() {
		return this.dataType;
	}

	public double getBookENTNW() {
		return this.bookENTNW;
	}

	public double getBookSecurity() {
		return this.bookSecurity;
	}

	public double getBookCollab() {
		return this.bookCollab;
	}

	public double getBookDCV() {
		return this.bookDCV;
	}

	public double getListENTNW() {
		return this.listENTNW;
	}

	public double getListSecurity() {
		return this.listSecurity;
	}

	public double getListCollab() {
		return this.listCollab;
	}

	public double getListDCV() {
		return this.listDCV;
	}
}
