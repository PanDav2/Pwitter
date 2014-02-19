package servlet;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class TestMongo extends HttpServlet{
	
	//http://li328.lip6.fr:8280/CADENE_PANOU/users/login?username=Tamazy&password=azerty
	
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		try {
			String myUserName = "Cadene_Panou";
			String myPassword = "Cadene_Panou";
			Mongo m = new Mongo("132.227.201.129",27130);
			DB db = m.getDB("mydb");
			//Set<String> colls = db.getCollectionNames();
			DBCollection coll = db. getCollection ("testCollection2");
			BasicDBObject doc = new BasicDBObject();
			doc.put("name","PwittDatabase2");
			doc.put("type","database");
			doc.put("count", 1);
			
			BasicDBObject info = new BasicDBObject();
			info.put("Nom","Panou");
			info.put("Prénom","David");
			doc.put("info",info);/*
			info.put("Nom","Cadene");
			info.put("Prénom","Remi");
			doc.put("info",info);*/
		
			coll.insert(doc);
			
			DBObject dbDoc = coll.findOne();
			
			//for(String s : colls){
			resp.getWriter().println(dbDoc);
			//}
		} catch (UnknownHostException | MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/* catch (JSONException e) {
			resp.getWriter().println(ServletTools.JSONError());
		}*/
		
	
	}
	

}
