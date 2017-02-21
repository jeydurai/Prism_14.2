package prism14;

public class DataBaseUserLoginData {

	private String userName;
	private String userPwd;
	private int hits;
	private String firstName;
	private String lastName;
	private String emailID;
	private String reportingTo;
	private String role;
	private String eU;
	private String department;
	private String division;
	private String region;

	public DataBaseUserLoginData(String u, String p, int Int) {
		setUserName(u);
		setUserPwd(p);
		setHits(Int);
	}
	public DataBaseUserLoginData(String op, String string) {
		switch (op) {
		case "1" :
			setEU(string);
			break;
		case "2" :
			setRole(string);
			break;
		case "3" :
			setReportingTo(string);
			break;
		case "4" :
			setFirstName(string);
			break;
		case "5" :
			setLastName(string);
			break;
		case "6" :
			setEmailID(string);
			break;
		case "7" :
			setEU(string);
			break;
		case "8" :
			setRegion(string);
			break;
		case "9" :
			setUserName(string);
			break;
		}
	}	
		public DataBaseUserLoginData(String fN, String lN, String eID, String role, String eu, 
				String dept, String dev, String pwd,String rep,String reg) {
			setFirstName(fN);
			setLastName(lN);
			setEmailID(eID);
			setRole(role);
			setEU(eu);
			setDepartment(dept);
			setDivision(dev);
			setUserPwd(pwd);
			setReportingTo(rep);
			setRegion(reg);
		}

		public DataBaseUserLoginData(String u) {
			setUserName(u);
		}
		
		private void setUserName (String string) {
			userName = string;
		}
		public String getUserName() {
			return userName;
		}
		private void setUserPwd (String string) {
			userPwd = string;
		}
		public String getUserPwd() {
			return userPwd;
		}
		private void setHits (int Int) {
			hits = Int;
		}
		public int getHits() {
			return hits;
		}

		private void setFirstName (String string) {
			firstName = string;
		}
		public String getFirstName() {
			return firstName;
		}

		private void setLastName (String string) {
			lastName = string;
		}
		public String getLastName() {
			return lastName;
		}

		private void setEmailID (String string) {
			emailID = string;
		}
		public String getEmailID() {
			return emailID;
		}

		private void setRole (String string) {
			role = string;
		}
		public String getRole() {
			return role;
		}

		private void setEU (String string) {
			eU = string;
		}
		public String getEU() {
			return eU;
		}

		private void setDepartment (String string) {
			department = string;
		}
		public String getDepartment() {
			return department;
		}

		private void setReportingTo (String string) {
			reportingTo = string;
		}
		public String getReportingTo() {
			return reportingTo;
		}

		private void setRegion (String string) {
			region = string;
		}
		public String getRegion() {
			return region;
		}

		private void setDivision (String string) {
			division = string;
		}
		public String getDivision() {
			return division;
		}
		
}
