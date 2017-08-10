package StockData;

import StockSchemaClass.*;
import StockBasic.*;

public class StockDataDBInsertBean extends DBTableType
{
	StockDataOutput _stockDataOutput = null;
	
	public StockDataDBInsertBean(StockDataOutput stockDataOutput, String tableName)
	{
		this._stockDataOutput = stockDataOutput;
		this.tableName = tableName;
	}
	
	public StockDataDBInsertBean()
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
				 "CurrentPrice FLOAT DEFAULT 0, " + 
				 "Increase FLOAT DEFAULT 0, " +
				 "StartPrice FLOAT DEFAULT 0, " +
				 "EndPrice FLOAT DEFAULT 0, " +
				 "TotalNumber BIGINT(20) DEFAULT 0, " +
				 "Turnover FLOAT DEFAULT 0, " +
				 "Hprice FLOAT DEFAULT 0, " +
				 "Lprice FLOAT DEFAULT 0, " +
				 "OutSize BIGINT(20) DEFAULT 0, " +
				 "InSize BIGINT(20) DEFAULT 0, " +
				 "Swing FLOAT DEFAULT 0, " +
				 "MainFlowIn FLOAT DEFAULT 0, " +
				 "MainFlowOut FLOAT DEFAULT 0, " +
				 "NetMainFlowIn FLOAT DEFAULT 0, " +
				 "NetMainFlowInRatio FLOAT DEFAULT 0, " +
				 "RetailFlowIn FLOAT DEFAULT 0, " +
				 "RetailFlowOut FLOAT DEFAULT 0, " +
				 "NetRetailFlowIn FLOAT DEFAULT 0, " +
				 "NetRetailFlowInRatio FLOAT DEFAULT 0, " +
				 "PRIMARY KEY(Timestamp)" +
				 ")";
		 System.out.println(excuteCommand);
		 return excuteCommand;
	}
	
	public String getInsertDataExcuteCommand()
	{
		String parameter = String.format("%d, %d, %d, \"%s\", \"%s\", %2f, %2f, %2f, %2f, "
				+ "%d, %2f, %2f, %2f, %d, "
				+ "%d, %2f, %2f, %2f, %2f, "
				+ "%2f, %2f, %2f, %2f, %2f",
				_stockDataOutput.getTimestamp(), _stockDataOutput.getStartTimestamp(), _stockDataOutput.getEndTimestamp(), _stockDataOutput.getDate(), _stockDataOutput.getTime(), _stockDataOutput.getCurrentPrice(), _stockDataOutput.getIncrease(), _stockDataOutput.getStartPrice(), _stockDataOutput.getEndPrice(), 
				_stockDataOutput.getTotalNumber(), _stockDataOutput.getTurnover(), _stockDataOutput.getHprice(), _stockDataOutput.getLprice(), _stockDataOutput.getOutSize(),
				_stockDataOutput.getInSize(), _stockDataOutput.getSwing(), _stockDataOutput.getMainFlowIn(), _stockDataOutput.getMainFlowOut(), _stockDataOutput.getNetMainFlowIn(), 
				_stockDataOutput.getNetMainFlowInRatio(), _stockDataOutput.getRetailFlowIn(), _stockDataOutput.getRetailFlowOut(), _stockDataOutput.getNetRetailFlowIn(), _stockDataOutput.getNetRetailFlowInRatio());
		String excuteCommand = "INSERT INTO " + this.tableName +
				" (" +
				"Timestamp, StartTimestamp, EndTimestamp, Date, Time, CurrentPrice, Increase, StartPrice, EndPrice, TotalNumber, Turnover, Hprice, Lprice, " + 
				"OutSize, InSize, Swing, MainFlowIn, MainFlowOut, NetMainFlowIn, NetMainFlowInRatio, RetailFlowIn, " +
				"RetailFlowOut, NetRetailFlowIn, NetRetailFlowInRatio" +
				") VALUES " +
				"(" +
				parameter +
				")";
		System.out.println(excuteCommand);
		return excuteCommand;
	}
}
