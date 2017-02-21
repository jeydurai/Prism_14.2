package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseXxmScorecardQueries {

	Connection conn;
	CallableStatement selectAll;
	
	public DataBaseXxmScorecardQueries() {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.URL,ConnectionString.USR,ConnectionString.PWD);
		
		selectAll = conn.prepareCall("{CALL get_xxm_scorecard_data(?, ?, ?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public ScorecardXxmDataset	getAll(String agentType, String finQuarter, String eu, 
			String region, String node, String agentName) throws SQLException {
		ScorecardXxmDataset finalResults = null;
		List<ScorecardGoalActualTable> results = null;
		List<ScorecardGrowthTable> results2 = null;
		List<ScorecardDiscountTable> results3 = null;
		List<ScorecardATPenetrationTable> results4 = null;
//		List<DataBaseXxmDashOtherTableModified> results5 = null;
		List<ScorecardSCPTable> results5 = null;
		List<ScorecardLeadsTable> results6 = null;
		List<ScorecardPartnerPenetrationTable> results7 = null;
		List<ScorecardLinearityTable> results8 = null;
		List<ScorecardPipelineTable> results9 = null;
		List<ScorecardDebookingTable> results10 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		ResultSet resultSet4 = null;
		ResultSet resultSet5 = null;
		ResultSet resultSet6 = null;
		ResultSet resultSet7 = null;
		ResultSet resultSet8 = null;
		ResultSet resultSet9 = null;
		ResultSet resultSet10 = null;
		try {
			selectAll.setString(1, agentType);
			selectAll.setString(2, finQuarter);
			selectAll.setString(3, eu);
			selectAll.setString(4, region);
			selectAll.setString(5, node);
			selectAll.setString(6, agentName);

			resultSet = selectAll.executeQuery();
			results = new ArrayList<ScorecardGoalActualTable>();
			while (resultSet.next()) {
				results.add(new ScorecardGoalActualTable 
						(resultSet.getDouble("value"),
						 resultSet.getString("sales_level_6"),
						 resultSet.getString("sales_agent_name"),
						 resultSet.getString("data_type")));
			}

			
			if (selectAll.getMoreResults()) {
				resultSet2 = selectAll.getResultSet();
				results2 = new ArrayList<ScorecardGrowthTable>();
				while (resultSet2.next()) {
					results2.add(new ScorecardGrowthTable 
							(resultSet2.getDouble("value"),
							 resultSet2.getString("sales_level_6"),
							 resultSet2.getString("sales_agent_name"),
							 resultSet2.getString("data_type")));
				}
			}
			
			if (selectAll.getMoreResults()) {
				resultSet3 = selectAll.getResultSet();
				results3 = new ArrayList<ScorecardDiscountTable>();
				while (resultSet3.next()) {
					results3.add(new ScorecardDiscountTable 
							(resultSet3.getString("sales_level_6"),
							 resultSet3.getString("sales_agent_name"),
							 resultSet3.getString("data_type"),
							 resultSet3.getDouble("ent_nw_bnet"),
							 resultSet3.getDouble("security_bnet"),
							 resultSet3.getDouble("collab_bnet"),
							 resultSet3.getDouble("dcv_bnet"),
							 resultSet3.getDouble("ent_nw_blist"),
							 resultSet3.getDouble("security_blist"),
							 resultSet3.getDouble("collab_blist"),
							 resultSet3.getDouble("dcv_blist")));
				}
			}
			
			if (selectAll.getMoreResults()) {
				resultSet4 = selectAll.getResultSet();
				results4 = new ArrayList<ScorecardATPenetrationTable>();
				while (resultSet4.next()) {
					results4.add(new ScorecardATPenetrationTable 
							(resultSet4.getString("sales_level_6"),
							 resultSet4.getString("sales_agent_name"),
							 resultSet4.getString("data_type"),
							 resultSet4.getDouble("sales_at"),
							 resultSet4.getDouble("sales_non_at")));
				}
			}

			if (selectAll.getMoreResults()) {
				resultSet5 = selectAll.getResultSet();
				results5 = new ArrayList<ScorecardSCPTable>();
				while (resultSet5.next()) {
					results5.add(new ScorecardSCPTable 
							(resultSet5.getString("sales_level_6"),
							 resultSet5.getString("sales_agent_name"),
							 resultSet5.getString("data_type"),
							 resultSet5.getDouble("account_penetration"),
							 resultSet5.getDouble("rev"),
							 resultSet5.getDouble("techs"),
							 resultSet5.getDouble("bnet"),
							 resultSet5.getDouble("blist"),
							 resultSet5.getDouble("total_accounts")));
				}
			}

			
			
/*			if (selectAll.getMoreResults()) {
				resultSet5 = selectAll.getResultSet();
				results5 = new ArrayList<DataBaseXxmDashOtherTableModified>();
					while (resultSet5.next()) {
						results5.add(new DataBaseXxmDashOtherTableModified 
								(resultSet5.getString(1),
								 resultSet5.getString(2),
								 resultSet5.getDouble(3),
								 resultSet5.getDouble(4),
								 resultSet5.getDouble(5),
								 resultSet5.getDouble(6),
								 resultSet5.getDouble(7),
								 resultSet5.getDouble(8),
								 resultSet5.getDouble(9),
								 resultSet5.getDouble(10),
								 resultSet5.getDouble(11),
								 resultSet5.getDouble(12),
								 resultSet5.getDouble(13),
								 resultSet5.getDouble(14),
								 resultSet5.getDouble(15),
								 resultSet5.getDouble(16),
								 resultSet5.getDouble(17),
								 resultSet5.getDouble(18),
								 resultSet5.getDouble(19),
								 resultSet5.getDouble(20),
								 resultSet5.getDouble(21),
								 resultSet5.getDouble(22),
								 resultSet5.getDouble(23),
								 resultSet5.getDouble(24),
								 resultSet5.getDouble(25),
								 resultSet5.getDouble(26),
								 resultSet5.getDouble(27),
								 resultSet5.getDouble(28),
								 resultSet5.getDouble(29),
								 resultSet5.getDouble(30),
								 resultSet5.getDouble(31),
								 resultSet5.getDouble(32),
								 resultSet5.getDouble(33),
								 resultSet5.getDouble(34),
								 resultSet5.getDouble(35),
								 resultSet5.getDouble(36),
								 resultSet5.getDouble(37),
								 resultSet5.getDouble(38),
								 resultSet5.getDouble(39),
								 resultSet5.getDouble(40),
								 resultSet5.getDouble(41),
								 resultSet5.getDouble(42),
								 resultSet5.getDouble(43),
								 resultSet5.getDouble(44),
								 resultSet5.getDouble(45),
								 resultSet5.getDouble(46),
								 resultSet5.getDouble(47),
								 resultSet5.getDouble(48),
								 resultSet5.getDouble(49),
								 resultSet5.getDouble(50),
								 resultSet5.getDouble(51),
								 resultSet5.getDouble(52),
								 resultSet5.getDouble(53),
								 resultSet5.getDouble(54),
								 resultSet5.getDouble(55),
								 resultSet5.getDouble(56),
								 resultSet5.getDouble(57),
								 resultSet5.getDouble(58),
								 resultSet5.getDouble(59),
								 resultSet5.getDouble(60),
								 resultSet5.getDouble(61),
								 resultSet5.getDouble(62),
								 resultSet5.getDouble(63),
								 resultSet5.getDouble(64),
								 resultSet5.getDouble(65),
								 resultSet5.getDouble(66),
								 resultSet5.getDouble(67),
								 resultSet5.getDouble(68),
								 resultSet5.getDouble(69),
								 resultSet5.getDouble(70),
								 resultSet5.getDouble(71),
								 resultSet5.getDouble(72),
								 resultSet5.getDouble(73),
								 resultSet5.getDouble(74),
								 resultSet5.getDouble(75),
								 resultSet5.getDouble(76),
								 resultSet5.getDouble(77),
								 resultSet5.getDouble(78),
								 resultSet5.getDouble(79),
								 resultSet5.getDouble(80),
								 resultSet5.getDouble(81),
								 resultSet5.getDouble(82),
								 resultSet5.getDouble(83),
								 resultSet5.getDouble(84),
								 resultSet5.getDouble(85),
								 resultSet5.getDouble(86),
								 resultSet5.getDouble(87),
								 resultSet5.getDouble(88),
								 resultSet5.getDouble(89),
								 resultSet5.getDouble(90),
								 resultSet5.getDouble(91),
								 resultSet5.getDouble(92),
								 resultSet5.getDouble(93),
								 resultSet5.getDouble(94),
								 resultSet5.getDouble(95)));
					}
				}
*/
			if (selectAll.getMoreResults()) {
				resultSet6 = selectAll.getResultSet();
				results6 = new ArrayList<ScorecardLeadsTable>();
				while (resultSet6.next()) {
					results6.add(new ScorecardLeadsTable 
							(resultSet6.getString("sales_level_6"),
							 resultSet6.getString("sales_agent_name"),
							 resultSet6.getString("data_type"),
							 resultSet6.getDouble("waiting"),
							 resultSet6.getDouble("accepted"),
							 resultSet6.getDouble("converted"),
							 resultSet6.getDouble("overall")));
				}
			}

			if (selectAll.getMoreResults()) {
				resultSet7 = selectAll.getResultSet();
				results7 = new ArrayList<ScorecardPartnerPenetrationTable>();
				while (resultSet7.next()) {
					results7.add(new ScorecardPartnerPenetrationTable 
							(resultSet7.getDouble("assigned_partner"),
							 resultSet7.getString("sales_level_6"),
							 resultSet7.getString("sales_agent_name"),
							 resultSet7.getString("data_type")));
				}
			}

			if (selectAll.getMoreResults()) {
				resultSet8 = selectAll.getResultSet();
				results8 = new ArrayList<ScorecardLinearityTable>();
				while (resultSet8.next()) {
					results8.add(new ScorecardLinearityTable 
							(resultSet8.getString("sales_level_6"),
							 resultSet8.getString("sales_agent_name"),
							 resultSet8.getString("data_type"),
							 resultSet8.getDouble("m1"),
							 resultSet8.getDouble("m2"),
							 resultSet8.getDouble("m3")));
				}
			}

			if (selectAll.getMoreResults()) {
				resultSet9 = selectAll.getResultSet();
				results9 = new ArrayList<ScorecardPipelineTable>();
				while (resultSet9.next()) {
					results9.add(new ScorecardPipelineTable 
							(resultSet9.getString("sales_level_6"),
							 resultSet9.getString("sales_agent_name"),
							 resultSet9.getString("data_type"),
							 resultSet9.getDouble("q1"),
							 resultSet9.getDouble("q2"),
							 resultSet9.getDouble("q3"),
							 resultSet9.getDouble("q4"),
							 resultSet9.getDouble("overall")));
				}
			}

			if (selectAll.getMoreResults()) {
				resultSet10 = selectAll.getResultSet();
				results10 = new ArrayList<ScorecardDebookingTable>();
				while (resultSet10.next()) {
					results10.add(new ScorecardDebookingTable 
							(resultSet10.getDouble("value"),
							 resultSet10.getString("sales_level_6"),
							 resultSet10.getString("sales_agent_name"),
							 resultSet10.getString("data_type")));
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
		
		finalResults = new ScorecardXxmDataset(results, results2, results3, results4,
				results5, results6, results7, results8, results9, results10);
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
