package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import tools.DBMySQL;

public class User extends AppModel {
	
	static public int create(
			String firstName, String lastName,
			String username, String email, String password) throws CoreException
	{
		if ((firstName == null) || (lastName == null)
			||(username == null) || (email == null) || (password == null)) {
			throw new CoreException(0);
		}

		try {

		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = DBMySQL.getMySQLConnection();
		    String sql = "INSERT INTO Cadene_Panou.Users"
		    		   + "(firstName,lastName,username,email,password,created)"
		    		   + "VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,firstName);
			ps.setString(2,lastName);
			ps.setString(3,username);
			ps.setString(4,email);
			ps.setString(5,password);
			ps.setTimestamp(6, getCurrentTimeStamp());
			ps.executeUpdate();
			ResultSet rset = ps.getGeneratedKeys();
			rset.next()
			return rset.getInt(1);

		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		} catch (InstantiationException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (IllegalAccessException e) {
			throw new CoreException(e.getMessage(),11);
		} catch (SQLException e) {
			throw new CoreException(e.getMessage(),10);
		} finally {
 			if (ps != null) {
				ps.close();
			}
	 		if (con != null) {
				con.close();
			}
		}
	}

	public static int login(String username, String password) throws CoreException
	{
		if ((username == null) || (password == null)) {
			throw new CoreException(0);
		}
		
		try {

 			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = DBMySQL.getMySQLConnection();
		    String sql = "SELECT id, username, password"
		    		   + "FROM Cadene_Panou.Users"
		    		   + "WHERE username = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rset = ps.execute();
			rset.next();
			if(rset.getString(2) != username){
				throw new CoreException(1);
			}
			if(rset.getString(3) != password){
				throw new CoreException(2);
			}
			int id = rset.getInt(1);
			
			//Insère une nouvelle session dans la base de données
		    //String key=AuthentificationTools.insertSession(id_user,false);
		    return id;

		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		} catch (InstantiationException e) {
			throw new CoreException(e.getMessage(),12);
		} catch (IllegalAccessException e) {
			throw new CoreException(e.getMessage(),11);
		} catch (SQLException e) {
			throw new CoreException(e.getMessage(),10);
		} finally {
 			if (ps != null) {
				ps.close();
			}
	 		if (con != null) {
				con.close();
			}
		}

		
	}

	public static void logout(String key) throws CoreException {
		// TODO Auto-generated method stub
		
	}
	
	
}
