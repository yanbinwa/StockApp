package StockData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import StockApp.*;
import StockSchemaClass.*;
import StockBasic.*;
import StockAppUtils.*;

public class Stock extends StockType{
	
	protected StockId _stockId = null;
	protected StockData _stockData = null;
	protected StockHandicapData _stockHandicapData = null;
	protected long _lastStockDataTimestamp = 0;
	protected boolean _shouldUpdateDB = true;
	protected int _buyDealNumber = 0;
	protected int _saleDealNumber = 0;
	protected int _totalDealNumber = 0;
	protected int _relativeDealNumber = 0;
	
	protected Map<String, Long> _intervalMap = null;
	protected Map<String, Long> _aggregateTimestampMap = null;
	protected Map<String, Long> _nextAggregateTimestampMap = null;
	protected Map<String, String> _readTableNameMap = null;
	protected StockDataRawData _lastStockDataRawData = null;
	
	public Stock()
	{
		init();
	}
	
	public Stock(StockId stockId)
	{
		this._stockId = stockId;
		init();
	}
	
	public void init()
	{
		_intervalMap = new HashMap<String, Long>();
		_intervalMap.put(StockParameter.STOCK_AGGREGATE_1MIN, StockParameter.INTERVAL_1MIN);
		_intervalMap.put(StockParameter.STOCK_AGGREGATE_5MIN, StockParameter.INTERVAL_5MIN);
		_intervalMap.put(StockParameter.STOCK_AGGREGATE_15MIN, StockParameter.INTERVAL_15MIN);
		_intervalMap.put(StockParameter.STOCK_AGGREGATE_60MIN, StockParameter.INTERVAL_60MIN);
		_intervalMap.put(StockParameter.STOCK_AGGREGATE_DAY, StockParameter.INTERVAL_DAY);
		
		_aggregateTimestampMap = new HashMap<String, Long>();
		Date now = new Date();
		long timestamp = now.getTime();
		_aggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_1MIN, timestamp);
		_aggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_5MIN, timestamp);
		_aggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_15MIN, timestamp);
		_aggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_60MIN, timestamp);
		_aggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_DAY, timestamp);
		
		_nextAggregateTimestampMap = new HashMap<String, Long>();
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_1MIN, calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_1MIN), StockParameter.INTERVAL_1MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_5MIN, calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_5MIN), StockParameter.INTERVAL_5MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_15MIN, calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_15MIN), StockParameter.INTERVAL_15MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_60MIN, calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_60MIN), StockParameter.INTERVAL_60MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_DAY, calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_DAY), StockParameter.INTERVAL_DAY));

		_readTableNameMap = new HashMap<String, String>();
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_1MIN, this.getId() + "_RawData");
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_5MIN, this.getId() + "_" + StockParameter.STOCK_AGGREGATE_1MIN);
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_15MIN, this.getId() + "_" + StockParameter.STOCK_AGGREGATE_5MIN);
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_60MIN, this.getId() + "_" + StockParameter.STOCK_AGGREGATE_15MIN);
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_DAY, this.getId() + "_" + StockParameter.STOCK_AGGREGATE_60MIN);
	}
	
	public void setStockId(StockId stockId)
	{
		this._stockId = stockId;
	}
	
	public StockId getStockId()
	{
		return this._stockId;
	}
	
	public void setStockData(StockData stockData)
	{
		this._stockData = stockData;
	}
	
	public StockData getStockData()
	{
		return this._stockData;
	}
	
	public void setStockHandicapData(StockHandicapData stockHandicapData)
	{
		this._stockHandicapData = stockHandicapData;
	}
	
	public StockHandicapData getStockHandicapData()
	{
		return this._stockHandicapData;
	}
	
	public static String queryStockData(String id)
	{
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String> header = new HashMap<String, String>();
		param.clear();
		param.put("stockid", id);
		param.put("list", "1");
		
		header.clear();
		header.put("apikey", StockParameter.APIKEY);	
		return HttpUtils.sendGet(StockParameter.STOCK_URL, param, header);
	}
	
	private void queryStockDataList(String id)
	{
		String data = queryStockData(id);
		String stockDataStr = JsonUtils.getStockData(data);
		List<StockDataType> stockDataList =  (ArrayList<StockDataType>) JsonUtils.getObjectListFromJson(stockDataStr, StockData.class);
		if(stockDataList == null)
		{
			System.out.println("flush stock " + id + " is failded" );
			return;
		}
		StockDataType stockDataType = stockDataList.get(0);
		if (stockDataType instanceof StockData)
		{
			StockData stockData = (StockData)stockDataType;
			long timestamp = DBUtils.getTimestamp(stockData.getDate(), stockData.getTime());
			if(timestamp != 0 && timestamp != this._lastStockDataTimestamp)
			{
				setStockData(stockData);
				this._shouldUpdateDB = true;
				this._lastStockDataTimestamp = timestamp;
			}
		}
		else
		{
			System.out.println("the stockDataType is not an instance of StockData");
		}
	}
	
	private void queryStockHandicapData(String id)
	{
		String url = StockParameter.STOCK_HANDICAP_URL + "q=" + id;
		String stockHandicapDataStr = HttpUtils.sendGet(url, null, null);
		if (stockHandicapDataStr == null)
		{
			return;
		}
		String[] stockHandicapDatas = stockHandicapDataStr.split("=")[1].replaceAll("[\";]", "").split("~");
		if (stockHandicapDatas.length != 50)
		{
			return;
		}
		StockHandicapData stockHandicapData = new StockHandicapData();
		stockHandicapData.setOutSize(Integer.valueOf(stockHandicapDatas[7]));
		stockHandicapData.setInSize(Integer.valueOf(stockHandicapDatas[8]));
		stockHandicapData.setExchangeRatio(Float.valueOf(stockHandicapDatas[38]));
		stockHandicapData.setPriceEaringRatio(Float.valueOf(stockHandicapDatas[39]));
		stockHandicapData.setSwing(Float.valueOf(stockHandicapDatas[43]));
		stockHandicapData.setCirculatedMarket(Float.valueOf(stockHandicapDatas[44]));
		stockHandicapData.setTotalMarket(Float.valueOf(stockHandicapDatas[45]));
		stockHandicapData.setPriceToBookRatio(Float.valueOf(stockHandicapDatas[46]));
		
		url = StockParameter.STOCK_HANDICAP_URL + "q=ff_" + id;
		stockHandicapDataStr = HttpUtils.sendGet(url, null, null);
		if (stockHandicapDataStr == null)
		{
			return;
		}
		stockHandicapDatas = stockHandicapDataStr.split("=")[1].replaceAll("[\";]", "").split("~");
		if (stockHandicapDatas.length != 18)
		{
			return;
		}
		stockHandicapData.setMainFlowIn(Float.valueOf(stockHandicapDatas[1]));
		stockHandicapData.setMainFlowOut(Float.valueOf(stockHandicapDatas[2]));
		stockHandicapData.setNetMainFlowIn(Float.valueOf(stockHandicapDatas[3]));
		stockHandicapData.setNetMainFlowInRatio(Float.valueOf(stockHandicapDatas[4]));
		stockHandicapData.setRetailFlowIn(Float.valueOf(stockHandicapDatas[5]));
		stockHandicapData.setRetailFlowOut(Float.valueOf(stockHandicapDatas[6]));
		stockHandicapData.setNetRetailFlowIn(Float.valueOf(stockHandicapDatas[7]));
		stockHandicapData.setNetRetailFlowInRatio(Float.valueOf(stockHandicapDatas[8]));
		
		_stockHandicapData = stockHandicapData;
	}
	
	private void updateStockDataToDB()
	{
		if(_stockData == null)
		{
			return;
		}
		_lastStockDataRawData = new StockDataRawData(this);
		Connection conn = null;
		try 
		{
			conn = DataServer.connectionPool.getConnection();
			DBUtils.insertTable(conn, _lastStockDataRawData);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
			{
				DataServer.connectionPool.returnConnection(conn);
			}
		}
	}
	
	public void flushStockData()
	{
		if (_stockId == null)
		{
			System.out.println("stockId is null");
			return;
		}
		//Get StockData
		String id = _stockId.getId();
		queryStockDataList(id);		
		queryStockHandicapData(id);
		if (_shouldUpdateDB)
		{
			updateStockDataToDB();
			_shouldUpdateDB = false;
		}
	}
	
	public Future excuteFlushStockData()
	{
		QueryStockDataTask thread = new QueryStockDataTask();
		return DataServer.stockQueryPool.submit(thread);
	}
	
	public static Calendar getNextDay(Calendar cal)
	{
		if (cal == null)
		{
			return null;
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		{
			cal.add(Calendar.DATE, 1 * 7);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		}
		else
		{
			cal.add(Calendar.DATE, 1);
		}
		return cal;
	}
	
	public boolean checkStockId()
	{
		queryStockDataList(this.getId());
		if(_stockData == null)
		{
			return false;
		}
		return true;
	}
		
	public static long calculateAggregateTimestamp(long lastAggregateTimestamp, long interval)
	{
		Date date = new Date(lastAggregateTimestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String targetDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		
		if (interval == StockParameter.INTERVAL_DAY)
		{
			calendar = getNextDay(calendar);
			return calendar.getTimeInMillis();
		}
		
		try
		{
			while(interval > 0)
			{
				for(int i = 0; i < 2; i++)
				{
					long startTime = df.parse(targetDay + ' ' + StockParameter.STOCK_TIME[i * 2]).getTime();
					if (lastAggregateTimestamp < startTime)
					{
						lastAggregateTimestamp = startTime;
					}
					long endTime = df.parse(targetDay + ' ' + StockParameter.STOCK_TIME[i * 2 + 1]).getTime();
					if (lastAggregateTimestamp < endTime)
					{
						if (lastAggregateTimestamp + interval <= endTime)
						{
							return lastAggregateTimestamp + interval;
						}
						else
						{
							interval -= endTime - lastAggregateTimestamp;
							lastAggregateTimestamp = endTime;
						}
					}
				}
				calendar = getNextDay(calendar);
				targetDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			}
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public void aggregateStockDataAndInsertToDB()
	{
		
		Date now = new Date();
		long timestamp = now.getTime() - StockParameter.INTERVAL_DELAY;
		Connection conn = null;
		try
		{
			conn = DataServer.connectionPool.getConnection();
			for(String key : StockParameter.STOCK_AGGREGATE_LIST)
			{
				long lastAggregateTimestamp = _aggregateTimestampMap.get(key);
				long nextAggregateTimestamp = _nextAggregateTimestampMap.get(key);
				if (timestamp > nextAggregateTimestamp)
				{
					_aggregateTimestampMap.put(key, timestamp);
					_nextAggregateTimestampMap.put(key, calculateAggregateTimestamp(timestamp, _intervalMap.get(key)));
					StockDataDBQueryBean stockDataDBQueryBean = new StockDataDBQueryBean(_readTableNameMap.get(key), lastAggregateTimestamp, timestamp - lastAggregateTimestamp);
					List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockDataDBQueryBean);
					StockDataType stockDataType = null;
					if (_readTableNameMap.get(key).indexOf("RawData") > -1)
					{
						stockDataType = stockDataDBQueryBean.aggragateDBData(stockDataTypeLists);
					}
					else
					{
						stockDataType = stockDataDBQueryBean.aggragateDBData1(stockDataTypeLists);
					}
					if (stockDataType instanceof StockDataOutput)
					{
						StockDataOutput stockDataOutput = (StockDataOutput)stockDataType;
						String tableName = this.getId() + "_" + key;
						StockDataDBInsertBean stockDataDBInsertBean = new StockDataDBInsertBean(stockDataOutput, tableName);
						DBUtils.insertTable(conn, stockDataDBInsertBean);
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null)
			{
				DataServer.connectionPool.returnConnection(conn);
			}
		}
	}
	
	public Future excuteAggregateStockData()
	{
		AggregateStockDataTask thread = new AggregateStockDataTask();
		return DataServer.stockAggregatePool.submit(thread);
	}
	
	public String getId()
	{
		if(_stockId != null)
		{
			return _stockId.getId();
		}
		return null;
	}
	
	public String getDate()
	{
		if(_stockData != null)
		{
			return _stockData.getDate();
		}
		return null;
	}
	
	public String getTime()
	{
		if(_stockData != null)
		{
			return _stockData.getTime();
		}
		return null;
	}
	
	public String getName()
	{
		if (_stockData != null)
		{
			return _stockData.getName();
		}
		return null;
	}
	
	public float getLaskEndPrice()
	{
		if (_lastStockDataRawData != null)
		{
			return _lastStockDataRawData.getEndPrice();
		}
		return 0;
	}

	class QueryStockDataTask implements Runnable 
	{
		
		public QueryStockDataTask()
		{

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try
			{
				flushStockData();
			}
			catch (Exception e)
			{
				System.out.println("flushStockData for " + _stockId.getId() + ". Error msg is " + e.getMessage());
			}
		}
	}
	
	class AggregateStockDataTask implements Runnable
	{
		public AggregateStockDataTask()
		{

		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			aggregateStockDataAndInsertToDB();
		}
		
	}
	
	public static void main(String[] args)
	{
//		long timestamp = 1457334000000L;
//		Date tmp = new Date(timestamp);
//		System.out.println(tmp.toString());
//		long ret = Stock.calculateAggregateTimestamp(timestamp, 5 * 60 * 1000);
//		Date date = new Date(ret);
//		System.out.println(date.toString());

	}
}
