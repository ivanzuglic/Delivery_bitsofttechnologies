package Database;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import DataStructure.GeoLokacija;
import DataStructure.Korisnik;
import DataStructure.Restoran;

public class PodatkovnaLjuskaDAO {
			
	private String userDB;
	private String passwDB;
	private String host;
	
	
	public PodatkovnaLjuskaDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abcd";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public String vrstaKorisnika (String korisnickoIme) {
		
		String sql = "SELECT uloga FROM korisnik WHERE korisnickoIme = ?";
		
		String result = null;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, korisnickoIme);
			ResultSet rs = prepSt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public boolean korisnickoImePostoji (String korisnickoIme) {
		
		String sql = "SELECT korisnik.* FROM korisnik WHERE korisnickoIme = ?";
		
		boolean result = false;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, korisnickoIme);
			ResultSet rs = prepSt.executeQuery();
			
			if (rs.next()) {
				result = true;	// ako je upit nesto vratio, korisnicko ime postoji
			}
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public boolean lozinkaIspravna (String korisnickoIme, String lozinka) {
		
		String sql = "SELECT lozinka FROM korisnik WHERE korisnickoIme = ?";
		
		String ucitanaLozinka;
		boolean result = false;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, korisnickoIme);
			ResultSet rs = prepSt.executeQuery();
			
			rs.next();
			ucitanaLozinka = rs.getString(1);
			
			if (ucitanaLozinka.equals(lozinka)) {
				result = true;
			}
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public void postaviOnlineStatus (String korisnickoIme, boolean status) {
		
		String sql = "UPDATE korisnik SET online = ? WHERE korisnickoIme = ?";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setBoolean(1, status);
			prepSt.setString(2, korisnickoIme);
			
			prepSt.executeUpdate();
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
	}
	
	public Set<Restoran> ucitajRestorane(){
		
		String sql = "SELECT restoran.* FROM restoran WHERE restoranOdobren = true";
		Set<Restoran> restorani = new HashSet<Restoran>();
		
		try (Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				
				int idRestoran = rs.getInt(1);
				String imeRestoran = rs.getString(2);
				String opis = rs.getString(3);
				String adresa = rs.getString(4);
				float lokacijaSirina = rs.getFloat(5);	
				float lokacijaDuzina = rs.getFloat(6);	 
				String telefon = rs.getString(7);
				String fax = rs.getString(8);
				int oib = rs.getInt(9);					
				int iban = rs.getInt(10);				
				int ziroRac = rs.getInt(11);			
				String slikaPath = rs.getString(12);
				boolean odobren = rs.getBoolean(13);	
				int idVlasnik = rs.getInt(14);
				
				GeoLokacija lokacija = new GeoLokacija(lokacijaSirina, lokacijaDuzina, "Restoran");
				BufferedImage slika = null;
				/*try {													//dodatno testirat
		        	slika = ImageIO.read(new File(slikaPath));
		        } catch (IOException e){
		            e.printStackTrace();
		        }*/
				
				Korisnik vlasnik = new Korisnik (idVlasnik);
				Restoran trenRestoran = new Restoran(idRestoran, imeRestoran, vlasnik, lokacija, opis, slika, odobren, telefon, fax, oib, iban, ziroRac, adresa);	// dodan i id restorana u restorane koji vec postoje i stvaraju se iz baze podataka -LM
				restorani.add(trenRestoran);
			}
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return restorani;
	}
}