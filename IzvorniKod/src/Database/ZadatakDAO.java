package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataStructure.Artikl;
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
		
		String sql = "INSERT INTO zadatak (idNar, vrstaZad, zadGotov, geoSirinaIdiNaLok, geoDuzinaIdiNaLok) VALUES (?, ?, ?, ?, ?)";
		int result = 2;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, zadatak.getNarudzba().getId());
			prepSt.setString(2, zadatak.getVrsta().toString());
			prepSt.setBoolean(3, zadatak.getGotov());
			prepSt.setFloat(4, zadatak.dohvatiLokaciju().getGeoSirina());
			prepSt.setFloat(5, zadatak.dohvatiLokaciju().getGeoDuziina());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		return result;
	}
	
	// vraca -1 ako je nuspjelo, = ako id ne postoji i id ako postoji
	public int dohvatiIdZadatka (Zadatak zadatak) {
		
		String sql = "SELECT idZad FROM zadatak WHERE idNar = ? AND vrstaZad = ?";
		int id = -1;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
				PreparedStatement prepSt = con.prepareStatement(sql)) {
				
				prepSt.setInt(1, zadatak.getNarudzba().getId());
				prepSt.setString(2, zadatak.getVrsta().toString());
				
				ResultSet rs = prepSt.executeQuery();
				
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
			
		catch (SQLException sqlExc) {
				
			System.out.println(sqlExc.getMessage());
		}
			
		return id;
	}
}