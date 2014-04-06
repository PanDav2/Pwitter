package core;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

import tools.MongoDB;
import tools.Time;

public class Pwitt {

	static public void add(String session, String title, String content) throws CoreException
	{
		int id = User.isAuthentified(session);
		
		try {
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			BasicDBObject doc = new BasicDBObject();
			doc.put("user_id",id);
			if(title != null)
				doc.put("title",title);
			doc.put("content",content);
			doc.put("created",Time.getCurrentTimeUnix());
			coll.insert(doc);
			
			
			
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
	}
	
	static public ArrayList<String> find(String session,String words,String dofriends) throws CoreException
	{
		int user_id;
		
		if(session == "" && words == "" && dofriends == "")
			return _find();
		
		if(session != "")
			user_id = User.isAuthentified(session);
		
		return null;
		
	}
	/**
	 * Trouver tous les pwitts
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
	
}
