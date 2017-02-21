package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAdminPartnerQueries {

	Connection conn;
	CallableStatement selectPartner;
	
	public DataBaseAdminPartnerQueries() {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.ADMIN_URL,ConnectionString.USR,ConnectionString.PWD);
		selectPartner = conn.prepareCall("{CALL get_partner(?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseAdminPartner> 
	getPartner(String node, String subSCMS,	String finYear, 
			String salesAgent) throws SQLException {
		List<DataBaseAdminPartner> results = null;
		ResultSet resultSet = null;
		try {
			selectPartner.setString(1, node);
			selectPartner.setString(2, subSCMS);
			selectPartner.setString(3, finYear);
			selectPartner.setString(4, salesAgent);
			resultSet = selectPartner.executeQuery();
			results = new ArrayList<DataBaseAdminPartner>();
				while (resultSet.next()) {
					results.add(new DataBaseAdminPartner 
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
