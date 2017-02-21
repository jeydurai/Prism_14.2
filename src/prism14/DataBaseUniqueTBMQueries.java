package prism14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUniqueTBMQueries {

	Connection conn;
	CallableStatement selectAll;
	
	public DataBaseUniqueTBMQueries(boolean isPreviousYear) {
		try {
		Class.forName(ConnectionString.REGISTER_STRING).newInstance();
		conn = DriverManager.getConnection(ConnectionString.URL,ConnectionString.USR,ConnectionString.PWD);
		
		selectAll = (isPreviousYear)? conn.prepareCall("{CALL get_tbmvbm_partner_customer_previous(?, ?, ?, ?, ?)}") :
			conn.prepareCall("{CALL get_tbmvbm_partner_customer(?, ?, ?, ?, ?)}");
		}
		catch (SQLException| InstantiationException | IllegalAccessException | 
				ClassNotFoundException sqlException){
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<DataBaseUniqueTBM> 
	getAll(String node, String name, String finYear, 
			String finQuarter, String finMonth) throws SQLException {
		List<DataBaseUniqueTBM> results = null;
		ResultSet resultSet = null;
		try {
			selectAll.setString(1, node);
			selectAll.setString(2, name);
			selectAll.setString(3, finYear);
			selectAll.setString(4, finQuarter);
			selectAll.setString(5, finMonth);
			resultSet = selectAll.executeQuery();
			results = new ArrayList<DataBaseUniqueTBM>();
				while (resultSet.next()) {
					results.add(new DataBaseUniqueTBM 
							(resultSet.getString(1),
							 resultSet.getString(2),
							 resultSet.getString(3),
							 resultSet.getString(4),
							 resultSet.getString(5),
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
							 resultSet.getDouble(95),
							 resultSet.getDouble(96)));
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
