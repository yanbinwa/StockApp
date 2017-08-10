package StockAppUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//将json数据写入到文件中，或将json文件读出，这里是记录stockID到本地磁盘中

public class FileUtils {
	
	public static String read(String fileName)
	{
		BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    if(fileName == null)
	    {
	    	return null;
	    }
	    try
	    {
	    	reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	    	String strRead = null;
	    	while ((strRead = reader.readLine()) != null) {
	    		sbf.append(strRead);
	    	}
	    	result = sbf.toString();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
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
	
	public static void write(String fileName, String context)
	{
		OutputStreamWriter fos = null;
		try
		{
			fos = new OutputStreamWriter(new FileOutputStream(new File(fileName)));
			fos.write(context);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
	}
}
