package prism14;

public class DataBaseXxmDashSalesTable {
	private String uniquePartnerName;
	private String salesAgentName;
	private String salesLevel6;
	private String executionUnit;
	private String region;
	private String dataType;
	private double q1Rev;
	private double q2Rev;
	private double h1Rev;
	private double q3Rev;
	private double q4Rev;
	private double h2Rev;
	private double ytdRev;
	
	public DataBaseXxmDashSalesTable(String upn, String san, String dType, double q1, double q2, double h1, double q3, 
			double q4, double h2, double ytd) {
		setUniquePartnerName(upn);
		setSalesAgentName(san);
		setDataType(dType);
		setQ1Rev(q1);
		setQ2Rev(q2);
		setH1Rev(h1);
		setQ3Rev(q3);
		setQ4Rev(q4);
		setH2Rev(h2);
		setYTDRev(ytd);
	}
	private void setUniquePartnerName(String string) {
		uniquePartnerName = string;
	}
	public String getUniquePartnerName() {
		return uniquePartnerName;
	}
	private void setSalesAgentName(String string) {
		salesAgentName = string;
	}
	public String getSalesAgentName() {
		return salesAgentName;
	}
	private void setSalesLevel6(String string) {
		salesLevel6 = string;
	}
	public String getSalesLevel6() {
		return salesLevel6;
	}
	private void setExecutionUnit(String string) {
		executionUnit = string;
	}
	public String getExecutionUnit() {
		return executionUnit;
	}
	private void setRegion(String string) {
		region = string;
	}
	public String getRegion() {
		return region;
	}
	private void setDataType(String string) {
		dataType = string;
	}
	public String getDataType() {
		return dataType;
	}
	private void setQ1Rev(double d) {
		q1Rev = d;
	}
	public double getQ1Rev() {
		return q1Rev;
	}
	private void setQ2Rev(double d) {
		q2Rev = d;
	}
	public double getQ2Rev() {
		return q2Rev;
	}
	private void setH1Rev(double d) {
		h1Rev = d;
	}
	public double getH1Rev() {
		return h1Rev;
	}
	private void setQ3Rev(double d) {
		q3Rev = d;
	}
	public double getQ3Rev() {
		return q3Rev;
	}
	private void setQ4Rev(double d) {
		q4Rev = d;
	}
	public double getQ4Rev() {
		return q4Rev;
	}
	private void setH2Rev(double d) {
		h2Rev = d;
	}
	public double getH2Rev() {
		return h2Rev;
	}
	private void setYTDRev(double d) {
		ytdRev = d;
	}
	public double getYTDRev() {
		return ytdRev;
	}
}
