package prism14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAdminEUQueries {

	Connection conn;
	PreparedStatement selectEUbyUserName;
	
	public DataBaseAdminEUQueries() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.ADMIN_URL,ConnectionString.USR,ConnectionString.PWD);
		selectEUbyUserName = conn.prepareStatement("SELECT DISTINCT execution_unit FROM "
				+ "admin_eu WHERE user_name = ?");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseAdminEU> getEU(String u) throws SQLException {
		List<DataBaseAdminEU> results = null;
		ResultSet resultSet = null;
		try {
			selectEUbyUserName.setString(1, u);
			resultSet = selectEUbyUserName.executeQuery();
			results = new ArrayList<DataBaseAdminEU>();
				while (resultSet.next()) {
					results.add(new DataBaseAdminEU 
							(resultSet.getString("execution_unit")));
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
