package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDB {

	private static boolean mysql_pooling = true;
	private static String mysql_host = "132.227.201.129:33306";//"localhost:33306";
	private static String mysql_username = "Cadene_Panou";
	private static String mysql_db = "Cadene_Panou";
	private static String mysql_password = "Cadene_Panou";
	
	private static Database database;
	
	public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		if (MySQLDB.mysql_pooling==false)
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return( DriverManager.getConnection("jdbc:mysql://"+MySQLDB.mysql_host
					+"/"
					+MySQLDB.mysql_db, MySQLDB.mysql_username,MySQLDB.mysql_password));
		}
		else
		{
			if (database==null)
			{
				database = new Database("jdbc/db");
			}
			return(database.getConnection());
		}
	}
}