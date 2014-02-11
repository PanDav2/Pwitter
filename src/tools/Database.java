package tools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Database {

	private DataSource dataSource;

	public Database(String jndiname) throws SQLException {
	
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
		}
		catch (NamingException e) {
			// Handle error that it’s not configured in JNDI.
			throw new SQLException(jndiname + " is missing in JNDI! : "+e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}


}