package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAdminCustomerQueries {

	Connection conn;
	CallableStatement selectCustomer;
	
	public DataBaseAdminCustomerQueries() {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.ADMIN_URL,ConnectionString.USR,ConnectionString.PWD);
		selectCustomer = conn.prepareCall("{CALL get_customer(?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseAdminCustomer> 
	getCustomer(String node, String subSCMS, String finYear, 
			String salesAgent) throws SQLException {
		List<DataBaseAdminCustomer> results = null;
		ResultSet resultSet = null;
		try {
			selectCustomer.setString(1, node);
			selectCustomer.setString(2, subSCMS);
			selectCustomer.setString(3, finYear);
			selectCustomer.setString(4, salesAgent);
			resultSet = selectCustomer.executeQuery();
			results = new ArrayList<DataBaseAdminCustomer>();
				while (resultSet.next()) {
					results.add(new DataBaseAdminCustomer 
							(resultSet.getString(1),
							 resultSet.getString(2),
							 resultSet.getString(3),
							 resultSet.getString(4)));
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
