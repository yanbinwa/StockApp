package StockData;

import StockSchemaClass.*;
import StockBasic.*;
import StockAppUtils.*;

public class StockDataRawData extends DBTableType{
	
	protected long _timestamp = 0;
	protected String _date = null;
	protected String _time = null;
	protected float _currentPrice = 0;
	protected float _increase = 0;
	protected float _competitivePrice = 0;
	protected float _auctionPrice = 0;
	protected int _totalNumber = 0;
	protected float _turnover = 0;
	protected float _hprice = 0;
	protected float _lprice = 0;
	protected float _startPrice = 0;
	protected float _endPrice = 0;
	protected int _outSize = 0;
    protected int _inSize = 0;
    protected float _exchangeRatio = 0;
    protected float _priceEaringRatio = 0;
    protected float _priceToBookRatio = 0;
    protected float _swing = 0;
    protected float _circulatedMarket = 0;
    protected float _totalMarket = 0;
    protected float _mainFlowIn = 0;
    protected float _mainFlowOut = 0;
    protected float _netMainFlowIn = 0;
    protected float _netMainFlowInRatio = 0;
    protected float _retailFlowIn = 0;
    protected float _retailFlowOut = 0;
    protected float _netRetailFlowIn = 0;
    protected float _netretailFlowInRatio = 0;
	
	public StockDataRawData(Stock stock)
	{
		StockData stockData = stock.getStockData();
		_date = stockData.getDate();
		_time = stockData.getTime();
		_currentPrice = stockData.getCurrentPrice();
		_increase = stockData.getIncrease();
		_competitivePrice = stockData.getCompetitivePrice();
		_auctionPrice = stockData.getAuctionPrice();
		_totalNumber = stockData.getTotalNumber();
		_turnover = stockData.getTurnover();
		_hprice = stockData.getHprice();
		_lprice = stockData.getLprice();
		if (stock.getLaskEndPrice() > 0)
		{
			_startPrice = stock.getLaskEndPrice();
		}
		else
		{
			_startPrice = _currentPrice;
		}
		_endPrice = _currentPrice;
		
		StockHandicapData stockHandicapData = stock.getStockHandicapData();
		_outSize = stockHandicapData.getOutSize();
	    _inSize = stockHandicapData.getInSize();
	    _exchangeRatio = stockHandicapData.getExchangeRatio();
	    _priceEaringRatio = stockHandicapData.getPriceEaringRatio();
	    _priceToBookRatio = stockHandicapData.getPriceToBookRatio();
	    _swing = stockHandicapData.getSwing();
	    _circulatedMarket = stockHandicapData.getCirculatedMarket();
	    _totalMarket = stockHandicapData.getTotalMarket();
	    _mainFlowIn = stockHandicapData.getMainFlowIn();
	    _mainFlowOut = stockHandicapData.getMainFlowOut();
	    _netMainFlowIn = stockHandicapData.getNetMainFlowIn();
	    _netMainFlowInRatio = stockHandicapData.getNetMainFlowInRatio();
	    _retailFlowIn = stockHandicapData.getRetailFlowIn();
	    _retailFlowOut = stockHandicapData.getRetailFlowOut();
	    _netRetailFlowIn = stockHandicapData.getNetRetailFlowIn();
	    _netretailFlowInRatio = stockHandicapData.getNetRetailFlowInRatio();
		generateTimestamp();
		
		this.tableName = stock.getId() + "_RawData";
	}
	
	public StockDataRawData()
	{
		
	}
	
	public void generateTimestamp()
	{
		long timestamp = DBUtils.getTimestamp(_date, _time);
		if (timestamp != 0)
		{
			_timestamp = timestamp;
		}
	}
	
	public float getEndPrice()
	{
		return _endPrice;
	}
	
