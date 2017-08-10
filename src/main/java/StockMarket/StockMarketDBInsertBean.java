package StockMarket;

import StockSchemaClass.*;
import StockBasic.*;

public class StockMarketDBInsertBean extends DBTableType
{
	StockMarketOutput _stockMarketOutput = null;
	
	public StockMarketDBInsertBean(StockMarketOutput stockMarketOutput, String tableName)
	{
		this._stockMarketOutput = stockMarketOutput;
		this.tableName = tableName;
	}
	
	public StockMarketDBInsertBean()
	{
		
	}
	
	public String getCreateTableExcuteCommand()
	{
		 String excuteCommand = "CREATE TABLE " + this.tableName + 
				 " (" +
				 "Timestamp BIGINT(20) DEFAULT 0, " + 
				 "StartTimestamp BIGINT(20) DEFAULT 0, " + 
				 "EndTimestamp BIGINT(20) DEFAULT 0, " + 
				 "Date DATE DEFAULT NULL, " +
				 "Time TIME DEFAULT NULL, " +
				 "Curdot FLOAT DEFAULT 0, " + 
				 "Rate FLOAT DEFAULT 0, " + 
				 "StartDot FLOAT DEFAULT 0, " +
				 "EndDot FLOAT DEFAULT 0, " +
				 "DealNumber BIGINT(20) DEFAULT 0, " +
				 "Turnover FLOAT DEFAULT 0, " +
				 "Hdot FLOAT DEFAULT 0, " +
				 "Ldot FLOAT DEFAULT 0, " +
				 "Swing FLOAT DEFAULT 0, " +
				 "PRIMARY KEY(Timestamp)" +
				 ")";
		 System.out.println(excuteCommand);
		 return excuteCommand;
	}
	
	public String getInsertDataExcuteCommand()
	{
		String parameter = String.format("%d, %d, %d, \"%s\", \"%s\", %2f, %2f, %2f, %2f, "
				+ "%d, %2f, %2f, %2f, %2f",
				_stockMarketOutput.getTimestamp(), _stockMarketOutput.getStartTimestamp(), _stockMarketOutput.getEndTimestamp(), _stockMarketOutput.getDate(), _stockMarketOutput.getTime(), _stockMarketOutput.getCurdot(), _stockMarketOutput.getRate(), _stockMarketOutput.getStartDot(), _stockMarketOutput.getEndDot(), 
				_stockMarketOutput.getDealNumber(), _stockMarketOutput.getTurnover(), _stockMarketOutput.getHdot(), _stockMarketOutput.getLdot(), _stockMarketOutput.getSwing());
		String excuteCommand = "INSERT INTO " + this.tableName +
				" (" +
				"Timestamp, StartTimestamp, EndTimestamp, Date, Time, Curdot, Rate, StartDot, EndDot, DealNumber, Turnover, Hdot, Ldot, Swing" + 
				") VALUES " +
				"(" +
				parameter +
				")";
		System.out.println(excuteCommand);
		return excuteCommand;
	}
}
