package prism14;

public class RatioBoundaries {

	private double lowerBoundary, higherBoundary;
	private GeneralConstants.ComparePoint compareMethod;
	
	public RatioBoundaries(double lower, double heigher, GeneralConstants.ComparePoint compare) {
		
		this.lowerBoundary = lower;
		this.higherBoundary = heigher;
		this.compareMethod = compare;
	}
	
	public double getLowerBoundary() {
		return this.lowerBoundary;
	}
	public double getHigherBoundary() {
		return this.higherBoundary;
	}
	public GeneralConstants.ComparePoint getCompareMethod() {
		return this.compareMethod;
	}

}