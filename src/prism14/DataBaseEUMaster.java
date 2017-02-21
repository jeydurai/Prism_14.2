package prism14;

public class DataBaseEUMaster {

	private String eUnit;
	private String region;
	
	public DataBaseEUMaster(float field, String string) {
		if (field == 1) {
			setEUnit(string);
		}
		else {
			setRegion(string);
		}
	}
	public DataBaseEUMaster(String eu, String reg) {
		setEUnit(eu);
		setRegion(reg);
	}
	private void setEUnit (String ex_u) {
		eUnit = ex_u;
	}
	public String getEUnit(){
		return eUnit;
	}

	private void setRegion (String reg) {
		region = reg;
	}
	public String getRegion(){
		return region;
	}
}
