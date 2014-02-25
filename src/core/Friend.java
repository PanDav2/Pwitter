package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


import tools.MySQLDB;
import tools.Time;

public class Friend {
	
	static public int add(String sessionKey, int friend_id) throws CoreException
	{

		int user_id = CoreTools.isAuthentified(sessionKey);
		
		try {

		    Connection con = MySQLDB.getConnection();
		    String sql = "INSERT INTO Cadene_Panou.Friends "
		    		   + "(user_id, friend_id, created) "
		    		   + "VALUES (?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,user_id);
			ps.setInt(2,friend_id);
			ps.setInt(3,Time.getCurrentTimeUnix());
			ps.executeUpdate();
			ResultSet rset = ps.getGeneratedKeys();
			rset.next();
			int id = rset.getInt(1);
			
			ps.close();
			con.close();
			
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
	
	
	static public int remove(String sessionKey, int friend_id) throws CoreException
	{

		int user_id = CoreTools.isAuthentified(sessionKey);
		
		try {

		    Connection con = MySQLDB.getConnection();
		    String sql = "DELETE FROM Cadene_Panou.Friends "
		    		   + "WHERE user_id = ? AND friend_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,user_id);
			ps.setInt(2,friend_id);
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return user_id;

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
