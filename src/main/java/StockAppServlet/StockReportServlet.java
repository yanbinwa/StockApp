package StockAppServlet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import StockApp.DataServer;
import StockSchemaClass.*;
import StockAppUtils.*;
import StockData.*;
import StockMarket.*;

@RestController
public class StockReportServlet 
{
	
    @RequestMapping("/report")
    public String queryStockData(@RequestParam(value="query", defaultValue="World") String query) 
    {
        StockDataType stockDataType = JsonUtils.getObjectFromJson(query, StockQuery.class);
        String ret = null;
        try
        {
	    	if (stockDataType instanceof StockQuery)
	    	{
	    		StockQuery stockQuery = (StockQuery)stockDataType;
	    		Map<String, Object> stockQueryResultMap = getQueryResult(stockQuery);
	    		ret = JsonUtils.getJsonString(stockQueryResultMap);
	    	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return "error";
        }
        return ret;
    }
    
    public Map<String, Object> getQueryResult(StockQuery stockQuery) 
    {
    	List<StockQueryResult> stockQueryResultList = null;
    	String stockTypeName = null;
    	String type = stockQuery.getType().replace("\n", "");
    	if (type.equals("stock"))
    	{
    		stockQueryResultList = getStockDataQueryResult(stockQuery);
    		stockTypeName = DataServer.instance().getStockName(stockQuery.getId());
    	}
    	else if (type.equals("market"))
    	{
    		stockQueryResultList = getStockMarketQueryResult(stockQuery);
    		stockTypeName = DataServer.instance().getStockMarketName(stockQuery.getId());
    	}
    	Map<String, Object> stockTypeDataMap = new HashMap<String, Object>();
    	stockTypeDataMap.put("name", stockTypeName);
    	stockTypeDataMap.put("data", stockQueryResultList);
    	return stockTypeDataMap;
    }
    
    public List<StockQueryResult> getStockDataQueryResult(StockQuery stockQuery) 
    {
    	List<StockQueryResult> stockQueryResultList = new ArrayList<StockQueryResult>();
    	String readTableName = stockQuery.getId() + "_" + stockQuery.getInterval();
    	StockDataDBQueryBean stockDataDBQueryBean = new StockDataDBQueryBean(readTableName, stockQuery.getStartTimestamp(), stockQuery.getEndTimestamp() - stockQuery.getStartTimestamp());
    	Connection conn = null;
    	try
    	{
    		conn = DataServer.connectionPool.getConnection();
	    	List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockDataDBQueryBean);
			for (StockDataType stockDataType : stockDataTypeLists)
			{
				if(stockDataType instanceof StockDataOutput)
				{
					StockDataOutput stockDataOutput = (StockDataOutput) stockDataType;
					StockQueryResult StockQueryResult = new StockQueryResult();
					StockQueryResult.setStartPrice(stockDataOutput.getStartPrice());
					StockQueryResult.setEndPrice(stockDataOutput.getEndPrice());
					StockQueryResult.setHprice(stockDataOutput.getHprice());
					StockQueryResult.setLprice(stockDataOutput.getLprice());
					StockQueryResult.setTimestamp(stockDataOutput.getTimestamp());
					StockQueryResult.setTotalNumber(stockDataOutput.getTotalNumber());
					StockQueryResult.setMainFlowIn(stockDataOutput.getMainFlowIn());
					StockQueryResult.setMainFlowOut(stockDataOutput.getMainFlowOut());
					StockQueryResult.setRetailFlowIn(stockDataOutput.getRetailFlowIn());
					StockQueryResult.setRetailFlowOut(stockDataOutput.getRetailFlowOut());
					StockQueryResult.setOutSize(stockDataOutput.getOutSize());
					StockQueryResult.setInSize(stockDataOutput.getInSize());
					stockQueryResultList.add(StockQueryResult);
				}
			}
    	}
    	catch(Exception e)
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
    	return stockQueryResultList;
    }
    
    public List<StockQueryResult> getStockMarketQueryResult(StockQuery stockQuery) 
    {
    	List<StockQueryResult> stockQueryResultList = new ArrayList<StockQueryResult>();
    	String readTableName = stockQuery.getId() + "_" + stockQuery.getInterval();
    	StockMarketDBQueryBean stockMarketDBQueryBean = new StockMarketDBQueryBean(readTableName, stockQuery.getStartTimestamp(), stockQuery.getEndTimestamp() - stockQuery.getStartTimestamp());
    	Connection conn = null;
    	try
    	{
    		conn = DataServer.connectionPool.getConnection();
	    	List<StockDataType> stockDataTypeLists = DBUtils.readTable(conn, stockMarketDBQueryBean);
			for (StockDataType stockDataType : stockDataTypeLists)
			{
				if(stockDataType instanceof StockMarketOutput)
				{
					StockMarketOutput stockDataOutput = (StockMarketOutput) stockDataType;
					StockQueryResult StockQueryResult = new StockQueryResult();
					StockQueryResult.setStartPrice(stockDataOutput.getStartDot());
					StockQueryResult.setEndPrice(stockDataOutput.getEndDot());
					StockQueryResult.setHprice(stockDataOutput.getHdot());
					StockQueryResult.setLprice(stockDataOutput.getLdot());
					StockQueryResult.setTimestamp(stockDataOutput.getTimestamp());
					StockQueryResult.setTotalNumber(stockDataOutput.getDealNumber());
					stockQueryResultList.add(StockQueryResult);
				}
			}
    	}
    	catch(Exception e)
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
    	return stockQueryResultList;
    }
}