package StockMarket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import StockApp.*;
import StockSchemaClass.*;
import StockBasic.*;
import StockAppUtils.*;
import StockData.*;

public class StockMarket extends StockType {
	
	protected StockMarketInfo _stockMarketInfo = null;	
	protected Map<String, Long> _aggregateTimestampMap = null;
	protected Map<String, Long> _nextAggregateTimestampMap = null;
	protected Map<String, Long> _intervalMap = null;
	protected Map<String, String> _readTableNameMap = null;
	
	protected long _lastStockMarketDataTimestamp = 0;
	protected boolean _shouldUpdateDB = true;
	protected StockMarketId _stockMarketId = null;
	protected StockMarketDataRawData _stockMarketDataRawData = null;
	
	public StockMarket(StockMarketId stockMarketId)
	{
		this._stockMarketId = stockMarketId;		
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
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_1MIN, Stock.calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_1MIN), StockParameter.INTERVAL_1MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_5MIN, Stock.calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_5MIN), StockParameter.INTERVAL_5MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_15MIN, Stock.calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_15MIN), StockParameter.INTERVAL_15MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_60MIN, Stock.calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_60MIN), StockParameter.INTERVAL_60MIN));
		_nextAggregateTimestampMap.put(StockParameter.STOCK_AGGREGATE_DAY, Stock.calculateAggregateTimestamp(_aggregateTimestampMap.get(StockParameter.STOCK_AGGREGATE_DAY), StockParameter.INTERVAL_DAY));
		
		_readTableNameMap = new HashMap<String, String>();
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_1MIN, this.getMarketName() + "_RawData");
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_5MIN, this.getMarketName() + "_" + StockParameter.STOCK_AGGREGATE_1MIN);
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_15MIN, this.getMarketName() + "_" + StockParameter.STOCK_AGGREGATE_5MIN);
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_60MIN, this.getMarketName() + "_" + StockParameter.STOCK_AGGREGATE_15MIN);
		_readTableNameMap.put(StockParameter.STOCK_AGGREGATE_DAY, this.getMarketName() + "_" + StockParameter.STOCK_AGGREGATE_60MIN);
	}
	
	public StockMarketInfo queryStockMarketData(String data)
	{
		String stockMarketInfoStr = JsonUtils.getStockMarketInfo(data, getMarketName());
		StockDataType stockDataType = JsonUtils.getObjectFromJson(stockMarketInfoStr, StockMarketInfo.class);
		StockMarketInfo stockMarketInfo = null;
		if (stockDataType != null && stockDataType instanceof StockMarketInfo)
		{
			stockMarketInfo = (StockMarketInfo)stockDataType;
		}
		return stockMarketInfo;	
	}
	
	public void flushStockMarketInfo()
	{
		String data = Stock.queryStockData("sh600000");
		StockMarketInfo stockMarketInfo = queryStockMarketData(data);
		if (stockMarketInfo == null)
		{
			System.out.println("flushStockMarketInfo failed");
			return;
		}		
		long timestamp = JsonUtils.getTimestamp(data);
		if(timestamp != 0 && timestamp != _lastStockMarketDataTimestamp)
		{
			setStockMarketData(stockMarketInfo);
			this._shouldUpdateDB = true;
			this._lastStockMarketDataTimestamp = timestamp;
		}
		if (_shouldUpdateDB)
		{
			updateStockMarketDataToDB();
			_shouldUpdateDB = false;
		}
	}
	
	public void updateStockMarketDataToDB()
	{
		if(_stockMarketInfo == null)
		{
			return;
		}
		_stockMarketDataRawData = new StockMarketDataRawData(this);
		Connection conn = null;
		try
		{
			conn = DataServer.connectionPool.getConnection();
			DBUtils.insertTable(conn, _stockMarketDataRawData);
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
	
	public Future excuteFlushStockMarketInfo()
	{
		QueryStockMarketDataTask thread = new QueryStockMarketDataTask();
		return DataServer.stockQueryPool.submit(thread);
	}
	
	public void aggregateStockMarketDataAndInsertToDB()
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
				if (timestamp >= nextAggregateTimestamp)
				{
					_aggregateTimestampMap.put(key, timestamp);
					_nextAggregateTimestampMap.put(key, Stock.calculateAggregateTimestamp(timestamp, _intervalMap.get(key)));
					StockMarketDBQueryBean stockMarketDBQueryBean = new StockMarketDBQueryBean(_readTableNameMap.get(key), lastAggregateTimestamp, timestamp - lastAggregateTimestamp);
					List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockMarketDBQueryBean);
					StockDataType stockDataType = null;
					if (_readTableNameMap.get(key).indexOf("RawData") > -1)
					{
						stockDataType = stockMarketDBQueryBean.aggragateDBData(stockDataTypeLists);
					}
					else
					{
						stockDataType = stockMarketDBQueryBean.aggragateDBData1(stockDataTypeLists);
					}
					if (stockDataType instanceof StockMarketOutput)
					{
						StockMarketOutput stockMarketOutput = (StockMarketOutput)stockDataType;
						String tableName = this.getMarketName() + "_" + key;
						StockMarketDBInsertBean stockMarketDBInsertBean = new StockMarketDBInsertBean(stockMarketOutput, tableName);
						DBUtils.insertTable(conn, stockMarketDBInsertBean);
					}
				}
			}
		}
		catch (Exception e)
		{
			
		}
		finally
		{
			if(conn != null)
			{
				DataServer.connectionPool.returnConnection(conn);
			}
		}
	}
	
	public Future excuteAggregateStockMarketData()
	{
		AggregateStockMarketDataTask thread = new AggregateStockMarketDataTask();
		return DataServer.stockAggregatePool.submit(thread);
	}
	
	public void setStockMarketId(StockMarketId stockMarketId)
	{
		this._stockMarketId = stockMarketId;
	}
	
	public StockMarketId getStockMarketId()
	{
		return this._stockMarketId;
	}
	
	public void setStockMarketData(StockMarketInfo stockMarketInfo)
	{
		this._stockMarketInfo = stockMarketInfo;
	}
	
	public StockMarketInfo getStockMarketData()
	{
		return this._stockMarketInfo;
	}
	
	public float getLastEndDot()
	{
		if (_stockMarketDataRawData != null)
		{
			return _stockMarketDataRawData.getEndDot();
		}
		return 0;
	}
	
	public String getMarketName()
	{
		if(_stockMarketId != null)
		{
			return _stockMarketId.getMarketName();
		}
		return null;
	}
	
	public long getTimeStamp()
	{
		return this._lastStockMarketDataTimestamp;
	}
		
	public float getCurdot()
	{
		if(_stockMarketInfo != null)
		{
			return _stockMarketInfo.getCurdot();
		}
		return -1;
	}
	
	public float getCurprice()
	{
		if(_stockMarketInfo != null)
		{
			return _stockMarketInfo.getCurprice();
		}
		return -1;
	}
	
	public float getRate()
	{
		if(_stockMarketInfo != null)
		{
			return _stockMarketInfo.getRate();
		}
		return -1;
	}
	
	public float getDealnumber()
	{
		if(_stockMarketInfo != null)
		{
			return _stockMarketInfo.getDealnumber();
		}
		return -1;
	}
	
	public float getTurnover()
	{
		if(_stockMarketInfo != null)
		{
			return _stockMarketInfo.getTurnover();
		}
		return -1;
	}
		
	class QueryStockMarketDataTask implements Runnable 
	{
		
		public QueryStockMarketDataTask()
		{

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try
			{
				flushStockMarketInfo();
			}
			catch (Exception e)
			{
				System.out.println("flushStockMarketInfo for " + _stockMarketInfo.getName() + ". Error msg is " + e.getMessage());
			}
		}
	}
	
	class AggregateStockMarketDataTask implements Runnable
	{
		public AggregateStockMarketDataTask()
		{

		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			aggregateStockMarketDataAndInsertToDB();
		}
		
	}
	
}
