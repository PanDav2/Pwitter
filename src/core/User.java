package core;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import tools.MySQLDB;
import tools.Sha1;
import tools.Time;

public class User {
	
	static public int register(
			String firstname, String lastname,
			String email, String password) throws CoreException
	{

		try {

		    Connection con = MySQLDB.getConnection();
		    String sql = "INSERT INTO Cadene_Panou.Users "
		    		   + "(firstname,lastname,email,password,created) "
		    		   + "VALUES (?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,firstname);
			ps.setString(2,lastname);
			ps.setString(3,email);
			ps.setString(4,password);
			ps.setInt(5,Time.getCurrentTimeUnix());
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

	public static ArrayList<String> login(String email, String password) throws CoreException
	{
		
		try {

 			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = MySQLDB.getConnection();
		    
		    String sql = "SELECT id, email, password "
		    		   + "FROM Cadene_Panou.Users "
		    		   + "WHERE email = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,email);
			ResultSet rset = ps.executeQuery();
			if(!rset.next()){
				throw new CoreException(2000);
			}
			// password verified
			if(!rset.getString(3).equals(password)){
				throw new CoreException(2001);
			}
			int id = rset.getInt(1);
			
			String session = Sha1.generate(
				id + email + password + Time.getCurrentTimeUnix()
			);
			
			sql = "UPDATE Cadene_Panou.Users "
					+ "SET session=?, lastLogin=? "
		    		+ "WHERE id=?;";
			ps = con.prepareStatement(sql);
			ps.setString(1,session);
			ps.setInt(2,Time.getCurrentTimeUnix());
			ps.setInt(3,id);
			ps.executeUpdate();
			
			ArrayList<String> rslt = new ArrayList<String>();
		    rslt.add(String.valueOf(id));
		    rslt.add(session);
		    return rslt;
		    
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

	public static void logout(String session) throws CoreException
	{
		
		try {
		    Connection con = MySQLDB.getConnection();
		    
		    String sql = "UPDATE Cadene_Panou.Users "
					+ "SET lastLogin=0 "
		    		+ "WHERE session=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,session);
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

	public static ArrayList<String> get(String session) throws CoreException {
		try {

	 			Class.forName("com.mysql.jdbc.Driver").newInstance();
			    Connection con = MySQLDB.getConnection();
			    
			    String sql = "SELECT id, email, firstName, lastName "
			    		   + "FROM Cadene_Panou.Users "
			    		   + "WHERE session = ?;";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,session);
				ResultSet rset = ps.executeQuery();
				if(!rset.next()){
					throw new CoreException(2000);
				}
				int id = rset.getInt(1);
				String email = rset.getString(2);
				String firstName = rset.getString(3);
				String lastName = rset.getString(4);
				
				ArrayList<String> rslt = new ArrayList<String>();
			    rslt.add(String.valueOf(id));
			    rslt.add(email);
			    rslt.add(firstName);
			    rslt.add(lastName);
			    
			    return rslt;
			    
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

