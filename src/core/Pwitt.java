package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tools.DBMySQL;
import tools.Time;

public class Pwitt {

	static public void add(
			String sessionKey,
			String title, String content) throws CoreException
	{

		/*if(title == null)
			title = "No Title";
		
		try {

		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = DBMySQL.getMySQLConnection();
		    String sql = "INSERT INTO Cadene_Panou.Pwitts "
		    		   + "(title) "
		    		   + "VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,firstName);
			ps.setString(2,lastName);
			ps.setString(3,username);
			ps.setString(4,email);
			ps.setString(5,password);
			ps.setInt(6,Time.getCurrentTime());
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
		}*/
	}
	
}
