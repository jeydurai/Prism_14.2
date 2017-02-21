package prism14;

public class DataBaseXxmScorecardDiscount {

	private String salesAgentName, salesLevel6, dataType;
	private double bookENTNW, listENTNW, bookSecurity, listSecurity, 
	bookCollab, listCollab, bookDCV, listDCV, bookAll, listAll;
	
	public DataBaseXxmScorecardDiscount(String saName, String sl6, String dType, 
			double bookENTNW, double listENTNW, double bookSecurity, double listSecurity, 
			double bookCollab, double listCollab, double bookDCV, double listDCV) {
		this.salesAgentName = saName;
		this.salesLevel6 = sl6;
		this.dataType = dType;
		this.bookENTNW = bookENTNW;
		this.listENTNW = listENTNW;
		this.bookSecurity = bookSecurity;
		this.listSecurity = listSecurity;
		this.bookCollab = bookCollab;
		this.listCollab = listCollab;
		this.bookDCV = bookDCV;
		this.listDCV = listDCV;
		this.bookAll = bookENTNW + bookSecurity + bookCollab + bookDCV;
		this.listAll = listENTNW + listSecurity + listCollab + listDCV;
	}
	
	
	public String getSalesAgentName() {
		return this.salesAgentName;
	}
	
	public String getNode() {
		return this.salesLevel6;
	}
	
	public String getDataType() {
		return this.dataType;
	}
	
	public double getBookingENTNW() {
		return this.bookENTNW;
	}

	public double getBookingSecurity() {
		return this.bookSecurity;
	}
	
	public double getBookingCollab() {
		return this.bookCollab;
	}

	public double getBookingDCV() {
		return this.bookDCV;
	}

	public double getBookingAll() {
		return this.bookAll;
	}

	public double getListingENTNW() {
		return this.listENTNW;
	}

	public double getListingSecurity() {
		return this.listSecurity;
	}
	
	public double getListingCollab() {
		return this.listCollab;
	}

	public double getListingDCV() {
		return this.listDCV;
	}

	public double getListingAll() {
		return this.listAll;
	}

}
