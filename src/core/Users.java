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

public class Users {
	

	public static ArrayList<ArrayList<String>> find(String session, String words) throws CoreException {
		
		int user_id = User.isAuthentified(session);
		
		try {

				String[] tab = words.split(" ");
			
	 			Class.forName("com.mysql.jdbc.Driver").newInstance();
			    Connection con = MySQLDB.getConnection();
			    
			    String sql = "SELECT u.id, u.email, u.firstName, u.lastName "
			    		   + "FROM Cadene_Panou.Users u "
			    		   + "WHERE 1=1";
			    
			    for(int i=0; i<tab.length; i++){
			    	sql += " AND u.lastName LIKE \"%"+tab[i]+"%\"";
			    }
			    
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rset = ps.executeQuery();
				
				ArrayList<ArrayList<String>> friends = new ArrayList<ArrayList<String>>();
				ArrayList<String> friend;
				while(rset.next()){
					friend = new ArrayList<String>();
					friend.add( rset.getString(1) );
					friend.add( rset.getString(2) );
					friend.add( rset.getString(3) );
					friend.add( rset.getString(4) );
					friends.add(friend);
				}
			    return friends;
			    
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

