package Database;

//uspostava veze prema bazi podataka

import java.sql.*;
import java.util.Properties;

// Database Access Object
public class DAO {
	  private Connection connect = null;  
	  
	  /*Vraca objekt tipa Connection koji predstavlja upostavljenu vezu prema DB.
	  	Pomocu takvog objekta se mogu izvrsavati SQL upiti i raditi ostale 
	  	operacije nad DB */
	  public Connection openConnection(String user, String passwd) throws SQLException {
		  Properties properties = new Properties();
		  properties.put("user", user);
		  properties.put("password", passwd);
		  properties.put("characterEncoding", "ISO-8859-1");
		  properties.put("useUnicode", "true");
		  String url = "jdbc:mysql://hostname/database"; //server baze podataka(host)
		  
		  connect = DriverManager.getConnection(url, properties);
		  
		  return connect;
		  
	  }
	  
	  private void closeConnection() throws SQLException {
		  connect.close();
	  }
	  
	  private Connection getConnection() {
		  return this.connect;
	  }
	  
}
