package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.MySQLDB;
import tools.Time;

public class CoreTools {
	
	public static int isAuthentified(String session) throws CoreException{
		
		try
		{
		    Connection con = MySQLDB.getConnection();
		    
		    String sql = "SELECT id, lastLogin "
		    		   + "FROM Cadene_Panou.Users "
		    		   + "WHERE session = ? LIMIT 1;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,session);
			ResultSet rset = ps.executeQuery();
			if(!rset.next())
				throw new CoreException(9);
			int id = rset.getInt(1);
			int lastLogin = rset.getInt(2);
			
			if(Time.getCurrentTimeUnix() - lastLogin > Config.time_without_login){
				throw new CoreException(9);
			}
			
			return id;
			
			
		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		} catch (InstantiationException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (IllegalAccessException e) {
			throw new CoreException(e.getMessage(),11);
		} catch (SQLException e) {
			throw new CoreException(e.getMessage(),10);
		}
	}
	
	

}
