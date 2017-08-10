package StockAppUtils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
  
import StockSchemaClass.*;

public class JsonUtils
{  
	
    public static String getJsonString(String key, Object value)  
    {  
        JSONObject obj = new JSONObject();
        obj.put(key, value);
        return obj.toString();
    }    
    
    public static String getJsonString(Object obj) throws JsonProcessingException
    {  
    	ObjectMapper objectMapper = new ObjectMapper();
    	String ret = null;
    	ret = objectMapper.writeValueAsString(obj);
		return ret;
    }  
    
	public static List<StockDataType> getObjectListFromJson(String jsonStr, @SuppressWarnings("rawtypes") Class clazz)
    {
    	ArrayList<StockDataType> ret = new ArrayList<StockDataType>();
    	JSONArray array = JSONArray.fromObject(jsonStr);  
    	   
        for (int i = 0; i < array.length(); i++)
        {  
            JSONObject jsonObject = array.getJSONObject(i);  
            ret.add((StockDataType) JSONObject.toBean(jsonObject, clazz));
        }
    	return ret;
    }
	
	public static StockDataType getObjectFromJson(String jsonStr, @SuppressWarnings("rawtypes") Class clazz)
    {
		JSONObject jsonObject = JSONObject.fromString(jsonStr);
		return (StockDataType) JSONObject.toBean(jsonObject, clazz);
    }
    
    public static String getStockId(String jsonStr)
    {
    	JSONObject obj = JSONObject.fromString(jsonStr);
    	return obj.getString("stockInfo").toString();
    }
    
    public static String getStockMarketId(String jsonStr)
    {
    	JSONObject obj = JSONObject.fromString(jsonStr);
    	return obj.getString("stockMarketInfo").toString();
    }
    
    public static String getStockData(String jsonStr) throws JSONException
    {	
    	JSONObject obj = JSONObject.fromString(jsonStr);
    	String ret = obj.getJSONObject("retData").getString("stockinfo");	
    	ret = ret.replaceAll("OpenningPrice", "openningPrice");
    	ret = ret.replaceAll("hPrice", "hprice");
    	ret = ret.replaceAll("lPrice", "lprice");
    	return ret;
    }
    
    public static String getStockMarketInfo(String jsonStr, String name) throws JSONException
    {
    	JSONObject obj = JSONObject.fromString(jsonStr);
    	return obj.getJSONObject("retData").getJSONObject("market").getString(name);
    }
    
    public static long getTimestamp(String jsonStr)
    {
    	String data = getStockData(jsonStr);
    	List<StockDataType> stockDataTypeList = getObjectListFromJson(data, StockData.class);
    	StockDataType stockDataType = stockDataTypeList.get(0);
    	if(stockDataType instanceof StockData)
    	{
    		StockData stockData = (StockData)stockDataType;
    		String dataStr = stockData.getDate();
    		String timeStr = stockData.getTime();
    		long timestamp = DBUtils.getTimestamp(dataStr, timeStr);
    		return timestamp;
    	}
    	return 0;
    }
}