package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataStructure.Artikl;

public class ArtiklDAO {
	
	private String userDB;
	private String passwDB;
	private String host;
	
	
	public ArtiklDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public int pohraniArtikl (Artikl artikl) {
		
		String sql = "INSERT INTO artikl (idArtikl, idRestoran, nazivArtikla, opis, cijena, slika, vrijemePripreme)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, artikl.getIdArtikl());
			prepSt.setInt(2, artikl.getRestoran().getId());
			prepSt.setString(3, artikl.getNaziv());
			prepSt.setString(4, artikl.getOpis());
			prepSt.setFloat(5, artikl.getCijena());
			// slika ?? kako spremiti u BP ---> treba u Artikl napraviti implementaciju BufferedImage i uzeti path kao string
			prepSt.setInt(7, artikl.getVrijemePripravljanja());
			
			result = prepSt.executeUpdate();
		}
		
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
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