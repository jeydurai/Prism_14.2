package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUniqueDataQueries {

	Connection conn;
	PreparedStatement selectFY;
	CallableStatement selectNodes;
	
	public DataBaseUniqueDataQueries() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.URL,ConnectionString.USR,ConnectionString.PWD);
		selectFY = conn.prepareStatement("SELECT DISTINCT fiscal_year FROM "
				+ "unique_data_for_general_dashboard");
//		selectNodes = conn.prepareCall("{CALL general_dashboard_get_nodes(?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseUniqueData> getFY() throws SQLException {
		List<DataBaseUniqueData> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectFY.executeQuery();
			results = new ArrayList<DataBaseUniqueData>();
				while (resultSet.next()) {
					results.add(new DataBaseUniqueData ("2",
					resultSet.getString("fiscal_year")));
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

	public List<DataBaseUniqueData> getSalesLevel6(String fYear, String eu, String subSCMS, String region) throws SQLException {
		List<DataBaseUniqueData> results = null;
		ResultSet resultSet = null;
		try {
			selectNodes.setString(1, fYear);
			selectNodes.setString(2, eu);
			selectNodes.setString(3, subSCMS);
			selectNodes.setString(4, region);
			resultSet = selectNodes.executeQuery();
			results = new ArrayList<DataBaseUniqueData>();
				while (resultSet.next()) {
					results.add(new DataBaseUniqueData ("7",
					resultSet.getString("sales_level_6")));
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
