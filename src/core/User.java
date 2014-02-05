package core;

public class User extends AppModel {
	
	static public void create(String firstName, String lastName, String username, String email, String password) throws CoreException{
		
	}

	public static void login(String username, String password) throws CoreException {
		if ((username == null) || (password == null)) {
			throw new CoreException(0);
		}
		try
		{
			boolean isUser = UserModel.userExists(username);
			if (!isUser) throw new CoreException(1);
		
			boolean passwordOk = UserModel.checkPassword()          ;
		      if (!password_ok) return(ServicesTools.error("Bad password "+login,2));
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
		catch (BDException e)
		{
		      return(ServicesTools.error("Problem while generating session key",1000)
		          );
		} catch (Exception e) {
		      return(ServicesTools.error("Problem...",10000));
		}

		
	}

	public static void logout(String key) throws CoreException {
		// TODO Auto-generated method stub
		
	}
	
	
}
