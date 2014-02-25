package core;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

import tools.MongoDB;
import tools.Time;

public class Pwitt {

	static public void add(String sessionKey, String title, String content) throws CoreException
	{
		int id = AppModel.isAuthentified(sessionKey);
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void search(String sessionKey) throws CoreException
	{
		int user_id = AppModel.isAuthentified(sessionKey);
		
		try {
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			DBCursor cur = coll.find();
			while(cur.hasNext()){
				throw new CoreException(cur.next().toString(),12);
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
