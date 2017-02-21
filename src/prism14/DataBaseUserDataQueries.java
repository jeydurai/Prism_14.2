package prism14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataBaseUserDataQueries{

	Connection conn;
	Connection conn2;
	PreparedStatement selectAllbyNameandPwd;
	PreparedStatement selectAllEU;
	PreparedStatement selectAllRegion;
	PreparedStatement selectAllRole;
	PreparedStatement selectRolebyName;
	PreparedStatement selectAllEUbyName;
	PreparedStatement selectReportingTobyName;
	PreparedStatement selectFirstNamebyName;
	PreparedStatement selectLastNamebyName;
	PreparedStatement selectEmailIDbyName;
	PreparedStatement selectEUbyName;
	PreparedStatement selectRegionbyName;
	PreparedStatement selectRegionbyEU;
	PreparedStatement selectUserNamebyReportingTo;
	PreparedStatement selectModuleAccessbyName;
	public DataBaseUserDataQueries() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		try {
			Class.forName(ConnectionString.REGISTER_STRING).newInstance();
			conn = DriverManager.getConnection(ConnectionString.URL, ConnectionString.USR, ConnectionString.PWD);
			conn2 = DriverManager.getConnection(ConnectionString.SPECIAL_URL, ConnectionString.USR, ConnectionString.PWD);
			selectAllbyNameandPwd = conn2.prepareStatement("SELECT * FROM user_data WHERE "
					+ "user_name = BINARY ? AND user_password = PASSWORD(?)");
			selectModuleAccessbyName = conn2.prepareStatement("SELECT role, admin_module, tbm_vbm_dash_module, tbm_vbm_score_module, "
					+ "general_dash_module, lead_indi_module, stack_rank_module, at_dash_module FROM user_data WHERE "
					+ "user_name = BINARY ?");
			selectAllEU = conn.prepareStatement("SELECT DISTINCT execution_unit FROM user_data ORDER BY execution_unit");
			selectAllRegion = conn.prepareStatement("SELECT DISTINCT region FROM user_data ORDER BY region");
			selectAllEUbyName = conn.prepareStatement("SELECT DISTINCT execution_unit FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY execution_unit");
			selectAllRole = conn.prepareStatement("SELECT DISTINCT role FROM user_data ORDER BY role");
			selectRolebyName = conn.prepareStatement("SELECT DISTINCT role FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY role");
			selectReportingTobyName = conn.prepareStatement("SELECT DISTINCT reporting_to FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY reporting_to");
			selectFirstNamebyName = conn.prepareStatement("SELECT DISTINCT first_name FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY first_name");
			selectLastNamebyName = conn.prepareStatement("SELECT DISTINCT last_name FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY last_name");
			selectEmailIDbyName = conn.prepareStatement("SELECT DISTINCT email_id FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY email_id");
			selectEUbyName = conn.prepareStatement("SELECT DISTINCT execution_unit FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY execution_unit");
			selectRegionbyName = conn.prepareStatement("SELECT DISTINCT region FROM user_data WHERE "
					+ "user_name = BINARY ? ORDER BY region");
			selectRegionbyEU = conn.prepareStatement("SELECT DISTINCT region FROM user_data WHERE "
					+ "execution_unit = BINARY ? ORDER BY region");
			selectUserNamebyReportingTo = conn.prepareStatement("SELECT DISTINCT user_name FROM user_data WHERE "
					+ "reporting_to = BINARY ? ORDER BY user_name");
		}
		catch (SQLException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
		catch (InstantiationException iException){
			iException.printStackTrace();
			System.exit(1);
		}
		catch (IllegalAccessException iAException){
			iAException.printStackTrace();
			System.exit(1);
		}
		catch (ClassNotFoundException cException){
			cException.printStackTrace();
			System.exit(1);
		}
		
	}
	public List<DataBaseUserLoginData> getPrimarybyNameandPwd(String u, String p) throws SQLException {
		List<DataBaseUserLoginData> results = null;
		ResultSet resultSet = null;
		try {
			selectAllbyNameandPwd.setString(1, u);
			selectAllbyNameandPwd.setString(2, p);
			resultSet = selectAllbyNameandPwd.executeQuery();
			results = new ArrayList<DataBaseUserLoginData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserLoginData (
					resultSet.getString("user_name"),
					resultSet.getString("user_password"),
					resultSet.getInt("hits")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	public List<DataBaseUserData> getAllEU() throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectAllEU.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("1",resultSet.getString("execution_unit")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	public List<DataBaseUserData> getAllRegion() throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectAllRegion.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("8",resultSet.getString("region")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	
	public List<DataBaseUserData> getAllRole() throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectAllRole.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("2",resultSet.getString("role")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}


	public List<DataBaseUserData> getUserNamebyReportingTo(String rT) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectUserNamebyReportingTo.setString(1, rT);
			resultSet = selectUserNamebyReportingTo.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("9",resultSet.getString("user_name")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

/*	public List<DataBaseUserData> getRolebyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectRolebyName.setString(1, u);
			resultSet = selectRolebyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
			while (resultSet.next()) {
				results.add(new DataBaseUserData (
				resultSet.getString("first_name"),
				resultSet.getString("last_name"),
				resultSet.getString("email_id"),
				resultSet.getString("role"),
				resultSet.getString("execution_unit"),
				resultSet.getString("department"),
				resultSet.getString("division"),
				resultSet.getString("user_password"),
				resultSet.getString("reporting_to"),
				resultSet.getString("region")));
			}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}
*/

	public List<DataBaseUserData> getModuleAccessbyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectModuleAccessbyName.setString(1, u);
			resultSet = selectModuleAccessbyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
			while (resultSet.next()) {
				results.add(new DataBaseUserData (
				resultSet.getString("role"),
				resultSet.getBoolean("admin_module"),
				resultSet.getBoolean("tbm_vbm_dash_module"),
				resultSet.getBoolean("tbm_vbm_score_module"),
				resultSet.getBoolean("general_dash_module"),
				resultSet.getBoolean("lead_indi_module"),
				resultSet.getBoolean("stack_rank_module"),
				resultSet.getBoolean("at_dash_module")));
			}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	
	public List<DataBaseUserData> getFirstNamebyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectFirstNamebyName.setString(1, u);
			resultSet = selectFirstNamebyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("4",resultSet.getString("first_name")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	public List<DataBaseUserData> getLastNamebyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectLastNamebyName.setString(1, u);
			resultSet = selectLastNamebyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("5",resultSet.getString("last_name")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	public List<DataBaseUserData> getEmailIDbyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectEmailIDbyName.setString(1, u);
			resultSet = selectEmailIDbyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("6",resultSet.getString("email_id")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	public List<DataBaseUserData> getEUbyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectEUbyName.setString(1, u);
			resultSet = selectEUbyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("7",resultSet.getString("execution_unit")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}
	
	public List<DataBaseUserData> getRegionbyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectRegionbyName.setString(1, u);
			resultSet = selectRegionbyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("8",resultSet.getString("region")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	public List<DataBaseUserData> getRegionbyEU(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectRegionbyEU.setString(1, u);
			resultSet = selectRegionbyEU.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("8",resultSet.getString("region")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}
	
	public List<DataBaseUserData> getReportingTobyName(String u) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectReportingTobyName.setString(1, u);
			resultSet = selectReportingTobyName.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData ("3",resultSet.getString("reporting_to")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}

	
	public List<DataBaseUserData> getAllbyNameandPwd(String u, String p) throws SQLException {
		List<DataBaseUserData> results = null;
		ResultSet resultSet = null;
		try {
			selectAllbyNameandPwd.setString(1, u);
			selectAllbyNameandPwd.setString(2, p);
			resultSet = selectAllbyNameandPwd.executeQuery();
			results = new ArrayList<DataBaseUserData>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserData (
					resultSet.getString("first_name"),
					resultSet.getString("last_name"),
					resultSet.getString("email_id"),
					resultSet.getString("role"),
					resultSet.getString("execution_unit"),
					resultSet.getString("department"),
					resultSet.getString("division"),
					resultSet.getString("user_password"),
					resultSet.getString("reporting_to"),
					resultSet.getString("region")));
				}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}
	
	
	public void close() {
		try{
			conn.close();
		}
		catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
	}
}
