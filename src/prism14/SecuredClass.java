package prism14;

import java.util.prefs.Preferences;

public class SecuredClass {
	Preferences pref = Preferences.userNodeForPackage(SecuredClass.class);
	
	public void setCredentials(String userName, String password) {
		pref.put("db_username", userName);
		pref.put("db_password", password);
	}
	
	public String getUserName() {
		return pref.get("db_username", null);
	}
	public String getPassword() {
		return pref.get("db_password", null);
	}
}
