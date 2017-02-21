package prism14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataBaseUserModuleAccessQueries {

	Connection conn;
	PreparedStatement selectPrivileges;
	
	public DataBaseUserModuleAccessQueries() {
		try {
			Class.forName(ConnectionString.REGISTER_STRING).newInstance();
			conn = DriverManager.getConnection(ConnectionString.SPECIAL_URL, ConnectionString.USR, ConnectionString.PWD);
			selectPrivileges = conn.prepareStatement("SELECT * FROM user_module_access WHERE "
					+ "role = ?");
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
	
	public List<DataBaseUserModuleAccess> getAllPrivileges(String r) throws SQLException {
		List<DataBaseUserModuleAccess> results = null;
		ResultSet resultSet = null;
		try {
			selectPrivileges.setString(1, r);
			resultSet = selectPrivileges.executeQuery();
			results = new ArrayList<DataBaseUserModuleAccess>();
			
				while (resultSet.next()) {
					results.add(new DataBaseUserModuleAccess (
					resultSet.getBoolean(2),
					resultSet.getBoolean(3),
					resultSet.getBoolean(4),
					resultSet.getBoolean(5),
					resultSet.getBoolean(6),
					resultSet.getBoolean(7)));
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
