package prism14;

public class DataBaseXxmDashTechTable {

	private String uniquePartnerName;
	private String salesAgentName;
	private String salesLevel6;
	private String executionUnit;
	private String region;
	private String dataType;
	private double rs;
	private double sec;
	private double ucs;
	private double uc;
	private double dcs;
	private double video;
	private double wLan;
	
	public DataBaseXxmDashTechTable(String upn, String san, String dType, double rs, double sec, double ucs, double uc, 
			double dcs, double video, double wlan) {
		
		setUniquePartnerName(upn);
		setSalesAgentName(san);
		setDataType(dType);
		setRS(rs);
		setSec(sec);
		setUCS(ucs);
		setUC(uc);
		setDCS(dcs);
		setVideo(video);
		setWLAN(wlan);
	}
	public DataBaseXxmDashTechTable(String op, String string) {
		switch (op) {
		case "1": setUniquePartnerName(string);
			break;
		case "2": setSalesAgentName(string);
			break;
		case "3": setSalesLevel6(string);
			break;
		case "4": setExecutionUnit(string);
			break;
		case "5": setRegion(string);
			break;
		case "6": setDataType(string);
			break;
		}
	}
	public DataBaseXxmDashTechTable(String op, double d) {
		switch (op) {
		case "1": setRS(d);
			break;
		case "2": setSec(d);
			break;
		case "3": setUCS(d);
			break;
		case "4": setUC(d);
			break;
		case "5": setDCS(d);
			break;
		case "6": setVideo(d);
			break;
		case "7": setWLAN(d);
			break;
		}
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
	private void setRS(double d) {
		rs = d;
	}
	public double getRS() {
		return rs;
	}
	private void setSec(double d) {
		sec = d;
	}
	public double getSec() {
		return sec;
	}
	private void setUCS(double d) {
		ucs = d;
	}
	public double getUCS() {
		return ucs;
	}
	private void setUC(double d) {
		uc = d;
	}
	public double getUC() {
		return uc;
	}
	private void setDCS(double d) {
		dcs = d;
	}
	public double getDCS() {
		return dcs;
	}
	private void setVideo(double d) {
		video = d;
	}
	public double getVideo() {
		return video;
	}
	private void setWLAN(double d) {
		wLan = d;
	}
	public double getWLAN() {
		return wLan;
	}
}
