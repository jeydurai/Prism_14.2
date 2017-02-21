package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAdminNodeQueries {

	Connection conn;
	CallableStatement selectNodeandRegion;
	
	public DataBaseAdminNodeQueries() {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.ADMIN_URL,ConnectionString.USR,ConnectionString.PWD);
		selectNodeandRegion = conn.prepareCall("{CALL get_node(?, ?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseAdminNode> 
	getSalesLevel6(String u, String subSCMS, String region, 
			String finYear, String eu) throws SQLException {
		List<DataBaseAdminNode> results = null;
		ResultSet resultSet = null;
		try {
			selectNodeandRegion.setString(1, u);
			selectNodeandRegion.setString(2, subSCMS);
			selectNodeandRegion.setString(3, region);
			selectNodeandRegion.setString(4, finYear);
			selectNodeandRegion.setString(5, eu);
			resultSet = selectNodeandRegion.executeQuery();
			results = new ArrayList<DataBaseAdminNode>();
				while (resultSet.next()) {
					results.add(new DataBaseAdminNode 
							(resultSet.getString(1),
							 resultSet.getString(2)));
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
