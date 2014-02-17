package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMySQL {

	private static boolean mysql_pooling = false;
	private static String mysql_host = "localhost:33306";
	private static String mysql_username = "Cadene_Panou";
	private static String mysql_db = "Cadene_Panou";
	private static String mysql_password = "Cadene_Panou";
	
	private static Database database;
	
	public static Connection getMySQLConnection() throws SQLException
	{
		if (DBMySQL.mysql_pooling==false)
		{
			return( DriverManager.getConnection("jdbc:mysql://"+DBMySQL.mysql_host
					+"/"
					+DBMySQL.mysql_db, DBMySQL.mysql_username,DBMySQL.mysql_password));
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