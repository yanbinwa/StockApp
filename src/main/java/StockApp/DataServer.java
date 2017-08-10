package StockApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.json.JSONObject;

import StockSchemaClass.*;
import StockData.*;
import StockMarket.*;
import StockAppUtils.*;


public class DataServer {
	
	protected Map<String, Stock> _stockMap = null;
	protected Map<String, Stock> _stockMap_copy = null;
	protected Map<String, StockMarket> _stockMarketMap = null;
	protected Map<String, StockMarket> _stockMarketMap_copy = null;
	protected static DataServer _instance = null;
	protected static int queryThreadNum = 20;
	public static ExecutorService stockQueryPool = Executors.newFixedThreadPool(queryThreadNum);
	public static ExecutorService stockAggregatePool = Executors.newFixedThreadPool(queryThreadNum);
	public static ConnectionPool connectionPool = new ConnectionPool();
	
	private DataServer() 
	{
		_stockMap = new HashMap<String, Stock>();
		_stockMap_copy = new HashMap<String, Stock>();
		_stockMarketMap = new HashMap<String, StockMarket>();
		_stockMarketMap_copy = new HashMap<String, StockMarket>();
		init();
	}

	private void init()
	{

		String data = FileUtils.read(StockParameter.STOCK_ID_FILE);
		try
		{
			_stockMap_copy.clear();
			String StockIdStr = JsonUtils.getStockId(data);
			List<StockDataType> stockDataTypeList = (ArrayList<StockDataType>) JsonUtils.getObjectListFromJson(StockIdStr, StockId.class);
			for(StockDataType stockDataType : stockDataTypeList)
			{
				if (stockDataType instanceof StockId)
				{
					StockId StockId = (StockId) stockDataType;
					Stock stock = new Stock(StockId);
					_stockMap_copy.put(stock.getId(), stock);
				}				
			}
			
			_stockMarketMap_copy.clear();
			String StockMarketIdStr = JsonUtils.getStockMarketId(data);
			stockDataTypeList = (ArrayList<StockDataType>) JsonUtils.getObjectListFromJson(StockMarketIdStr, StockMarketId.class);
			for(StockDataType stockDataType : stockDataTypeList)
			{
				if (stockDataType instanceof StockMarketId)
				{
					StockMarketId StockMarketId = (StockMarketId) stockDataType;
					StockMarket stockMarket = new StockMarket(StockMarketId);
					_stockMarketMap_copy.put(stockMarket.getMarketName(), stockMarket);
				}				
			}
			updateStock();
			QueryStockDataAndInsertToDB queryStockDataAndInsertToDB = new QueryStockDataAndInsertToDB();
			Thread queryTask = new Thread(queryStockDataAndInsertToDB);
			queryTask.start();
			AggerateDBData aggerateDBData = new AggerateDBData();
			Thread aggerateTask = new Thread(aggerateDBData);
			aggerateTask.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}

	public static DataServer instance()
	{
		if(_instance == null)
		{
			_instance = new DataServer();
		}
		return _instance;
	}
	
	public void updateStock()
	{
		synchronized(_stockMap)
		{
			_stockMap.clear();
			_stockMap.putAll(_stockMap_copy);
		}
		synchronized(_stockMarketMap)
		{
			_stockMarketMap.clear();
			_stockMarketMap.putAll(_stockMarketMap_copy);
		}
	}
	
	public void saveStockFile()
	{
		List<StockId> stockIds= new ArrayList<StockId>();
		synchronized(_stockMap)
		{
			for(Map.Entry<String, Stock> stockEntry : _stockMap.entrySet())
			{
				stockIds.add(stockEntry.getValue().getStockId());
			}
		}
		List<StockMarketId> stockMarketIds= new ArrayList<StockMarketId>();
		synchronized(_stockMarketMap)
		{
			for(Map.Entry<String, StockMarket> stockMarketEntry : _stockMarketMap.entrySet())
			{
				stockMarketIds.add(stockMarketEntry.getValue().getStockMarketId());
			}
		}
		JSONObject obj = new JSONObject();
        obj.put("stockInfo", stockIds);
        obj.put("stockMarketInfo", stockMarketIds);
		FileUtils.write(StockParameter.STOCK_TEST_FILE, obj.toString());
	}
	
	public void addStockId(String id)
	{
		boolean isUpdated = false;
		synchronized(_stockMap_copy)
		{
			if(!_stockMap_copy.containsKey(id))
			{
				StockId stockId = new StockId();
				stockId.setId(id);
				Stock stock = new Stock(stockId);
				if(stock.checkStockId())
				{
					_stockMap_copy.put(id, stock);
					isUpdated = true;
				}
			}
		}
		if(isUpdated)
		{
			updateStock();
			saveStockFile();
		}
	}
	
	public void deleteStockId(String id)
	{
		boolean isUpdated = false;
		synchronized(_stockMap_copy)
		{
			if(_stockMap_copy.containsKey(id))
			{
				_stockMap_copy.remove(id);
				isUpdated = true;
			}
		}
		if(isUpdated)
		{
			updateStock();
			saveStockFile();
		}
	}
	
	public void addStockMarketId(String marketName)
	{
		boolean isUpdated = false;
		synchronized(_stockMarketMap_copy)
		{
			if(!_stockMarketMap.containsKey(marketName))
			{
				StockMarketId stockMarketId = new StockMarketId();
				stockMarketId.setMarketName(marketName);
				StockMarket stockMarket = new StockMarket(stockMarketId);
				_stockMarketMap.put(marketName, stockMarket);
				isUpdated = true;
			}
		}
		if(isUpdated)
		{
			updateStock();
			saveStockFile();
		}
	}
	
	public List<String> getAllStockId()
	{
		List<String> stockIds= new ArrayList<String>();
		synchronized(_stockMap_copy)
		{
			for(Map.Entry<String, Stock> stockEntry : _stockMap_copy.entrySet())
			{
				stockIds.add(stockEntry.getValue().getId() + ": " + stockEntry.getValue().getName());
			}
		}
		return stockIds;
	}
	
	public List<String> getAllStockMarketId()
	{
		List<String> stockMarketIds= new ArrayList<String>();
		synchronized(_stockMarketMap_copy)
		{
			for(Map.Entry<String, StockMarket> stockMarketEntry : _stockMarketMap_copy.entrySet())
			{
				stockMarketIds.add(stockMarketEntry.getValue().getMarketName());
			}
		}
		return stockMarketIds;
	}
	
	public String getStockName(String id)
	{
		synchronized(_stockMap_copy)
		{
			return _stockMap_copy.get(id).getName();
		}
	}
	
	
	public String getStockMarketName(String id)
	{
		synchronized(_stockMarketMap_copy)
		{
			return _stockMarketMap_copy.get(id).getMarketName();
		}
	}
	
	public static long checkStockTime()
	{
		//股市开盘时间为9：00 - 11：30， 1：00 - 3：00，周六周日停牌
		Calendar cal = Calendar.getInstance();
		String targetDay = null;
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		{
			cal.add(Calendar.DATE, 1 * 7);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			targetDay = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		else
		{
			targetDay = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long now = new Date().getTime() / 1000;
		try
		{
			for (int i = 0; i < 2; i ++)
			{
				long startTime = df.parse(targetDay + ' ' + StockParameter.STOCK_TIME[i * 2]).getTime() / 1000;
				if(now < startTime)
				{
					return startTime - now;
				}
				long endTime = df.parse(targetDay + ' ' + StockParameter.STOCK_TIME[i * 2 + 1]).getTime() / 1000;
				if(now < endTime)
				{
					return 0;
				}
				if (i == 1)
				{
					if(now >= endTime)
					{
						return 60 * 60 * 18;
					}
				}
			}
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果为0，则需要进行query操作
		return 0;
	}
	
	
	class QueryStockDataAndInsertToDB implements Runnable
	{
		QueryStockDataAndInsertToDB()
		{
			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(true)
			{	
				try 
				{
					//这里先计算是否处于开盘时间，如果是一天结束，会生成
					long nextInterval = checkStockTime() / 2;
					if (nextInterval != 0)
					{
						Thread.sleep(1000 * nextInterval);
						continue;
					}
					
					List<Future> futures = null;
					synchronized(_stockMap)
					{
						futures = new ArrayList<Future>();
						for(Stock stock : _stockMap.values())
						{
							futures.add(stock.excuteFlushStockData());
						}
					}
					synchronized(_stockMarketMap)
					{
						futures = new ArrayList<Future>();
						for(StockMarket stockMarket : _stockMarketMap.values())
						{
							futures.add(stockMarket.excuteFlushStockMarketInfo());
						}
					}
					
					for(Future<List<StockDataType>> future : futures)
					{
						future.get();
					}
					
					Thread.sleep(1000 * 5);
				}
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (ExecutionException e) 
				{
					// TODO Auto-generated catch block
					System.out.println("Error");
					e.printStackTrace();
				}
			}
		}
	}
	
	//这里再起一个Class线程，用来汇聚Stock Data
	class AggerateDBData implements Runnable
	{
		AggerateDBData()
		{
			
		}
		
		@Override
		public void run() {
			// Every 
			while(true)
			{
				try
				{
					long nextInterval = checkStockTime() / 2;
					if (nextInterval != 0)
					{
						Thread.sleep(1000 * nextInterval);
						continue;
					}
					
					List<Future> futures = null;
					synchronized(_stockMap)
					{
						futures = new ArrayList<Future>();
						for(Stock stock : _stockMap.values())
						{
							futures.add(stock.excuteAggregateStockData());
						}
					}
					synchronized(_stockMarketMap)
					{
						futures = new ArrayList<Future>();
						for(StockMarket stockMarket : _stockMarketMap.values())
						{
							futures.add(stockMarket.excuteAggregateStockMarketData());
						}
					}
					
					for(Future<List<StockDataType>> future : futures)
					{
						future.get();
					}
					
					Thread.sleep(1000 * 60);
				}
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (ExecutionException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
	
	public static void main(String args[])
	{
		DataServer server = new DataServer();
	}
}