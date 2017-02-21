package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAdminSalesAgentQueries {

	Connection conn;
	CallableStatement selectSalesAgentandNode;
	
	public DataBaseAdminSalesAgentQueries() {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.ADMIN_URL,ConnectionString.USR,ConnectionString.PWD);
		selectSalesAgentandNode = conn.prepareCall("{CALL get_sales_agent(?, ?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseAdminSalesAgent> 
	getSalesLevel6(String node, String subSCMS, String region, 
			String finYear, String eu) throws SQLException {
		List<DataBaseAdminSalesAgent> results = null;
		ResultSet resultSet = null;
		try {
			selectSalesAgentandNode.setString(1, node);
			selectSalesAgentandNode.setString(2, subSCMS);
			selectSalesAgentandNode.setString(3, region);
			selectSalesAgentandNode.setString(4, finYear);
			selectSalesAgentandNode.setString(5, eu);
			resultSet = selectSalesAgentandNode.executeQuery();
			results = new ArrayList<DataBaseAdminSalesAgent>();
				while (resultSet.next()) {
					results.add(new DataBaseAdminSalesAgent 
							(resultSet.getString(1),
							 resultSet.getString(2),
							 resultSet.getString(3)));
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
