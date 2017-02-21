package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataBaseAdminRegionQueries {

	Connection conn;
	CallableStatement selectRegionbyUserNameEU;

	public DataBaseAdminRegionQueries() {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.ADMIN_URL,ConnectionString.USR,ConnectionString.PWD);
		selectRegionbyUserNameEU = conn.prepareCall("{CALL get_region(?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseAdminRegion> getRegion(String u, String eu) throws SQLException {
		List<DataBaseAdminRegion> results = null;
		ResultSet resultSet = null;
		try {
			selectRegionbyUserNameEU.setString(1, u);
			selectRegionbyUserNameEU.setString(2, eu);
			resultSet = selectRegionbyUserNameEU.executeQuery();
			results = new ArrayList<DataBaseAdminRegion>();
				while (resultSet.next()) {
					results.add(new DataBaseAdminRegion 
							(resultSet.getString(1)));
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
