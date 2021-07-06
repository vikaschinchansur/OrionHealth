package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
	public Connection getConnection() throws Exception
	{
		try
		{
			Connection connection=null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(  "jdbc:mysql://aakbc97u7fj9gp.ctliairk4adk.ap-southeast-2.rds.amazonaws.com:3306/ebdb","DBUser","DBPassword");
			return connection;
		}
		catch (SQLException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public void closeConnection(Connection con){
		try
		{
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
