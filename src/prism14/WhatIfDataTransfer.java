package prism14;

public class WhatIfDataTransfer {

	private double disAllValue, disENTNWValue, disSecValue,
	disDCVValue, disCollValue, cusPenValue, techPenValue;
	
	public WhatIfDataTransfer(double disAllValue, double disENTNWValue, double disSecValue,
			double disDCVValue, double disCollValue, double cusPenValue, 
			double techPenValue) {

		this.disAllValue = disAllValue;
		this.disENTNWValue = disENTNWValue;
		this.disSecValue = disSecValue;
		this.disDCVValue = disDCVValue;
		this.disCollValue = disCollValue;
		this.cusPenValue = cusPenValue;
		this.techPenValue = techPenValue;
	}
	
	public double getDiscountAllValue() {
		return this.disAllValue;
	}
	
	public double getDiscountENTNWValue() {
		return this.disENTNWValue;
	}
	
	public double getDiscountSecValue() {
		return this.disSecValue;
	}

	public double getDiscountCollValue() {
		return this.disCollValue;
	}
	
	public double getDiscountDCVValue() {
		return this.disDCVValue;
	}
	
	public double getCusPenValue() {
		return this.cusPenValue;
	}

	public double getTechPenValue() {
		return this.techPenValue;
	}
	
}