	public String getCreateTableExcuteCommand()
	{
		 String excuteCommand = "CREATE TABLE " + this.tableName + 
				 " (" +
				 "Timestamp BIGINT(20) DEFAULT 0, " + 
				 "Date DATE DEFAULT NULL, " +
				 "Time TIME DEFAULT NULL, " +
				 "CurrentPrice FLOAT DEFAULT 0, " + 
				 "StartPrice FLOAT DEFAULT 0, " + 
				 "EndPrice FLOAT DEFAULT 0, " + 
				 "Increase FLOAT DEFAULT 0, " + 
				 "CompetitivePrice FLOAT DEFAULT 0, " +
				 "AuctionPrice FLOAT DEFAULT 0, " +
				 "TotalNumber BIGINT(20) DEFAULT 0, " +
				 "Turnover FLOAT DEFAULT 0, " +
				 "Hprice FLOAT DEFAULT 0, " +
				 "Lprice FLOAT DEFAULT 0, " +
				 "OutSize BIGINT(20) DEFAULT 0, " +
				 "InSize BIGINT(20) DEFAULT 0, " +
				 "ExchangeRatio FLOAT DEFAULT 0, " +
				 "PriceEaringRatio FLOAT DEFAULT 0, " +
				 "PriceToBookRatio FLOAT DEFAULT 0, " +
				 "Swing FLOAT DEFAULT 0, " +
				 "CirculatedMarket FLOAT DEFAULT 0, " +
				 "TotalMarket FLOAT DEFAULT 0, " +
				 "MainFlowIn FLOAT DEFAULT 0, " +
				 "MainFlowOut FLOAT DEFAULT 0, " +
				 "NetMainFlowIn FLOAT DEFAULT 0, " +
				 "NetMainFlowInRatio FLOAT DEFAULT 0, " +
				 "RetailFlowIn FLOAT DEFAULT 0, " +
				 "RetailFlowOut FLOAT DEFAULT 0, " +
				 "NetRetailFlowIn FLOAT DEFAULT 0, " +
				 "NetretailFlowInRatio FLOAT DEFAULT 0, " +
				 "PRIMARY KEY(Timestamp)" +
				 ")";
		 System.out.println(excuteCommand);
		 return excuteCommand;
	}
	
	public String getInsertDataExcuteCommand()
	{
		String parameter = String.format("%d, \"%s\", \"%s\", %2f, %2f, %2f, %2f, %2f, "
				+ "%2f, %d, %2f, %2f, %2f, "
				+ "%d, %d, %2f, %2f, %2f, "
				+ "%2f, %2f, %2f, %2f, %2f, "
				+ "%2f, %2f, %2f, %2f, %2f, %2f",
				_timestamp, _date, _time, _currentPrice, _startPrice, _endPrice, _increase, _competitivePrice, 
				_auctionPrice, _totalNumber, _turnover, _hprice, _lprice,
				_outSize, _inSize, _exchangeRatio, _priceEaringRatio, _priceToBookRatio, 
				_swing, _circulatedMarket, _totalMarket, _mainFlowIn, _mainFlowOut, 
				_netMainFlowIn, _netMainFlowInRatio, _retailFlowIn, _retailFlowOut, _netRetailFlowIn, _netretailFlowInRatio);
		String excuteCommand = "INSERT INTO " + this.tableName +
				" (" +
				"Timestamp, Date, Time, CurrentPrice, StartPrice, EndPrice, Increase, CompetitivePrice, AuctionPrice, TotalNumber, Turnover, Hprice, Lprice, " + 
				"OutSize, InSize, ExchangeRatio, PriceEaringRatio, PriceToBookRatio, Swing, CirculatedMarket, TotalMarket, " +
				"MainFlowIn, MainFlowOut, NetMainFlowIn, NetMainFlowInRatio, RetailFlowIn, RetailFlowOut, NetRetailFlowIn, NetretailFlowInRatio" +
				") VALUES " +
				"(" +
				parameter +
				")";
		System.out.println(excuteCommand);
		return excuteCommand;
	}
	
}
