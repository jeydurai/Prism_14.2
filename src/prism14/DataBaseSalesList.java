package prism14;

public class DataBaseSalesList {

	private String partnerName, uniquePartnerName, gainingAgentEmailId,
	salesAgentName, salesLevel6, salesAgentType, regionalisation,
	executionUnit, region, userName;
	private float geoId;
	
	public DataBaseSalesList (String pName, String uPName, String gAEId, String sAName, 
			String sL6, String sAType, String reg, String eUnit, float gId, String regi, String u) {
		setPartnerName(pName);
		setUniquePartnerName(uPName);
		setGainingAgentEmailId(gAEId);
		setSalesAgentName(sAName);
		setSalesLevel6(sL6);
		setSalesAgentType(sAType);
		setRegionalisation(reg);
		setExecutionUnit(eUnit);
		setGeoId(gId);
		setRegion(regi);
		setUserName(u);
	}

	public DataBaseSalesList (float fl) {
		setGeoId(fl);
	 }
	public DataBaseSalesList (String option, String string) {
		switch (option) {
		case "1": setPartnerName(string);
				  break;
		case "2": setUniquePartnerName(string);
				  break;
		case "3": setGainingAgentEmailId(string);
		          break;
		case "4": setSalesAgentName(string);
		          break;
		case "5": setSalesLevel6(string);
				  break;
		case "6": setSalesAgentType(string);
				  break;
		case "7": setRegionalisation(string);
				  break;
		case "8": setExecutionUnit(string);
				  break;
		case "9": setRegion(string);
		  		  break;
		case "10": setUserName(string);
		  		  break;
		}
	}
	
	public String getPartnerName(){
		return partnerName;
	}
	private void setPartnerName(String string){
		partnerName = string;
	}
		
	public String getUniquePartnerName(){
		return uniquePartnerName;
	}
	private void setUniquePartnerName(String string){
		uniquePartnerName = string;
	}
	
	public String getGainingAgentEmailId(){
		return gainingAgentEmailId;
	}
	private void setGainingAgentEmailId(String string){
		gainingAgentEmailId = string;
	}

	public String getSalesAgentName(){
		return salesAgentName;
	}
	private void setSalesAgentName(String string){
		salesAgentName = string;
	}

	public String getSalesLevel6(){
		return salesLevel6;
	}
	private void setSalesLevel6(String string){
		salesLevel6 = string;
	}

	public String getSalesAgentType(){
		return salesAgentType;
	}
	private void setSalesAgentType(String string){
		salesAgentType = string;
	}

	public String getRegionalisation(){
		return regionalisation;
	}
	private void setRegionalisation(String string){
		regionalisation = string;
	}

	public String getExecutionUnit(){
		return executionUnit;
	}
	private void setExecutionUnit(String string){
		executionUnit = string;
	}

	public float getGeoId(){
		return geoId;
	}
	private void setGeoId(float f){
		geoId = f;
	}

	public String getRegion(){
		return region;
	}
	private void setRegion(String string){
		region = string;
	}

	public String getUserName(){
		return userName;
	}
	private void setUserName(String string){
		userName = string;
	}
}
