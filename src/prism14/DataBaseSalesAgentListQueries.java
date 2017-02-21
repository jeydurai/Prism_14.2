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

public class DataBaseSalesAgentListQueries {

	Connection conn;
	CallableStatement selectAll;
	PreparedStatement selectSalesAgentName;
	PreparedStatement selectSalesAgentType;
	PreparedStatement selectSL6bySATypeandEUandRegion;
	PreparedStatement selectXxmbySATypeandEUandNode;
	PreparedStatement selectXxmbySATypeandNode;
	PreparedStatement selectXxmbyNodeandName;
	PreparedStatement selectXxmbySATypeNodeandName;
	PreparedStatement selectXxmbyNode;
	PreparedStatement selectPartnerbyXxm;
	PreparedStatement selectPartnerbyXxmandNode;
	PreparedStatement selectAllSL6;
	PreparedStatement selectSL6byUserName;
	PreparedStatement selectSL6byXxmOptionandUserName;
	PreparedStatement selectSL6byEU;
	PreparedStatement selectSL6byRegion;
	PreparedStatement selectSATypebyName;
	public DataBaseSalesAgentListQueries () throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.URL,ConnectionString.USR,ConnectionString.PWD);
		selectSalesAgentType = conn.prepareStatement("SELECT DISTINCT sales_agent_type FROM sales_agent_list ORDER BY sales_agent_type");
		selectSATypebyName = conn.prepareStatement("SELECT DISTINCT sales_agent_type FROM sales_agent_list "
				+ " WHERE user_name = BINARY ? ORDER BY sales_agent_type");
		selectSalesAgentName = conn.prepareStatement("SELECT DISTINCT sales_agent_name FROM sales_agent_list ORDER BY sales_agent_name");
		selectSL6bySATypeandEUandRegion = conn.prepareStatement("SELECT DISTINCT sales_level_6 FROM sales_agent_list WHERE sales_agent_type = ? AND "
				+ "execution_unit = ? AND region = ? ORDER BY sales_level_6");
		selectXxmbySATypeandEUandNode = conn.prepareStatement("SELECT DISTINCT sales_agent_name FROM sales_agent_list WHERE sales_agent_type = ? AND "
				+ "execution_unit = ? AND sales_level_6 = ? ORDER BY sales_agent_name");
		selectXxmbySATypeandNode = conn.prepareStatement("SELECT DISTINCT sales_agent_name FROM sales_agent_list WHERE sales_agent_type = ? AND "
				+ "sales_level_6 = ? ORDER BY sales_agent_name");
		selectXxmbyNodeandName = conn.prepareStatement("SELECT DISTINCT sales_agent_name FROM sales_agent_list WHERE "
				+ "sales_level_6 = ? AND user_name = ? ORDER BY sales_agent_name");
		selectXxmbySATypeNodeandName = conn.prepareStatement("SELECT DISTINCT sales_agent_name FROM sales_agent_list WHERE "
				+ "sales_agent_type = ? AND sales_level_6 = ? AND user_name = ? ORDER BY sales_agent_name");
		selectXxmbyNode = conn.prepareStatement("SELECT DISTINCT sales_agent_name FROM sales_agent_list WHERE "
				+ "sales_level_6 = ? ORDER BY sales_agent_name");
		selectPartnerbyXxm = conn.prepareStatement("SELECT DISTINCT unique_partner_name FROM sales_agent_list WHERE "
				+ "sales_agent_name = ? ORDER BY unique_partner_name");
		selectPartnerbyXxmandNode = conn.prepareStatement("SELECT DISTINCT unique_partner_name FROM sales_agent_list WHERE "
				+ "sales_level_6 = ? AND sales_agent_name = ? ORDER BY unique_partner_name");
		selectAllSL6 = conn.prepareStatement("SELECT DISTINCT sales_level_6 FROM sales_agent_list ORDER BY sales_level_6");
		selectSL6byUserName = conn.prepareStatement("SELECT DISTINCT sales_level_6 FROM sales_agent_list WHERE "
				+ "user_name = BINARY ? ORDER BY sales_level_6");
		selectSL6byXxmOptionandUserName = conn.prepareStatement("SELECT DISTINCT sales_level_6 FROM sales_agent_list WHERE "
				+ "sales_agent_type = ? AND user_name = BINARY ? ORDER BY sales_level_6");
		selectSL6byEU = conn.prepareStatement("SELECT DISTINCT sales_level_6 FROM sales_agent_list WHERE "
				+ "execution_unit = BINARY ? ORDER BY sales_level_6");
		selectSL6byRegion = conn.prepareStatement("SELECT DISTINCT sales_level_6 FROM sales_agent_list WHERE "
				+ "region = BINARY ? ORDER BY sales_level_6");
		selectAll = conn.prepareCall("{CALL get_stack_ranking_data(?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseSalesList> getSAType() throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectSalesAgentType.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("6",
					resultSet.getString("sales_agent_type")));
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

	public List<DataBaseSalesList> getSATypebyName(String u) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectSATypebyName.setString(1, u);
			resultSet = selectSATypebyName.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("6",
					resultSet.getString("sales_agent_type")));
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

	
	public List<DataBaseSalesList> getSL6byUserName(String u) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectSL6byUserName.setString(1, u);
			resultSet = selectSL6byUserName.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("5",
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

	public List<DataBaseSalesList> getSL6byXxmOptionandUserName(String sAType, String u) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectSL6byXxmOptionandUserName.setString(1, sAType);
			selectSL6byXxmOptionandUserName.setString(2, u);
			resultSet = selectSL6byXxmOptionandUserName.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("5",
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

	
	public List<DataBaseSalesList> getSL6byEU(String eu) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectSL6byEU.setString(1, eu);
			resultSet = selectSL6byEU.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("5",
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

	public List<DataBaseSalesList> getSL6byRegion(String eu) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectSL6byRegion.setString(1, eu);
			resultSet = selectSL6byRegion.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("5",
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
	
	public List<DataBaseSalesList> getAllSL6() throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectAllSL6.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("5",
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

	
	public List<DataBaseSalesList> getSL6bySATypeandEUandRegion(String sAType, String eU, String reg) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectSL6bySATypeandEUandRegion.setString(1, sAType);
			selectSL6bySATypeandEUandRegion.setString(2, eU);
			selectSL6bySATypeandEUandRegion.setString(3, reg);
			resultSet = selectSL6bySATypeandEUandRegion.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("5",
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

	
	public List<DataBaseSalesList> getXxmbyNodeandName(String Node, String u) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectXxmbyNodeandName.setString(1, Node);
			selectXxmbyNodeandName.setString(2, u);
			resultSet = selectXxmbyNodeandName.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("4",
					resultSet.getString("sales_agent_name")));
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

	public List<DataBaseSalesList> getXxmbySATypeNodeandName(String sAType, String node, String u) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectXxmbySATypeNodeandName.setString(1, sAType);
			selectXxmbySATypeNodeandName.setString(2, node);
			selectXxmbySATypeNodeandName.setString(3, u);
			resultSet = selectXxmbySATypeNodeandName.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("4",
					resultSet.getString("sales_agent_name")));
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

	
	public List<DataBaseSalesList> getXxmbyNode(String Node) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectXxmbyNode.setString(1, Node);
			resultSet = selectXxmbyNode.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("4",
					resultSet.getString("sales_agent_name")));
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
	
	
	public List<DataBaseSalesList> getXxmbySATypeandEUandNode(String sAType, String eU, String sL6) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectXxmbySATypeandEUandNode.setString(1, sAType);
			selectXxmbySATypeandEUandNode.setString(2, eU);
			selectXxmbySATypeandEUandNode.setString(3, sL6);
			resultSet = selectXxmbySATypeandEUandNode.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("4",
					resultSet.getString("sales_agent_name")));
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

	public List<DataBaseSalesList> getXxmbySATypeandNode(String sAType, String sL6) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectXxmbySATypeandNode.setString(1, sAType);
			selectXxmbySATypeandNode.setString(2, sL6);
			resultSet = selectXxmbySATypeandNode.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("4",
					resultSet.getString("sales_agent_name")));
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

	
	public List<DataBaseSalesList> getPartnerbyXxm(String xxm) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectPartnerbyXxm.setString(1, xxm);
			resultSet = selectPartnerbyXxm.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("2",
					resultSet.getString("unique_partner_name")));
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

	public List<DataBaseSalesList> getPartnerbyXxmandNode(String node, String xxm) throws SQLException {
		List<DataBaseSalesList> results = null;
		ResultSet resultSet = null;
		try {
			selectPartnerbyXxmandNode.setString(1, node);
			selectPartnerbyXxmandNode.setString(2, xxm);
			resultSet = selectPartnerbyXxmandNode.executeQuery();
			results = new ArrayList<DataBaseSalesList>();
			
				while (resultSet.next()) {
					results.add(new DataBaseSalesList ("2",
					resultSet.getString("unique_partner_name")));
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


	public List<DataBaseSalesAgentList> getAll(String salesAgentType) throws SQLException {
		List<DataBaseSalesAgentList> results = null;
		ResultSet resultSet = null;
		try {
			selectAll.setString(1, salesAgentType);
			resultSet = selectAll.executeQuery();
			results = new ArrayList<DataBaseSalesAgentList>();
				while (resultSet.next()) {
					results.add(new DataBaseSalesAgentList (
							resultSet.getString("sales_agent_name"),
							resultSet.getString("sales_level_6"),
							resultSet.getString("sales_agent_type"),
							resultSet.getString("execution_unit"),
							resultSet.getString("region"),
							resultSet.getString("user_name")));
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
