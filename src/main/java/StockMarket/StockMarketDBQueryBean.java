package StockMarket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import StockSchemaClass.*;
import StockBasic.*;

public class StockMarketDBQueryBean extends DBTableType{
    
    protected long _startTime = 0;
    protected long _interval = 0;
    
    public StockMarketDBQueryBean()
    {
    	
    }
    
    public StockMarketDBQueryBean(String tableName, long startTime, long interval)
    {
    	this.tableName = tableName;
    	this._startTime = startTime;
    	this._interval = interval;
    }
	
    public String getReadDataExcuteCommand()
	{
		String excuteCommand = "SELECT Timestamp, Date, Time, Curdot, StartDot, EndDot, DealNumber, Turnover, Hdot, Ldot " + 
				"FROM " + tableName + " " +
				"WHERE Timestamp >= " + _startTime + " AND Timestamp < " + (_startTime + _interval);
		System.out.println(excuteCommand);
		return excuteCommand;
	}
    
	public List<StockDataType> parseDBData(ResultSet ret)
    {
		if(ret == null)
		{
			return null;
		}
		List<StockDataType> outputList = new ArrayList<StockDataType>();
		try 
		{
			while(ret.next())
			{
				StockMarketOutput stockMarketOutput = new StockMarketOutput();
				stockMarketOutput.setTimestamp(ret.getLong("Timestamp"));
				stockMarketOutput.setDate(ret.getString("Date"));
				stockMarketOutput.setTime(ret.getString("Time"));
				stockMarketOutput.setCurdot(ret.getFloat("Curdot"));
				stockMarketOutput.setStartDot(ret.getFloat("StartDot"));
				stockMarketOutput.setEndDot(ret.getFloat("EndDot"));
				stockMarketOutput.setDealNumber(ret.getInt("DealNumber"));
				stockMarketOutput.setTurnover(ret.getFloat("Turnover"));
				stockMarketOutput.setHdot(ret.getFloat("Hdot"));
				stockMarketOutput.setLdot(ret.getFloat("Ldot"));
				outputList.add(stockMarketOutput);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	return outputList;
    }
	
	public StockDataType aggragateDBData(List<StockDataType> stockDataTypeList)
    {
		if (stockDataTypeList == null)
		{
			return null;
		}
		Map<Long, StockMarketOutput> stockMarketOutputMap = new HashMap<Long, StockMarketOutput>();
		ArrayList<Long> timestampList = new ArrayList<Long>();
		long startTimestamp = Long.MAX_VALUE;
		long endTimestamp = Long.MIN_VALUE;
		float hDot = Float.MIN_VALUE;
		float lDot = Float.MAX_VALUE;
		float totalDot = 0;
		int cnt = 0;
		
		for(StockDataType stockDataType : stockDataTypeList)
		{
			if (stockDataType instanceof StockMarketOutput)
			{
				StockMarketOutput stockMarketOutTemp = (StockMarketOutput)stockDataType;
				long timestampTemp  = stockMarketOutTemp.getTimestamp();
				if (timestampTemp < startTimestamp)
				{
					startTimestamp = timestampTemp;
				}
				if (timestampTemp > endTimestamp)
				{
					endTimestamp = timestampTemp;
				}
				timestampList.add(timestampTemp);
				
				float curDotTemp = stockMarketOutTemp.getCurdot();
				totalDot += curDotTemp;
				if (curDotTemp < lDot)
				{
					lDot = curDotTemp;
				}
				if (curDotTemp > hDot)
				{
					hDot = curDotTemp;
				}
				stockMarketOutputMap.put(timestampTemp, stockMarketOutTemp);
				cnt ++;
			}
		}
		
		if (cnt == 0)
		{
			return null;
		}
		StockMarketOutput stockMarketOutput = new StockMarketOutput();
		Collections.sort(timestampList);
		long timestamp = timestampList.get((cnt - 1) / 2);
		stockMarketOutput.setTimestamp(timestamp);
		stockMarketOutput.setDate(stockMarketOutputMap.get(timestamp).getDate());
		stockMarketOutput.setTime(stockMarketOutputMap.get(timestamp).getTime());
		stockMarketOutput.setCurdot(totalDot / cnt);
		stockMarketOutput.setStartDot(stockMarketOutputMap.get(startTimestamp).getStartDot());
		stockMarketOutput.setEndDot(stockMarketOutputMap.get(endTimestamp).getEndDot());
		stockMarketOutput.setRate((stockMarketOutput.getEndDot() - stockMarketOutput.getStartDot()) / stockMarketOutput.getStartDot() * 100);
		stockMarketOutput.setHdot(hDot);
		stockMarketOutput.setLdot(lDot);
		stockMarketOutput.setSwing((hDot - lDot) / stockMarketOutput.getCurdot() * 100);
		stockMarketOutput.setDealNumber(stockMarketOutputMap.get(endTimestamp).getDealNumber() - stockMarketOutputMap.get(startTimestamp).getDealNumber());
		stockMarketOutput.setTurnover(stockMarketOutputMap.get(endTimestamp).getTurnover() - stockMarketOutputMap.get(startTimestamp).getTurnover());
		stockMarketOutput.setStartTimestamp(startTimestamp);
		stockMarketOutput.setEndTimestamp(endTimestamp);
    	return stockMarketOutput;
    }
	
	public StockDataType aggragateDBData1(List<StockDataType> stockDataTypeList)
    {
		if (stockDataTypeList == null)
		{
			return null;
		}
		Map<Long, StockMarketOutput> stockMarketOutputMap = new HashMap<Long, StockMarketOutput>();
		ArrayList<Long> timestampList = new ArrayList<Long>();
		long startTimestamp = Long.MAX_VALUE;
		long endTimestamp = Long.MIN_VALUE;
		float hDot = Float.MIN_VALUE;
		float lDot = Float.MAX_VALUE;
		int totalDealNumber = 0;
		float totalTurnover = 0;
		float totalDot = 0;
		int cnt = 0;
		
		for(StockDataType stockDataType : stockDataTypeList)
		{
			if (stockDataType instanceof StockMarketOutput)
			{
				StockMarketOutput stockMarketOutTemp = (StockMarketOutput)stockDataType;
				long timestampTemp  = stockMarketOutTemp.getTimestamp();
				if (timestampTemp < startTimestamp)
				{
					startTimestamp = timestampTemp;
				}
				if (timestampTemp > endTimestamp)
				{
					endTimestamp = timestampTemp;
				}
				timestampList.add(timestampTemp);
				
				float curDotTemp = stockMarketOutTemp.getCurdot();
				totalDot += curDotTemp;
				totalDealNumber += stockMarketOutTemp.getDealNumber();
				totalTurnover += stockMarketOutTemp.getTurnover();
				if (stockMarketOutTemp.getLdot() < lDot)
				{
					lDot = stockMarketOutTemp.getLdot();
				}
				if (stockMarketOutTemp.getHdot() > hDot)
				{
					hDot = stockMarketOutTemp.getHdot();
				}
				stockMarketOutputMap.put(timestampTemp, stockMarketOutTemp);
				cnt ++;
			}
		}
		
		if (cnt == 0)
		{
			return null;
		}
		StockMarketOutput stockMarketOutput = new StockMarketOutput();
		Collections.sort(timestampList);
		long timestamp = timestampList.get((cnt - 1) / 2);
		stockMarketOutput.setTimestamp(timestamp);
		stockMarketOutput.setDate(stockMarketOutputMap.get(timestamp).getDate());
		stockMarketOutput.setTime(stockMarketOutputMap.get(timestamp).getTime());
		stockMarketOutput.setCurdot(totalDot / cnt);
		stockMarketOutput.setStartDot(stockMarketOutputMap.get(startTimestamp).getStartDot());
		stockMarketOutput.setEndDot(stockMarketOutputMap.get(endTimestamp).getEndDot());
		stockMarketOutput.setRate((stockMarketOutput.getEndDot() - stockMarketOutput.getStartDot()) / stockMarketOutput.getStartDot() * 100);
		stockMarketOutput.setHdot(hDot);
		stockMarketOutput.setLdot(lDot);
		stockMarketOutput.setSwing((hDot - lDot) / stockMarketOutput.getCurdot() * 100);
		stockMarketOutput.setDealNumber(totalDealNumber);
		stockMarketOutput.setTurnover(totalTurnover);
		stockMarketOutput.setStartTimestamp(startTimestamp);
		stockMarketOutput.setEndTimestamp(endTimestamp);
    	return stockMarketOutput;
    }
}

