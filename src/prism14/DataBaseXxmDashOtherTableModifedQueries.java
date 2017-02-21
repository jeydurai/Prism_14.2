package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseXxmDashOtherTableModifedQueries {

	Connection conn;
	CallableStatement selectAll;
	
	public DataBaseXxmDashOtherTableModifedQueries() {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.URL,ConnectionString.USR,ConnectionString.PWD);
		
		selectAll = conn.prepareCall("{CALL get_xxm_dashboard_data(?, ?, ?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public DashboardXxmDataset	getAll(String parPre, String parDev, String bb, 
			String clPL, String shark, String option) throws SQLException {
		DashboardXxmDataset finalResults = null;
		List<DataBaseXxmDashOtherTableModified> results = null;
		List<DataBaseXxmDashSalesTable> results2 = null;
		List<DataBaseXxmDashTechTable> results3 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		try {
			selectAll.setString(1, parPre);
			selectAll.setString(2, parDev);
			selectAll.setString(3, bb);
			selectAll.setString(4, clPL);
			selectAll.setString(5, shark);
			selectAll.setString(6, option);
			resultSet = selectAll.executeQuery();
			results = new ArrayList<DataBaseXxmDashOtherTableModified>();
				while (resultSet.next()) {
					results.add(new DataBaseXxmDashOtherTableModified 
							(resultSet.getString(1),
							 resultSet.getString(2),
							 resultSet.getDouble(3),
							 resultSet.getDouble(4),
							 resultSet.getDouble(5),
							 resultSet.getDouble(6),
							 resultSet.getDouble(7),
							 resultSet.getDouble(8),
							 resultSet.getDouble(9),
							 resultSet.getDouble(10),
							 resultSet.getDouble(11),
							 resultSet.getDouble(12),
							 resultSet.getDouble(13),
							 resultSet.getDouble(14),
							 resultSet.getDouble(15),
							 resultSet.getDouble(16),
							 resultSet.getDouble(17),
							 resultSet.getDouble(18),
							 resultSet.getDouble(19),
							 resultSet.getDouble(20),
							 resultSet.getDouble(21),
							 resultSet.getDouble(22),
							 resultSet.getDouble(23),
							 resultSet.getDouble(24),
							 resultSet.getDouble(25),
							 resultSet.getDouble(26),
							 resultSet.getDouble(27),
							 resultSet.getDouble(28),
							 resultSet.getDouble(29),
							 resultSet.getDouble(30),
							 resultSet.getDouble(31),
							 resultSet.getDouble(32),
							 resultSet.getDouble(33),
							 resultSet.getDouble(34),
							 resultSet.getDouble(35),
							 resultSet.getDouble(36),
							 resultSet.getDouble(37),
							 resultSet.getDouble(38),
							 resultSet.getDouble(39),
							 resultSet.getDouble(40),
							 resultSet.getDouble(41),
							 resultSet.getDouble(42),
							 resultSet.getDouble(43),
							 resultSet.getDouble(44),
							 resultSet.getDouble(45),
							 resultSet.getDouble(46),
							 resultSet.getDouble(47),
							 resultSet.getDouble(48),
							 resultSet.getDouble(49),
							 resultSet.getDouble(50),
							 resultSet.getDouble(51),
							 resultSet.getDouble(52),
							 resultSet.getDouble(53),
							 resultSet.getDouble(54),
							 resultSet.getDouble(55),
							 resultSet.getDouble(56),
							 resultSet.getDouble(57),
							 resultSet.getDouble(58),
							 resultSet.getDouble(59),
							 resultSet.getDouble(60),
							 resultSet.getDouble(61),
							 resultSet.getDouble(62),
							 resultSet.getDouble(63),
							 resultSet.getDouble(64),
							 resultSet.getDouble(65),
							 resultSet.getDouble(66),
							 resultSet.getDouble(67),
							 resultSet.getDouble(68),
							 resultSet.getDouble(69),
							 resultSet.getDouble(70),
							 resultSet.getDouble(71),
							 resultSet.getDouble(72),
							 resultSet.getDouble(73),
							 resultSet.getDouble(74),
							 resultSet.getDouble(75),
							 resultSet.getDouble(76),
							 resultSet.getDouble(77),
							 resultSet.getDouble(78),
							 resultSet.getDouble(79),
							 resultSet.getDouble(80),
							 resultSet.getDouble(81),
							 resultSet.getDouble(82),
							 resultSet.getDouble(83),
							 resultSet.getDouble(84),
							 resultSet.getDouble(85),
							 resultSet.getDouble(86),
							 resultSet.getDouble(87),
							 resultSet.getDouble(88),
							 resultSet.getDouble(89),
							 resultSet.getDouble(90),
							 resultSet.getDouble(91),
							 resultSet.getDouble(92),
							 resultSet.getDouble(93),
							 resultSet.getDouble(94),
							 resultSet.getDouble(95)));
				}

				if (selectAll.getMoreResults()) {
					resultSet2 = selectAll.getResultSet();
					results2 = new ArrayList<DataBaseXxmDashSalesTable>();
					while (resultSet2.next()) {
						results2.add(new DataBaseXxmDashSalesTable 
								(resultSet2.getString(1),
								 resultSet2.getString(2),
								 resultSet2.getString(3),
								 resultSet2.getDouble(4),
								 resultSet2.getDouble(5),
								 resultSet2.getDouble(6),
								 resultSet2.getDouble(7),
								 resultSet2.getDouble(8),
								 resultSet2.getDouble(9),
								 resultSet2.getDouble(10)));
					}
				}

				
				if (selectAll.getMoreResults()) {
					resultSet3 = selectAll.getResultSet();
					results3 = new ArrayList<DataBaseXxmDashTechTable>();
					while (resultSet3.next()) {
						results3.add(new DataBaseXxmDashTechTable 
								(resultSet3.getString(1),
								 resultSet3.getString(2),
								 resultSet3.getString(3),
								 resultSet3.getDouble(4),
								 resultSet3.getDouble(5),
								 resultSet3.getDouble(6),
								 resultSet3.getDouble(7),
								 resultSet3.getDouble(8),
								 resultSet3.getDouble(9),
								 resultSet3.getDouble(10)));
					}
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
		
		finalResults = new DashboardXxmDataset(results, results2, results3);
		return finalResults;
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
