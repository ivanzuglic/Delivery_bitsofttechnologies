package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataStructure.Artikl;
import DataStructure.Restoran;

public class ArtiklDAO {
	
	private String userDB;
	private String passwDB;
	private String host;
	
	
	public ArtiklDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public Artikl ucitajArtikl (int idArtikl) {
		
		String sql = "SELECT artikl.* FROM artikl WHERE idArtikl = ?";
		Artikl artikl = null;
		int idRestoran = 0;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, idArtikl);
			ResultSet rs = prepSt.executeQuery();
			
			if(rs.next()) {
				
				idRestoran = rs.getInt(2);
				String nazivArtikla = rs.getString(3);
				String opis = rs.getString(4);
				float cijena = rs.getFloat(5);
				int vrijemePripremeMin = rs.getInt(6);
				
				artikl = new Artikl(idArtikl, nazivArtikla, cijena, vrijemePripremeMin, null, opis);
			}			
		}
		
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		if(idRestoran != 0) {
			Restoran restoran = new Restoran(idRestoran);
			artikl.setRestoran(restoran);
		}
				
		return artikl;
	}
	
	public int azurirajArtikl (Artikl artikl) {
		
		String sql = "UPDATE artikl SET nazivArtikla = ?, opis = ?, cijena = ?, vrijemePripreme = ? WHERE idArtikl = ?";
		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, artikl.getNaziv());
			prepSt.setString(2, artikl.getOpis());
			prepSt.setFloat(3, artikl.getCijena());
			prepSt.setInt(4, artikl.getVrijemePripravljanja());
			prepSt.setInt(5, artikl.getIdArtikl());
			
			result = prepSt.executeUpdate();
		}
		
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	// vraca -1 ako je nuspjelo, = ako id ne postoji i id ako postoji
	public int dohvatiIdArtikla (Artikl artikl) {
		
		String sql = "SELECT idArtikl FROM artikl WHERE idRestoran = ? AND nazivArtikla = ?";
		int id = -1;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
				PreparedStatement prepSt = con.prepareStatement(sql)) {
				
				prepSt.setInt(1, artikl.getRestoran().getId());
				prepSt.setString(2, artikl.getNaziv());
				
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