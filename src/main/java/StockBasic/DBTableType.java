package StockBasic;

import java.sql.ResultSet;
import java.util.List;
import StockSchemaClass.*;

public class DBTableType {
	
	protected String tableName = null;
		
	public DBTableType()
	{
		
	}
	
	public String getTableName()
	{
		return this.tableName;
	}
	
	public String getCreateTableExcuteCommand() 
	{
		return null;
	}
	
	public String getInsertDataExcuteCommand()
	{
		return null;
	}
	
	public String getReadDataExcuteCommand()
	{
		return null;
	}
	
	public List<StockDataType> parseDBData(ResultSet ret)
    {
    	return null;
    }
	
	public StockDataType aggragateDBData(List<StockDataType> stockDataTypeList)
    {
    	return null;
    }

}
