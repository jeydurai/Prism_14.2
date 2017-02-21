package prism14;

public class ScorecardDiscountValues {

	private double bookActualENTNW, bookActualSecurity, bookActualCollab, bookActualDCV, 
	listActualENTNW, listActualSecurity, listActualCollab, listActualDCV;
	
	private double bookThresholdENTNW, bookThresholdSecurity, bookThresholdCollab, bookThresholdDCV, 
	listThresholdENTNW, listThresholdSecurity, listThresholdCollab, listThresholdDCV;

	public ScorecardDiscountValues(double bookActualENTNW, double bookActualSecurity, double bookActualCollab, 
			double bookActualDCV, double listActualENTNW, double listActualSecurity, 
			double listActualCollab, double listActualDCV, double bookThresholdENTNW, 
			double bookThresholdSecurity, double bookThresholdCollab, double bookThresholdDCV, 
			double listThresholdENTNW, double listThresholdSecurity, double listThresholdCollab, 
			double listThresholdDCV) {
		this.bookActualENTNW = bookActualENTNW;
		this.bookActualSecurity = bookActualSecurity;
		this.bookActualCollab = bookActualCollab;
		this.bookActualDCV = bookActualDCV;
		this.listActualENTNW = listActualENTNW;
		this.listActualSecurity = listActualSecurity;
		this.listActualCollab = listActualCollab;
		this.listActualDCV  = listActualDCV;
		this.bookThresholdENTNW = bookThresholdENTNW;
		this.bookThresholdSecurity = bookThresholdSecurity;
		this.bookThresholdCollab = bookThresholdCollab;
		this.bookThresholdDCV  = bookThresholdDCV;
		this.listThresholdENTNW = listThresholdENTNW;
		this.listThresholdSecurity = listThresholdSecurity;
		this.listThresholdCollab = listThresholdCollab;
		this.listThresholdDCV = listThresholdDCV;
	}
	
	public double getBookActualENTNW() {
		return this.bookActualENTNW;
	}

	public double getBookActualSecurity() {
		return this.bookActualSecurity;
	}

	public double getBookActualCollab() {
		return this.bookActualCollab;
	}

	public double getBookActualDCV() {
		return this.bookActualDCV;
	}

	
	public double getListActualENTNW() {
		return this.listActualENTNW;
	}

	public double getListActualSecurity() {
		return this.listActualSecurity;
	}

	public double getListActualCollab() {
		return this.listActualCollab;
	}

	public double getListActualDCV() {
		return this.listActualDCV;
	}

	
	public double getBookThresholdENTNW() {
		return this.bookThresholdENTNW;
	}

	public double getBookThresholdSecurity() {
		return this.bookThresholdSecurity;
	}

	public double getBookThresholdCollab() {
		return this.bookThresholdCollab;
	}

	public double getBookThresholdDCV() {
		return this.bookThresholdDCV;
	}

	
	public double getListThresholdENTNW() {
		return this.listThresholdENTNW;
	}

	public double getListThresholdSecurity() {
		return this.listThresholdSecurity;
	}

	public double getListThresholdCollab() {
		return this.listThresholdCollab;
	}

	public double getListThresholdDCV() {
		return this.listThresholdDCV;
	}

}
