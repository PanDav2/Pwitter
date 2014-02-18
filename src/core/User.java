package core;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import tools.DBMySQL;
import tools.Sha1;
import tools.Time;

public class User {
	
	static public int create(
			String firstName, String lastName,
			String username, String email, String password) throws CoreException
	{
		if ((firstName == null) || (lastName == null)
			||(username == null) || (email == null) || (password == null)) {
			throw new CoreException(0);
		}

		try {

		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = DBMySQL.getMySQLConnection();
		    String sql = "INSERT INTO Cadene_Panou.Users "
		    		   + "(firstName,lastName,username,email,password,created) "
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
		}
	}

	public static String login(String username, String password) throws CoreException
	{
		if ((username == null) || (password == null)) {
			throw new CoreException(0);
		}
		
		try {

 			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = DBMySQL.getMySQLConnection();
		    
		    String sql = "SELECT id, username, password "
		    		   + "FROM Cadene_Panou.Users "
		    		   + "WHERE username = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rset = ps.executeQuery();
			rset.next();
			if(!rset.getString(3).equals(password)){
				throw new CoreException(2);
			}
			int id = rset.getInt(1);
			
			String sessionKey = Sha1.generate(
				id + username + password + Time.getCurrentTime()
			);
			
			sql = "UPDATE Cadene_Panou.Users "
					+ "SET sessionKey=?, lastLogin=? "
		    		+ "WHERE id=?;";
			ps = con.prepareStatement(sql);
			ps.setString(1,sessionKey);
			ps.setInt(2,Time.getCurrentTime());
			ps.setInt(3,id);
			ps.executeUpdate();
			
		    return sessionKey;

		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		} catch (InstantiationException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (IllegalAccessException e) {
			throw new CoreException(e.getMessage(),11);
		} catch (SQLException e) {
			throw new CoreException(e.getMessage(),10);
		} catch (NoSuchAlgorithmException e) {
			throw new CoreException(e.getMessage(),13);
		} catch (UnsupportedEncodingException e) {
			throw new CoreException(e.getMessage(),14);
		}

	
	}

	public static void logout(String sessionKey) throws CoreException {
		
		if (sessionKey == null) {
			throw new CoreException(0);
		}
		
		try {

 			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = DBMySQL.getMySQLConnection();
		    
		    String sql = "UPDATE Cadene_Panou.Users "
					+ "SET lastLogin=0 "
		    		+ "WHERE sessionKey=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,sessionKey);
			ps.executeUpdate();

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
