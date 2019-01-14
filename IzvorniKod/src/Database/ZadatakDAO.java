package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DataStructure.Zadatak;

public class ZadatakDAO {
	private String userDB;
	private String passwDB;
	private String host;
	
	public ZadatakDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public int postaviZadGotov(int narudzbaId, String vrstaZadatka) {
		
		String sql = "UPDATE zadatak SET gotov = true WHERE idNar = ? AND vrstaZad = ?";
		int result = 2;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {	
			
			prepSt.setInt(1, narudzbaId);
			prepSt.setString(2, vrstaZadatka);
			
			result = prepSt.executeUpdate();
						
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		return result;
	}
	
	public int pohraniZadatak (Zadatak zadatak) {
		
		String sql = "INSERT INTO zadatak (vrstaZad, zadGotov) VALUES (?, ?)";
		int result = 2;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, zadatak.getVrsta().toString());
			prepSt.setBoolean(2, zadatak.getGotov());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		return result;
	}
}
