package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataStructure.GeoLokacija;
import DataStructure.Narudzba;

public class KlijentDAO {

	private String userDB;
	private String passwDB;
	private String host;
	
	
	public KlijentDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abcd";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false";
	}
	
	// metoda vraca -1 ako nema aktivnih narudzbi za trenutnog korisnika
	public int dohvatiIdAktivneNarudzbe (int  korisnickiId) {
		
		int idNarudzbe = -1;
		String sql = "SELECT idNar FROM narudzba WHERE idKlijent = ? AND aktivna = true";	// bilo bi pametnije da je aktivna boolean
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {	
			
			prepSt.setInt(1, korisnickiId);
			
			ResultSet rs = prepSt.executeQuery();
			
			if (rs.next()) {
				
				idNarudzbe = rs.getInt(1);
			}
						
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
						
		return idNarudzbe;
	}
	
	// metoda vraca -1 ako upit nije prosao, vraca 0 ako narudzbi jos nije dodjeljen dostavljac, ako je dodjeljen dostavljac, vraca njegov id
	public int dohvatiIdDostavljaca (Narudzba narudzba) {
		
		int idDostavljaca = -1;
		String sql = "SELECT idDostavljac FROM narudzba WHERE idNar = ?";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
				PreparedStatement prepSt = con.prepareStatement(sql)) {	
			
			prepSt.setInt(1, narudzba.getId());
			
			ResultSet rs = prepSt.executeQuery();
			rs.next();
			
			idDostavljaca = rs.getInt(1);
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return idDostavljaca;
	}
	
	// vraca true ako je dispecer preuzeo narudzbu, inace vraca false 
	public boolean narudzbaPreuzeta (Narudzba narudzba) {
		
		boolean preuzeta = false;
		String sql = "SELECT zadatakGotov FROM zadatak WHERE idNar = ? AND vrstaZad = 'POKUPI'";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
				PreparedStatement prepSt = con.prepareStatement(sql)) {	
		
			prepSt.setInt(1, narudzba.getId());
			
			ResultSet rs = prepSt.executeQuery();
			
			// ako upit nista ne vrati, to znaci da dispecer jos nije dodjelio zadatak - preuzeta po defaultu false
			if (rs.next()) {
				
				preuzeta = rs.getBoolean(1);
			}
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return preuzeta;
	}
	
	// vraca lokaciju dostavljaca ako je ona poznata, ako nije vraca null
	public GeoLokacija lokacijaDostavljaca (Narudzba narudzba) {
		
		GeoLokacija lokacijaDostavljaca = null;
		String sql = "SELECT geoSirinaTrenutno, geoDuzinaTrenutno FROM trenutnaLokacijaDostavljaca WHERE idDostavljac = ?";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
				PreparedStatement prepSt = con.prepareStatement(sql)) {	
		
			prepSt.setInt(1, narudzba.getDostavljac().getKorisnickiId());
			
			ResultSet rs = prepSt.executeQuery();
			
			if (rs.next()) {
				
				float geoSirina = rs.getFloat(1);
				float geoDuzina = rs.getFloat(2);
				
				// 0 znaci da je lokacija u bazi = null;
				if (geoSirina != 0.0 && geoDuzina != 0.0) {
					
					lokacijaDostavljaca = new GeoLokacija(geoSirina, geoDuzina, "Dostavljac");
				}
			}
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return lokacijaDostavljaca;
	}
}