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
							"var words = this.content.match(/[\\wéèêàôöîïüûù']+/g); " +
							"tab = []; " +
							"for(w in words) { " +
								"tab[w] = 1; " +
							"}" +
							"for(w in tab) { " +
								"emit(words[w],tab[w]); " +
							"}" +
						"}";
			
			/*m = "function() { " +
					"var word = this.content; " +
					"emit(word,1); " +
				"}";*/
			
			String r = "function(key,values) { " +
							"total = 0; " +
							"for(var i in values) { " +
								"total += values[i]; " +
							"} " +
							"return total;" +
						"}";
		
			MapReduceCommand cmd = new MapReduceCommand(coll,m,r,null,
					MapReduceCommand.OutputType.INLINE,null);
			MapReduceOutput out = coll.mapReduce(cmd);
			String s = "";
			for(DBObject obj : out.results()){
				s += obj.toString() + "  ";
			}
			return s;
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
	}

	

	
}
