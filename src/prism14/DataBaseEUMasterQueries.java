package prism14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataBaseEUMasterQueries {
	Connection conn;
	PreparedStatement selectEU;
	PreparedStatement selectRegionbyEU;
	public DataBaseEUMasterQueries() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			Class.forName(ConnectionString.REGISTER_STRING).newInstance();
			conn = DriverManager.getConnection(ConnectionString.URL,ConnectionString.USR,ConnectionString.PWD);
			selectEU = conn.prepareStatement("SELECT DISTINCT execution_unit FROM eu_master");
			selectRegionbyEU = conn.prepareStatement("SELECT DISTINCT region FROM eu_master WHERE execution_unit=?");
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
	} //end of constructor

	public List<DataBaseEUMaster> getEU() throws SQLException {
		List<DataBaseEUMaster> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectEU.executeQuery();
			results = new ArrayList<DataBaseEUMaster>();
			while (resultSet.next()) {
				results.add(new DataBaseEUMaster(1f,
				resultSet.getString("execution_unit")));
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
			} // end of finally catch
		} // end of finally
		return results;
	}

	public List<DataBaseEUMaster> getRegion(String eu) throws SQLException {
		List<DataBaseEUMaster> results = null;
		ResultSet resultSet = null;
		try {
			selectRegionbyEU.setString(1, eu);
			resultSet = selectRegionbyEU.executeQuery();
			results = new ArrayList<DataBaseEUMaster>();
			while (resultSet.next()) {
				results.add(new DataBaseEUMaster(2f,
				resultSet.getString("region")));
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
			} // end of finally catch
		} // end of finally
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
