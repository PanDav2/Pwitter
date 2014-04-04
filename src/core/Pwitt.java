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

	static public void add(String session, String title, String content) throws CoreException
	{
		int id = CoreTools.isAuthentified(session);
		
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
	
	static public String find(String session) throws CoreException
	{
		int user_id = CoreTools.isAuthentified(session);
		
		try {
			String s = "";
			DB db = MongoDB.getConnection();
			
			DBCollection coll = db. getCollection ("Pwitts");
			DBCursor cur = coll.find();
			while(cur.hasNext()){
				s += cur.next().toString();
			}
			
			/*Set<String> colls = db.getCollectionNames();

			for (String s : colls) {
			    System.out.println(s);
			}
			*/
			cur.close();
			return s;
			
		} catch (UnknownHostException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (MongoException e) {
			throw new CoreException(e.getMessage(),12);
		}
		
	}
	
}
