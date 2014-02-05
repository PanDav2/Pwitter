package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBStatic {

	private static boolean mysql_pooling;
	private static String mysql_host;
	private static String mysql_username;
	private static String mysql_db;
	private static String mysql_password;
	
	private static Database database;

	public static Connection getMySQLConnection() throws SQLException
	{
	      if (DBStatic.mysql_pooling==false)
	      {
	            return( DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host
	                +"/"
	            +DBStatic.mysql_db, DBStatic.mysql_username,DBStatic.mysql_password));
	      }
	      else
	      {
	            if (database==null)
	            {
	                  database=new Database("jdbc/db");
	            }
	            return(database.getConnection());
	      }
	}
}
