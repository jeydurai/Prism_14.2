package prism14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataBaseUserDataUpdate {
	Connection conn;
	PreparedStatement updateHitsbyNameandPwd;
	PreparedStatement updateAllbyNameandOldPwd;
	public DataBaseUserDataUpdate() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		try {
			Class.forName(ConnectionString.REGISTER_STRING).newInstance();
			conn = DriverManager.getConnection(ConnectionString.SPECIAL_URL, ConnectionString.USR, ConnectionString.PWD);
			updateHitsbyNameandPwd = conn.prepareStatement("UPDATE user_data SET hits = ? WHERE "
					+ "user_name = BINARY ? AND user_password = PASSWORD(?)");
			updateAllbyNameandOldPwd = conn.prepareStatement("UPDATE user_data "
					+ "SET user_password = PASSWORD(?),"
					+ "first_name = ?,"
					+ "last_name = ?,"
					+ "email_id = ?,"
					+ "reporting_to = ?,"
					+ "execution_unit = ?,"
					+ "department = ?,"
					+ "division = ?,"
					+ "region = ?,"
					+ "role = ?"
					+ " WHERE user_name = BINARY ? AND user_password = PASSWORD(?)");
			conn.setAutoCommit(false);
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
	public boolean activateUpdateHitsbyNameandPwd(int Int, String u, String p) throws SQLException {
		boolean result = false;
		try {
			updateHitsbyNameandPwd.setInt(1, Int);
			updateHitsbyNameandPwd.setString(2, u);
			updateHitsbyNameandPwd.setString(3, p);
			
			updateHitsbyNameandPwd.executeUpdate();
			updateHitsbyNameandPwd.close();
			conn.commit();
			conn.setAutoCommit(true);
			result = true;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			result = false;
		}
		finally {
			try {
				updateHitsbyNameandPwd.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return result;
	}

	public boolean activateUpdateAllbyNameandPwd(String nPwd, String fN, String lN, String eID,  String rT, String eu, 
			String dept, String dev, String reg, String role, String u, String oPwd) throws SQLException {
		boolean result = false;
		try {
			updateAllbyNameandOldPwd.setString(1, nPwd);
			updateAllbyNameandOldPwd.setString(2, fN);
			updateAllbyNameandOldPwd.setString(3, lN);
			updateAllbyNameandOldPwd.setString(4, eID);
			updateAllbyNameandOldPwd.setString(5, rT);
			updateAllbyNameandOldPwd.setString(6, eu);
			updateAllbyNameandOldPwd.setString(7, dept);
			updateAllbyNameandOldPwd.setString(8, dev);
			updateAllbyNameandOldPwd.setString(9, reg);
			updateAllbyNameandOldPwd.setString(10, role);
			updateAllbyNameandOldPwd.setString(11, u);
			updateAllbyNameandOldPwd.setString(12, oPwd);
			
			updateAllbyNameandOldPwd.executeUpdate();
			updateAllbyNameandOldPwd.close();
			conn.commit();
			conn.setAutoCommit(true);
			result = true;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			result = false;
		}
		finally {
			try {
				updateAllbyNameandOldPwd.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();
			}
		}
		return result;
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
