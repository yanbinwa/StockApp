package StockAppUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import StockApp.*;
import StockSchemaClass.*;
import StockBasic.*;
import StockData.*;


//这里会通过connectionPoll中的connection将stock数据写入到表中，还有方法是定期扫描表，将数据汇总以及age
public class DBUtils {
	
	public static boolean createTable(Connection conn, String excuteCommand)
	{
		try 
		{
			if (conn == null)
			{
				return false;
			}
			Statement st = conn.createStatement();
			st.execute(excuteCommand);
			return true;
		} 
		catch (SQLException e) 
		{
			System.out.println("Create table failed: " + e.getMessage());
			return false;
		}
	}
	
	public static boolean insertTable(Connection conn, String excuteCommand)
	{
		try 
		{
			if (conn == null)
			{
				return false;
			}
			Statement st = conn.createStatement();
			st.execute(excuteCommand);
			return true;
		} 
		catch (SQLException e) 
		{
			System.out.println("Insert table failed: " + e.getMessage());
			return false;
		}
	}
	
	public static boolean insertTable(Connection conn, DBTableType t)
	{
		boolean ret = insertTable(conn, t.getInsertDataExcuteCommand());
		if (!ret && !checkTable(conn, t.getTableName()))
		{
			ret = createTable(conn, t.getCreateTableExcuteCommand());
			if (ret)
			{
				ret = insertTable(conn, t.getInsertDataExcuteCommand());
			}
		}
		return ret;
	}
	
	public static ResultSet readTable(Connection conn, String excuteCommand)
	{
		try 
		{
			if (conn == null)
			{
				return null;
			}
			Statement st = conn.createStatement();
			ResultSet ret = st.executeQuery(excuteCommand);
			return ret;
		} 
		catch (SQLException e) 
		{
			System.out.println("Read table failed: " + e.getMessage());
			return null;
		}
	}
	
	public static List<StockDataType> readTable(Connection conn, DBTableType t)
	{
		ResultSet ret = readTable(conn, t.getReadDataExcuteCommand());
		List<StockDataType> outputList = null;
		if (ret != null)
		{
			outputList = t.parseDBData(ret);
		}
		return outputList;
	}
	
	public static boolean checkTable(Connection conn, String tableName)
	{
		try 
		{
			if (conn == null)
			{
				return false;
			}
			DatabaseMetaData meta = (DatabaseMetaData)conn.getMetaData();
			ResultSet rs = meta.getTables(null, null, tableName, null);
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static long getTimestamp(String dateStr, String timeStr)
	{
		if (dateStr == null || timeStr == null)
		{
			return 0;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try 
		{
			Date stockDate = format.parse(dateStr + " " + timeStr);
			return stockDate.getTime();
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean deleteTable(Connection conn, String tableName)
	{
		try 
		{
			if (conn == null)
			{
				return false;
			}
			Statement st = conn.createStatement();
			String excuteCommand = "DROP TABLE " + tableName;
			st.execute(excuteCommand);
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean clearTable(Connection conn, String tableName)
	{
		try 
		{
			if (conn == null)
			{
				return false;
			}
			Statement st = conn.createStatement();
			String excuteCommand = "TRUNCATE TABLE " + tableName;
			st.execute(excuteCommand);
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) throws SQLException
	{
		Connection conn = DataServer.connectionPool.getConnection();
		long lastAggregateTimestamp = 1457069373000L;
		long end = 1457070025000L;
		
//		StockDataDBQueryBean stockDataDBQueryBean = new StockDataDBQueryBean("sh600021_RawData", lastAggregateTimestamp, end - lastAggregateTimestamp);
//		List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockDataDBQueryBean);
//		StockDataType stockDataType = null;
//		stockDataType = stockDataDBQueryBean.aggragateDBData(stockDataTypeLists);
//		if (stockDataType instanceof StockDataOutput)
//		{
//			StockDataOutput stockDataOutput = (StockDataOutput)stockDataType;
//			String tableName = "aaa";
//			StockDataDBInsertBean stockDataDBInsertBean = new StockDataDBInsertBean(stockDataOutput, tableName);
//			DBUtils.insertTable(conn, stockDataDBInsertBean);
//		}
		
		StockDataDBQueryBean stockDataDBQueryBean = new StockDataDBQueryBean("aaa", lastAggregateTimestamp, end - lastAggregateTimestamp);
		List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockDataDBQueryBean);
		StockDataType stockDataType = null;
		stockDataType = stockDataDBQueryBean.aggragateDBData1(stockDataTypeLists);
		if (stockDataType instanceof StockDataOutput)
		{
			StockDataOutput stockDataOutput = (StockDataOutput)stockDataType;
			String tableName = "aaa1";
			StockDataDBInsertBean stockDataDBInsertBean = new StockDataDBInsertBean(stockDataOutput, tableName);
			DBUtils.insertTable(conn, stockDataDBInsertBean);
		}
		
//		StockMarketDBQueryBean stockMarketDBQueryBean = new StockMarketDBQueryBean("shanghai_RawData", lastAggregateTimestamp, end - lastAggregateTimestamp);
//		List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockMarketDBQueryBean);
//		StockDataType stockDataType = null;
//		stockDataType = stockMarketDBQueryBean.aggragateDBData(stockDataTypeLists);
//		if (stockDataType instanceof StockMarketOutput)
//		{
//			StockMarketOutput stockMarketOutput = (StockMarketOutput)stockDataType;
//			String tableName = "bbb";
//			StockMarketDBInsertBean stockMarketDBInsertBean = new StockMarketDBInsertBean(stockMarketOutput, tableName);
//			DBUtils.insertTable(conn, stockMarketDBInsertBean);
//		}
		
//		StockMarketDBQueryBean stockMarketDBQueryBean = new StockMarketDBQueryBean("bbb", lastAggregateTimestamp, end - lastAggregateTimestamp);
//		List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockMarketDBQueryBean);
//		StockDataType stockDataType = null;
//		stockDataType = stockMarketDBQueryBean.aggragateDBData1(stockDataTypeLists);
//		if (stockDataType instanceof StockMarketOutput)
//		{
//			StockMarketOutput stockMarketOutput = (StockMarketOutput)stockDataType;
//			String tableName = "bbb1";
//			StockMarketDBInsertBean stockMarketDBInsertBean = new StockMarketDBInsertBean(stockMarketOutput, tableName);
//			DBUtils.insertTable(conn, stockMarketDBInsertBean);
//		}
	}
}
