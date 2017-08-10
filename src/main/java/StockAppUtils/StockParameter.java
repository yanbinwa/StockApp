package StockAppUtils;

public class StockParameter {
	public static final String APIKEY = "ed92d027b517e18f12680c8a740256fa";
	public static final String STOCK_URL = "http://apis.baidu.com/apistore/stockservice/stock";
	public static final String STOCK_ID_FILE = "/Users/yanbinwa/Documents/workspace/StockApp/WebContent/WEB-INF/StockTest";
	public static final String STOCK_TEST_FILE = "/Users/yanbinwa/Documents/workspace/StockApp/WebContent/WEB-INF/StockTest";
	public static final String[] MARKET_NAMES = {"shanghai", "shenzhen"};
	public static final String STOCK_HANDICAP_URL = "http://qt.gtimg.cn/";
	
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/";
	public static final String DB_DATABASE = "stock";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "root";
	public static final String DB_TESTTABLE = "testTable";
	public static final int DB_INIT_CONNECTION = 10;
	public static final int DB_INC_CONNECTION = 5;
	public static final int DB_MAX_CONNECTION = 50;
	public static final String[] STOCK_TIME = {"09:30:00", "11:30:00", "13:00:00", "15:00:00"};
	
	public static final String STOCK_AGGREGATE_1MIN = "1MIN";
	public static final String STOCK_AGGREGATE_5MIN = "5MIN";
	public static final String STOCK_AGGREGATE_15MIN = "15MIN";
	public static final String STOCK_AGGREGATE_60MIN = "60MIN";
	public static final String STOCK_AGGREGATE_DAY = "DAY";
	public static final String[] STOCK_AGGREGATE_LIST = {STOCK_AGGREGATE_1MIN, STOCK_AGGREGATE_5MIN, STOCK_AGGREGATE_15MIN, STOCK_AGGREGATE_60MIN, STOCK_AGGREGATE_DAY};
	
	public static final long INTERVAL_1MIN = 60 * 1000;
	public static final long INTERVAL_5MIN = 5 * 60 * 1000;
	public static final long INTERVAL_15MIN = 15 * 60 * 1000;
	public static final long INTERVAL_60MIN = 60 * 60 * 1000;
	public static final long INTERVAL_DAY = 24 * 60 * 60 * 1000;
	
	public static final long INTERVAL_DELAY = 10 * 1000;
	
}
