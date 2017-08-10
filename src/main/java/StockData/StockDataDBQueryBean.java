package StockData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import StockSchemaClass.*;
import StockBasic.*;

public class StockDataDBQueryBean extends DBTableType{
    
    protected long _startTime = 0;
    protected long _interval = 0;
    
    public StockDataDBQueryBean()
    {
    	
    }
    
    public StockDataDBQueryBean(String tableName, long startTime, long interval)
    {
    	this.tableName = tableName;
    	this._startTime = startTime;
    	this._interval = interval;
    }
    
    public String getReadDataExcuteCommand()
	{
		String excuteCommand = "SELECT Timestamp, Date, Time, CurrentPrice, StartPrice, EndPrice, TotalNumber, " + 
				"Turnover, Hprice, Lprice, OutSize, InSize, MainFlowIn, MainFlowOut, RetailFlowIn, RetailFlowOut " + 
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
				StockDataOutput stockDataOutput = new StockDataOutput();
				stockDataOutput.setTimestamp(ret.getLong("Timestamp"));
				stockDataOutput.setDate(ret.getString("Date"));
				stockDataOutput.setTime(ret.getString("Time"));
				stockDataOutput.setCurrentPrice(ret.getFloat("CurrentPrice"));
				stockDataOutput.setStartPrice(ret.getFloat("StartPrice"));
				stockDataOutput.setEndPrice(ret.getFloat("EndPrice"));
				stockDataOutput.setTotalNumber(ret.getInt("TotalNumber"));
				stockDataOutput.setTurnover(ret.getFloat("Turnover"));
				stockDataOutput.setOutSize(ret.getInt("OutSize"));
				stockDataOutput.setInSize(ret.getInt("InSize"));
				stockDataOutput.setHprice(ret.getFloat("Hprice"));
				stockDataOutput.setLprice(ret.getFloat("Lprice"));
				stockDataOutput.setMainFlowIn(ret.getFloat("MainFlowIn"));
				stockDataOutput.setMainFlowOut(ret.getFloat("MainFlowOut"));
				stockDataOutput.setRetailFlowIn(ret.getFloat("RetailFlowIn"));
				stockDataOutput.setRetailFlowOut(ret.getFloat("RetailFlowOut"));
				outputList.add(stockDataOutput);
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
		Map<Long, StockDataOutput> stockDataOutputMap = new HashMap<Long, StockDataOutput>();
		ArrayList<Long> timestampList = new ArrayList<Long>();
		long startTimestamp = Long.MAX_VALUE;
		long endTimestamp = Long.MIN_VALUE;
		float hPrice = Float.MIN_VALUE;
		float lPrice = Float.MAX_VALUE;
		float totalPrice = 0;
		int cnt = 0;
		
		for(StockDataType stockDataType : stockDataTypeList)
		{
			if (stockDataType instanceof StockDataOutput)
			{
				StockDataOutput stockDataOutTemp = (StockDataOutput)stockDataType;
				long timestampTemp  = stockDataOutTemp.getTimestamp();
				if (timestampTemp < startTimestamp)
				{
					startTimestamp = timestampTemp;
				}
				if (timestampTemp > endTimestamp)
				{
					endTimestamp = timestampTemp;
				}
				timestampList.add(timestampTemp);
				
				float currentPriceTemp = stockDataOutTemp.getCurrentPrice();
				totalPrice += currentPriceTemp;
				if (currentPriceTemp < lPrice)
				{
					lPrice = currentPriceTemp;
				}
				if (currentPriceTemp > hPrice)
				{
					hPrice = currentPriceTemp;
				}
				stockDataOutputMap.put(timestampTemp, stockDataOutTemp);
				cnt ++;
			}
		}
		
		if (cnt == 0)
		{
			return null;
		}
		StockDataOutput stockDataOutput = new StockDataOutput();
		Collections.sort(timestampList);
		long timestamp = timestampList.get((cnt - 1) / 2);
		stockDataOutput.setTimestamp(timestamp);
		stockDataOutput.setDate(stockDataOutputMap.get(timestamp).getDate());
		stockDataOutput.setTime(stockDataOutputMap.get(timestamp).getTime());
		stockDataOutput.setCurrentPrice(totalPrice / cnt);
		stockDataOutput.setStartPrice(stockDataOutputMap.get(startTimestamp).getStartPrice());
		stockDataOutput.setEndPrice(stockDataOutputMap.get(endTimestamp).getEndPrice());
		stockDataOutput.setIncrease((stockDataOutput.getEndPrice() - stockDataOutput.getStartPrice()) / stockDataOutput.getStartPrice() * 100);
		stockDataOutput.setTotalNumber(stockDataOutputMap.get(endTimestamp).getTotalNumber() - stockDataOutputMap.get(startTimestamp).getTotalNumber());
		stockDataOutput.setTurnover(stockDataOutputMap.get(endTimestamp).getTurnover() - stockDataOutputMap.get(startTimestamp).getTurnover());
		stockDataOutput.setHprice(hPrice);
		stockDataOutput.setLprice(lPrice);
		stockDataOutput.setOutSize(stockDataOutputMap.get(endTimestamp).getOutSize() - stockDataOutputMap.get(startTimestamp).getOutSize());
		stockDataOutput.setInSize(stockDataOutputMap.get(endTimestamp).getInSize() - stockDataOutputMap.get(startTimestamp).getInSize());
		stockDataOutput.setSwing((stockDataOutput.getHprice() - stockDataOutput.getLprice()) / stockDataOutput.getCurrentPrice() * 100);
		stockDataOutput.setMainFlowIn(stockDataOutputMap.get(endTimestamp).getMainFlowIn() - stockDataOutputMap.get(startTimestamp).getMainFlowIn());
		stockDataOutput.setMainFlowOut(stockDataOutputMap.get(endTimestamp).getMainFlowOut() - stockDataOutputMap.get(startTimestamp).getMainFlowOut());
		stockDataOutput.setNetMainFlowIn(stockDataOutput.getMainFlowIn() - stockDataOutput.getMainFlowOut());
		if ((stockDataOutput.getMainFlowIn() + stockDataOutput.getMainFlowOut()) != 0)
		{
			stockDataOutput.setNetMainFlowInRatio(stockDataOutput.getNetMainFlowIn() / (stockDataOutput.getMainFlowIn() + stockDataOutput.getMainFlowOut()) * 100);
		}
		else
		{
			stockDataOutput.setNetMainFlowInRatio(0);
		}
		stockDataOutput.setRetailFlowIn(stockDataOutputMap.get(endTimestamp).getRetailFlowIn() - stockDataOutputMap.get(startTimestamp).getRetailFlowIn());
		stockDataOutput.setRetailFlowOut(stockDataOutputMap.get(endTimestamp).getRetailFlowOut() - stockDataOutputMap.get(startTimestamp).getRetailFlowOut());
		stockDataOutput.setNetRetailFlowIn(stockDataOutput.getRetailFlowIn() - stockDataOutput.getRetailFlowOut());
		if ((stockDataOutput.getRetailFlowIn() + stockDataOutput.getRetailFlowOut()) != 0)
		{
			stockDataOutput.setNetRetailFlowInRatio(stockDataOutput.getNetRetailFlowIn() / (stockDataOutput.getRetailFlowIn() + stockDataOutput.getRetailFlowOut()) * 100);
		}
		else
		{
			stockDataOutput.setNetRetailFlowInRatio(0);
		}
		stockDataOutput.setStartTimestamp(startTimestamp);
		stockDataOutput.setEndTimestamp(endTimestamp);
    	return stockDataOutput;
    }
	
