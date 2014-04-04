package core;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoException;

import tools.MongoDB;
import tools.MySQLDB;
import tools.Sha1;
import tools.Time;

public class Server {
	
	static public String mapReduce(String password) throws CoreException
	{

		if(password.compareTo("admin") != 0)
			throw new CoreException(2001);
		
		try {
			
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			
			String m = "function() { " +
							"var words = this.content.match(/\\w+/g); " +
							"df = []; " +
							"for(w in words) " +
							"{ df[w] = 1; } " +
							"for(w in df) " +
							"{ emit(w,{df: df[w]}) }; " +
						"}";
			
			String r = "function(key,values) { " +
							"total = 0; " +
							"for(v in values) " +
							"{ total += v; } " +
							"return ({word:key,df:total})" +
						"}";
		
			MapReduceCommand cmd = new MapReduceCommand(coll,m,r,null,
					MapReduceCommand.OutputType.INLINE,null);
			MapReduceOutput out = coll.mapReduce(cmd);
			String s = "";
			for(DBObject obj : out.results()){
				s += obj.toString();
			}
			return s;
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
	}

	public static String login(String email, String password) throws CoreException
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
			
		    return session;

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
	
}
