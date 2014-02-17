package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tools.DBMySQL;

public class User extends AppModel {
	
	static public int create(
			String firstName, String lastName,
			String username, String email, String password) throws CoreException
	{
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DBMySQL.getMySQLConnection();
			Statement statement = connection.createStatement();
			int statut = statement.executeUpdate("INSERT INTO Cadene_Panou.User (username) VALUES ('test');");
			return statut;
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

	/*public static void login(String username, String password) throws CoreException {
		if ((username == null) || (password == null)) {
			throw new CoreException(0);
		}
		try
		{
			boolean isUser = UserModel.userExists(username);
			if (!isUser) throw new CoreException(1);
		
			boolean passwordOk = UserModel.checkPassword()          ;
		      if (!passwordOk) return(ServicesTools.error("Bad password "+login,2));
		      //Récupère l’id de l’utilisateur
		      int id_user=AuthentificationTools.getIdUser(login);
		      JSONObject retour=new JSONObject();
		      //Insère une nouvelle session dans la base de données
		      String key=AuthentificationTools.insertSession(id_user,false);
		      retour.put("key",key);
		      return(retour);
		}
		catch(JSONException e)
		{
		      return(ServicesTools.error("JSON Problem "+e.getMessage(),100));
		}
		catch (CoreException e)
		{
		      return(ServicesTools.error("Problem while generating session key",1000)
		          );
		} catch (Exception e) {
		      return(ServicesTools.error("Problem...",10000));
		}

		
	}*/

	public static void logout(String key) throws CoreException {
		// TODO Auto-generated method stub
		
	}
	
	
}