	public StockDataType aggragateDBData1(List<StockDataType> stockDataTypeList)
    {
		if (stockDataTypeList == null)
		{
			return null;
		}
		Map<Long, StockDataOutput> stockDataOutputMap = new HashMap<Long, StockDataOutput>();
		ArrayList<Long> timestampList = new ArrayList<Long>();
		long startTimestamp = Long.MAX_VALUE;
		long endTimestamp = Long.MIN_VALUE;
		float hPrice = Float.MIN_VALUE;
		float lPrice = Float.MAX_VALUE;
		int totalNumber = 0;
		float totalTurnover = 0;
		int totalOutSize = 0;
		int totalInSize = 0;
		float totalMainFlowIn = 0;
		float totalMainFlowOut = 0;
		float totalRetailFlowIn = 0;
		float totalRetailFlowOut = 0;
		float totalPrice = 0;
		int cnt = 0;
		
		for(StockDataType stockDataType : stockDataTypeList)
		{
			if (stockDataType instanceof StockDataOutput)
			{
				StockDataOutput stockDataOutTemp = (StockDataOutput)stockDataType;
				long timestampTemp  = stockDataOutTemp.getTimestamp();
				if (timestampTemp < startTimestamp)
				{
					startTimestamp = timestampTemp;
				}
				if (timestampTemp > endTimestamp)
				{
					endTimestamp = timestampTemp;
				}
				timestampList.add(timestampTemp);
				
				float currentPriceTemp = stockDataOutTemp.getCurrentPrice();
				totalPrice += currentPriceTemp;
				totalNumber += stockDataOutTemp.getTotalNumber();
				totalTurnover += stockDataOutTemp.getTurnover();
				totalOutSize += stockDataOutTemp.getOutSize();
				totalInSize += stockDataOutTemp.getInSize();
				totalMainFlowIn += stockDataOutTemp.getMainFlowIn();
				totalMainFlowOut += stockDataOutTemp.getMainFlowOut();
				totalRetailFlowIn += stockDataOutTemp.getRetailFlowIn();
				totalRetailFlowOut += stockDataOutTemp.getRetailFlowOut();
				
				if (stockDataOutTemp.getLprice() < lPrice)
				{
					lPrice = stockDataOutTemp.getLprice();
				}
				if (stockDataOutTemp.getHprice() > hPrice)
				{
					hPrice = stockDataOutTemp.getHprice();
				}
				stockDataOutputMap.put(timestampTemp, stockDataOutTemp);
				cnt ++;
			}
		}
		
		if (cnt == 0)
		{
			return null;
		}
		StockDataOutput stockDataOutput = new StockDataOutput();
		Collections.sort(timestampList);
		long timestamp = timestampList.get((cnt - 1) / 2);
		stockDataOutput.setTimestamp(timestamp);
		stockDataOutput.setDate(stockDataOutputMap.get(timestamp).getDate());
		stockDataOutput.setTime(stockDataOutputMap.get(timestamp).getTime());
		stockDataOutput.setCurrentPrice(totalPrice / cnt);
		stockDataOutput.setStartPrice(stockDataOutputMap.get(startTimestamp).getStartPrice());
		stockDataOutput.setEndPrice(stockDataOutputMap.get(endTimestamp).getEndPrice());
		stockDataOutput.setIncrease((stockDataOutput.getEndPrice() - stockDataOutput.getStartPrice()) / stockDataOutput.getStartPrice() * 100);
		stockDataOutput.setTotalNumber(totalNumber);
		stockDataOutput.setTurnover(totalTurnover);
		stockDataOutput.setHprice(hPrice);
		stockDataOutput.setLprice(lPrice);
		stockDataOutput.setOutSize(totalOutSize);
		stockDataOutput.setInSize(totalInSize);
		stockDataOutput.setSwing((stockDataOutput.getHprice() - stockDataOutput.getLprice()) / stockDataOutput.getCurrentPrice() * 100);
		stockDataOutput.setMainFlowIn(totalMainFlowIn);
		stockDataOutput.setMainFlowOut(totalMainFlowOut);
		stockDataOutput.setNetMainFlowIn(stockDataOutput.getMainFlowIn() - stockDataOutput.getMainFlowOut());
		if ((stockDataOutput.getMainFlowIn() + stockDataOutput.getMainFlowOut()) != 0)
		{
			stockDataOutput.setNetMainFlowInRatio(stockDataOutput.getNetMainFlowIn() / (stockDataOutput.getMainFlowIn() + stockDataOutput.getMainFlowOut()) * 100);
		}
		else
		{
			stockDataOutput.setNetMainFlowInRatio(0);
		}
		stockDataOutput.setRetailFlowIn(totalRetailFlowIn);
		stockDataOutput.setRetailFlowOut(totalRetailFlowOut);
		stockDataOutput.setNetRetailFlowIn(stockDataOutput.getRetailFlowIn() - stockDataOutput.getRetailFlowOut());
		if ((stockDataOutput.getRetailFlowIn() + stockDataOutput.getRetailFlowOut()) != 0)
		{
			stockDataOutput.setNetRetailFlowInRatio(stockDataOutput.getNetRetailFlowIn() / (stockDataOutput.getRetailFlowIn() + stockDataOutput.getRetailFlowOut()) * 100);
		}
		else
		{
			stockDataOutput.setNetRetailFlowInRatio(0);
		}
		stockDataOutput.setStartTimestamp(startTimestamp);
		stockDataOutput.setEndTimestamp(endTimestamp);
    	return stockDataOutput;
    }
}

