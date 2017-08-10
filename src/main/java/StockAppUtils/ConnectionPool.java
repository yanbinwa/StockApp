package StockAppUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;

public class ConnectionPool {

	protected String jdbcDriver = StockParameter.DB_DRIVER; 
	protected String dbUrl = StockParameter.DB_URL + StockParameter.DB_DATABASE; 
	protected String dbUsername = StockParameter.DB_USERNAME; 
	protected String dbPassword = StockParameter.DB_PASSWORD;
	protected String testTable = StockParameter.DB_TESTTABLE;
	protected int initialConnections = StockParameter.DB_INIT_CONNECTION;
	protected int incrementalConnections = StockParameter.DB_INC_CONNECTION;
	protected int maxConnections = StockParameter.DB_MAX_CONNECTION;
	
	/** */
	private Vector connections = null;

	public ConnectionPool() 
	{	
		try 
		{
			createPool();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	public int getInitialConnections() 
	{
		return this.initialConnections;
	}

	public void setInitialConnections(int initialConnections) 
	{
		this.initialConnections = initialConnections;
	}

	public int getIncrementalConnections() 
	{
		return this.incrementalConnections;
	}

	public void setIncrementalConnections(int incrementalConnections) 
	{
		this.incrementalConnections = incrementalConnections;
	}

	public int getMaxConnections() 
	{
		return this.maxConnections;
	}

	public void setMaxConnections(int maxConnections) 
	{
		this.maxConnections = maxConnections;
	}

	public String getTestTable() 
	{
		return this.testTable;
	}

	public void setTestTable(String testTable) 
	{
		this.testTable = testTable;
	}

	public synchronized void createPool() throws Exception 
	{

		if (connections != null) 
		{
			return; 
		}

		Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
		DriverManager.registerDriver(driver); // 注册 JDBC 驱动程序

		connections = new Vector();
		createConnections(this.initialConnections);
		System.out.println("create connection pool successfully!");
	}

	private void createConnections(int numConnections) throws SQLException 
	{
		for (int x = 0; x < numConnections; x++) 
		{	
			if (this.maxConnections > 0
					&& this.connections.size() >= this.maxConnections) 
			{
				break;
			}
			try 
			{
				connections.addElement(new PooledConnection(newConnection()));
			} 
			catch (SQLException e) 
			{
				System.out.println("create connection failed " + e.getMessage());
				throw new SQLException();
			}
			System.out.println("create connecton successful");
		}
	}

	private Connection newConnection() throws SQLException
	{
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername,
				dbPassword);
		if (connections.size() == 0) 
		{
			DatabaseMetaData metaData = conn.getMetaData();
			int driverMaxConnections = metaData.getMaxConnections();
			if (driverMaxConnections > 0
					&& this.maxConnections > driverMaxConnections) 
			{
				this.maxConnections = driverMaxConnections;
			}

		}
		return conn; // 返回创建的新的数据库连接
	}

	public synchronized Connection getConnection() throws SQLException 
	{
		if (connections == null) {
			return null; // 连接池还没创建，则返回 null
		}
		Connection conn = getFreeConnection(); // 获得一个可用的数据库连接
		while (conn == null) {
			wait(250);
			conn = getFreeConnection(); // 重新再试，直到获得可用的连接，如果
		}
		return conn;// 返回获得的可用的连接
	}

	private Connection getFreeConnection() throws SQLException 
	{
		Connection conn = findFreeConnection();
		if (conn == null) 
		{
			createConnections(incrementalConnections);
			conn = findFreeConnection();
			if (conn == null) {
				return null;
			}
		}
		return conn;
	}

	private Connection findFreeConnection() throws SQLException 
	{
		Connection conn = null;
		PooledConnection pConn = null;
		Enumeration enumerate = connections.elements();
		while (enumerate.hasMoreElements()) 
		{
			pConn = (PooledConnection) enumerate.nextElement();
			if (!pConn.isBusy()) {
				conn = pConn.getConnection();
				pConn.setBusy(true);
				// 测试此连接是否可用
				if (!testConnection(conn)) 
				{
					try 
					{
						conn = newConnection();
					} 
					catch (SQLException e) 
					{
						System.out.println(" Create DB connection failed" + e.getMessage());
						return null;
					}
					pConn.setConnection(conn);
				}
				break; // 己经找到一个可用的连接，退出
			}
		}

		return conn;// 返回找到到的可用连接

	}

	private boolean testConnection(Connection conn) 
	{
		try 
		{
			if (testTable.equals("")) 
			{
				conn.setAutoCommit(true);
			} 
			else 
			{
				Statement stmt = conn.createStatement();
				stmt.execute("select count(*) from " + testTable);
			}
		}
		catch (SQLException e) 
		{
			closeConnection(conn);
			return false;
		}
		return true;
	}

	public void returnConnection(Connection conn) 
	{
		if (connections == null) {
			System.out.println("Can not find connection pool");
			return;
		}

		PooledConnection pConn = null;
		Enumeration enumerate = connections.elements();
		while (enumerate.hasMoreElements()) 
		{
			pConn = (PooledConnection) enumerate.nextElement();
			// 先找到连接池中的要返回的连接对象
			if (conn == pConn.getConnection()) 
			{
				// 找到了 , 设置此连接为空闲状态
				pConn.setBusy(false);
				break;
			}
		}
	}

	public synchronized void refreshConnections() throws SQLException 
	{
		if (connections == null) {
			System.out.println("Can not find connection pool, freshing operation failedssss");
			return;
		}
		PooledConnection pConn = null;
		Enumeration enumerate = connections.elements();
		while (enumerate.hasMoreElements()) 
		{
			pConn = (PooledConnection) enumerate.nextElement();
			if (pConn.isBusy()) 
			{
				wait(5000); // 等 5 秒
			}
			closeConnection(pConn.getConnection());
			pConn.setConnection(newConnection());
			pConn.setBusy(false);
		}
	}

	public synchronized void closeConnectionPool() throws SQLException 
	{
		if (connections == null) 
		{
			System.out.println("Can not find connection poll, close connection failed");
			return;
		}
		PooledConnection pConn = null;
		Enumeration enumerate = connections.elements();
		while (enumerate.hasMoreElements()) 
		{
			pConn = (PooledConnection) enumerate.nextElement();
			if (pConn.isBusy()) {
				wait(5000); // 等 5 秒
			}
			closeConnection(pConn.getConnection());
			connections.removeElement(pConn);
		}
		connections = null;
	}

	private void closeConnection(Connection conn) 
	{
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Erro when close the DB connection: " + e.getMessage());
		}
	}

	private void wait(int mSeconds) 
	{
		try 
		{
			Thread.sleep(mSeconds);
		} 
		catch (InterruptedException e) 
		{

		}
	}

	class PooledConnection 
	{
		Connection connection = null;// 数据库连接
		boolean busy = false; // 此连接是否正在使用的标志，默认没有正在使用
		
		// 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
		public PooledConnection(Connection connection) 
		{
			this.connection = connection;
		}

		// 返回此对象中的连接
		public Connection getConnection() 
		{
			return connection;
		}

		// 设置此对象的，连接
		public void setConnection(Connection connection) 
		{
			this.connection = connection;
		}

		// 获得对象连接是否忙
		public boolean isBusy() 
		{
			return busy;
		}

		// 设置对象的连接正在忙
		public void setBusy(boolean busy) 
		{
			this.busy = busy;
		}
	}

	public static void main(String[] args)
	{
		ConnectionPool connectionPool = new ConnectionPool();
	}

}