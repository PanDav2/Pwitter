package core;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

import tools.MongoDB;
import tools.MySQLDB;
import tools.Time;

public class Pwitt {

	static public void add(String session,String content) throws CoreException
	{
		try {
			/* recover user info */ 
			
			Connection con = MySQLDB.getConnection();
		    
		    String sql = "SELECT id, firstname, lastname, email, lastLogin "
		    		   + "FROM Cadene_Panou.Users "
		    		   + "WHERE session = ? LIMIT 1;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,session);
			ResultSet rset = ps.executeQuery();
			if(!rset.next())
				throw new CoreException(9);
			int id = rset.getInt(1);
			String firstname = rset.getString(2);
			String lastname = rset.getString(3);
			String email = rset.getString(4);
			int lastLogin = rset.getInt(5);
			
			if(Time.getCurrentTimeUnix() - lastLogin > Config.time_without_login){
				throw new CoreException(9);
			}
		
			/* add pwitt */
			
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			BasicDBObject user = new BasicDBObject();
			user.put("id", id);
			user.put("firstname", firstname);
			user.put("lastname", lastname);
			user.put("email", email);
			BasicDBObject geodata = new BasicDBObject();
			geodata.put("latitude", 0);
			geodata.put("longitude", 0);
			geodata.put("magnitude", 0.001);
			BasicDBObject doc = new BasicDBObject();
			doc.put("user",user);
			doc.put("content",content);
			doc.put("date",Time.getCurrentTimeUnix());
			coll.insert(doc);
			
			
		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		} catch (InstantiationException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (IllegalAccessException e) {
			throw new CoreException(e.getMessage(),11);
		} catch (SQLException e) {
			throw new CoreException(e.getMessage(),10);
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
		
	}
	
	static public ArrayList<String> find(String session,String words,String dofriends) throws CoreException
	{
		// TODO parse words if exist by " " to ArrayList<String>
		
		if(session != ""){
			int user_id = User.isAuthentified(session);
			if(words != ""){
				if(dofriends == "" || dofriends == "0"){
					//TODO return _find(user_id,words)
				}else{
					//TODO return _find(user_id,words,dofriends)
				}
			}else{
				if(dofriends == "" || dofriends == "0"){
					return _find(user_id);
				}else{
					//TODO return _find(user_id,dofriends)
				}
			}
		}else{
			if(words != ""){
				//TODO return _find(words);
			}else{
				return _find();
			}
		}
		
		return null;
		
	}
	
	/**
	 * Find all twitts
	 * @return
	 */
	static private ArrayList<String> _find() throws CoreException
	{
		try {
			ArrayList<String> s = new ArrayList<String>();
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			DBCursor cur = coll.find();
			while(cur.hasNext()){
				s.add( cur.next().toString() );
			}
			cur.close();
			return s;
			
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
	}
	
	/**
	 * Find all twitts from user_id
	 * @param user_id
	 * @return
	 */
	//TODO à vérifier
	static private ArrayList<String> _find(int user_id) throws CoreException
	{
		try {
			ArrayList<String> s = new ArrayList<String>();
			DB db = MongoDB.getConnection();
			
			DBCollection pwitts = db. getCollection ("Pwitts");
			
			BasicDBObject whereQuery = new BasicDBObject();
			BasicDBObject user = new BasicDBObject();
			user.put("id", user_id);
			whereQuery.put("user", user);
			
			DBCursor cur = pwitts.find(whereQuery);
			while(cur.hasNext()){
				s.add( cur.next().toString() );
			}
			cur.close();
			return s;
			
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
	}
	
	/**
	 * Find all twitts from the friends of user_id
	 * @return
	 */
	static private ArrayList<String> _find(int user_id, String dofriends) throws CoreException
	{
		//TODO recover friends of user_id from MySQL::Users
		//TODO whereQuery MongoDB
		
		try {
			ArrayList<String> s = new ArrayList<String>();
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			DBCursor cur = coll.find();
			while(cur.hasNext()){
				s.add( cur.next().toString() );
			}
			cur.close();
			return s;
			
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
	}
	
	/**
	 * Find all twitts with best score linked to words
	 * @return
	 */
	static private ArrayList<String> _find(int user_id, ArrayList<String> words) throws CoreException
	{
		//TODO 
		
		try {
			ArrayList<String> s = new ArrayList<String>();
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			DBCursor cur = coll.find();
			while(cur.hasNext()){
				s.add( cur.next().toString() );
			}
			cur.close();
			return s;
			
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
	}
	
	
	
}
