package prism14;

public class DataBaseUniqueData {

	private String name, fiscalYear, fiscalQuarter, subSCMS, executionUnit,
	region, salesLevel6, nameType;
	
	public DataBaseUniqueData(String option, String string) {
		switch (option) {
		case "1": setName(string);
				  break;
		case "2": setFiscalYear(string);
				  break;
		case "3": setFiscalQuarter(string);
		          break;
		case "4": setSubSCMS(string);
		          break;
		case "5": setExecutionUnit(string);
				  break;
		case "6": setRegion(string);
				  break;
		case "7": setSalesLevel6(string);
				  break;
		case "8": setNameType(string);
				  break;
		}
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public void setFiscalYear(String s) {
		fiscalYear = s;
	}
	
	public String getFiscalYear() {
		return fiscalYear;
	}
	
	public void setFiscalQuarter(String s) {
		fiscalQuarter = s;
	}
	
	public String getFiscalQuarter() {
		return fiscalQuarter;
	}
	
	public void setSubSCMS(String s) {
		subSCMS = s;
	}
	
	public String getSubSCMS() {
		return subSCMS;
	}
	
	public void setExecutionUnit(String s) {
		executionUnit = s;
	}
	
	public String getExecutionUnit() {
		return executionUnit;
	}
	
	public void setRegion(String s) {
		region = s;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setSalesLevel6(String s) {
		salesLevel6 = s;
	}
	
	public String getSalesLevel6() {
		return salesLevel6;
	}
	
	public void setNameType(String s) {
		nameType = s;
	}
	
	public String getNameType() {
		return nameType;
	}
	
}
