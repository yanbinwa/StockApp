package StockMarket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import StockSchemaClass.*;
import StockBasic.*;

public class StockMarketDataRawData extends DBTableType{
	
	protected long _timestamp = 0;
	protected String _date = null;
	protected String _time = null;
	protected float _curdot = 0;
	protected float _curprice = 0;
	protected float _startDot = 0;
	protected float _endDot = 0;
	protected float _rate = 0;
	protected int _dealnumber = 0;
	protected float _turnover = 0;
	
	public StockMarketDataRawData(StockMarket stockMarket)
	{
		StockMarketInfo stockMarketInfo = stockMarket.getStockMarketData();
		_timestamp = stockMarket.getTimeStamp();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(_timestamp);
		_date = dateStr.split(" ")[0];
		_time = dateStr.split(" ")[1];
		_curdot = stockMarketInfo.getCurdot();
		_curprice = stockMarketInfo.getCurprice();
		if (stockMarket.getLastEndDot() > 0)
		{
			_startDot = stockMarket.getLastEndDot();
		}
		else
		{
			_startDot = _curdot;
		}
		_endDot = _curdot;
		_rate = stockMarketInfo.getRate();
		_dealnumber = stockMarketInfo.getDealnumber();
		_turnover = stockMarketInfo.getTurnover();
		
		this.tableName = stockMarket.getMarketName() + "_RawData";
	}
	
	public StockMarketDataRawData()
	{
		
	}
	
	public float getEndDot()
	{
		return _endDot;
	}
	
	public String getCreateTableExcuteCommand()
	{
		String excuteCommand = "CREATE TABLE " + this.tableName + 
				 " (" +
				 "Timestamp BIGINT(20) DEFAULT 0, " + 
				 "Date DATE DEFAULT NULL, " +
				 "Time TIME DEFAULT NULL, " +
				 "Curdot FLOAT DEFAULT 0, " + 
				 "StartDot FLOAT DEFAULT 0, " +
				 "EndDot FLOAT DEFAULT 0, " +
				 "Curprice FLOAT DEFAULT 0, " +
				 "Rate FLOAT DEFAULT 0, " +
				 "Dealnumber BIGINT(20) DEFAULT 0, " +
				 "Turnover FLOAT DEFAULT 0, " +
				 "Hdot FLOAT DEFAULT 0, " +
				 "Ldot FLOAT DEFAULT 0, " +
				 "PRIMARY KEY(Timestamp)" +
				 ")";
		 System.out.println(excuteCommand);
		 return excuteCommand;
	}
	
	public String getInsertDataExcuteCommand()
	{
		String parameter = String.format("%d, \"%s\", \"%s\", %2f, %2f, %2f, %2f, %2f, %d, %2f, %2f, %2f", 
				_timestamp, _date, _time, _curdot, _curprice, _startDot, _endDot,
				_rate, _dealnumber, _turnover, _curdot, _curdot);
		String excuteCommand = "INSERT INTO " + this.tableName +
				" (" +
				"Timestamp, Date, Time, Curdot, Curprice, StartDot, EndDot, Rate, Dealnumber, Turnover, Hdot, Ldot" + 
				") VALUES " +
				"(" +
				parameter +
				")";
		System.out.println(excuteCommand);
		return excuteCommand;
	}
	
	public static void main(String[] args)
	{

	}
}
