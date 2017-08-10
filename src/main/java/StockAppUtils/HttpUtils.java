package StockAppUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtils 
{
	public static String sendGet(String httpUrl, Map<String, String> param, Map<String, String> header) 
	{
		BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    boolean flag = false;
	    if (param != null)
    	{
	    	httpUrl += "?";
    		for(Map.Entry<String, String> entry: param.entrySet())
    		{
    	    	if (flag)
    	    	{
    	    		httpUrl += "&";
    	    	}
    	    	else
    	    	{
    	    		flag = true;
    	    	}
    			httpUrl += entry.getKey() + "="  + entry.getValue();
    		}
    	}
	    try 
	    {
	    	URL url = new URL(httpUrl);
	    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    	connection.setRequestMethod("GET");
	    	if(header != null)
	    	{
		    	for(Map.Entry<String, String> entry : header.entrySet())
		    	{
		    		connection.setRequestProperty(entry.getKey(), entry.getValue());
		    	}
	    	}
	    	connection.connect();
	    	InputStream is = connection.getInputStream();
	    	reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	    	String strRead = null;
	    	while ((strRead = reader.readLine()) != null) {
	    		sbf.append(strRead);
	    		sbf.append("\r\n");
	    	}
	    	result = sbf.toString();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    	return result;
	}
	
	public static void main(String[] args)
	{
		String httpUrl = "http://qt.gtimg.cn/q=ff_sz000858";
		String str = HttpUtils.sendGet(httpUrl, null, null);
		String[] stockArray = str.split("=")[1].replaceAll("[\";]", "").split("~");
		System.out.println(stockArray.length);
		for(int i = 0; i < stockArray.length; i ++)
		{
			System.out.println(stockArray[i]);
		}
		System.out.println(str);
	}
}