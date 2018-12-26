package Database;

//uspostava veze prema bazi podataka

import java.sql.*;
import java.util.Properties;

// Database Access Object
public class DAO {
		private Connection connect = null;
	/*  za potrebe implementacije pojedinih SQL upita u drugim klasama
	  private Statement statement = null; 
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null; */
	 
	  /* za uspostavu veze prema DB
	  final private String host = "xxxxxxxxxxxxxxxxxxxxxxxx";*/
		private String user = "xxxxxxxx";
		private String passwd = "xxxxxxxxxxx";
	
	 public DAO(String currentUser, String currentUserPasswd) {
		 this.user = currentUser;
		 this.passwd = currentUserPasswd;
	 }
	  
	  /*Vraca objekt tipa Connection koji predstavlja upostavljenu vezu prema DB.
	  	Pomocu takvog objekta se mogu izvrsavati SQL upiti i raditi ostale 
	  	operacije nad DB */
	  public Connection openConnection() throws SQLException {
		  Properties properties = new Properties();
		  properties.put("user", user);
		  properties.put("password", passwd);
		  properties.put("characterEncoding", "ISO-8859-1");
		  properties.put("useUnicode", "true");
		  String url = "jdbc:mysql://hostname/database"; //server baze podataka
		  
		  connect = DriverManager.getConnection(url, properties);
		  
		  return connect;
		  
	  }
	  
	  private void close() throws SQLException {
		  connect.close();
	  }

}
