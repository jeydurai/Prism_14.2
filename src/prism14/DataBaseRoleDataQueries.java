package prism14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataBaseRoleDataQueries {

	Connection conn;
	PreparedStatement selectAllRoles;
	
	public DataBaseRoleDataQueries() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		try {
			Class.forName(ConnectionString.REGISTER_STRING).newInstance();
			conn = DriverManager.getConnection(ConnectionString.URL, ConnectionString.USR, ConnectionString.PWD);
			selectAllRoles = conn.prepareStatement("SELECT * FROM role_data");
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
	public List<DataBaseRoleData> getAllRoles() throws SQLException {
		List<DataBaseRoleData> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectAllRoles.executeQuery();
			results = new ArrayList<DataBaseRoleData>();
				while (resultSet.next()) {
					results.add(new DataBaseRoleData (
					resultSet.getString("role")));
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
