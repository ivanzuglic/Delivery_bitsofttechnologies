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

public class PodatkovnaLjuskaDAO { 		//Ivan: maknuta 'starost' i ispravljeni try-catch blokovi i ispravljen konstruktor
			
	private String userDB;
	private String passwDB;
	private String host;
	
	public PodatkovnaLjuskaDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public String vrstaKorisnika (String korisnickoIme) {
		
		String sql = "SELECT vrsta FROM korisnik WHERE vrsta = ?";
		
		String result = null;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, korisnickoIme);
			ResultSet rs = prepSt.executeQuery();
			
			result = rs.getString(1);
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public boolean korisnickoImePostoji (String korisnickoIme) {
		
		String sql = "SELECT * FROM korisnik WHERE korisnickoIme = ?";
		
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
		
		Set<Restoran> restorani = new HashSet<Restoran>();
		
		String sql = "SELECT restoran.*, korisnik.* FROM restoran NATURAL JOIN korisnik WHERE restoranOdobren = true";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				int idRestoran = rs.getInt(1);
				String imeRestoran = rs.getString(2);
				String opis = rs.getString(3);
				String adresa = rs.getString(4);
				float lokacijaSirina = rs.getFloat(5);	//
				float lokacijaDuzina = rs.getFloat(6);	//popravi u DB : tip Decimal(9,6) 
				String telefon = rs.getString(7);
				String fax = rs.getString(8);
				int oib = rs.getInt(9);					// prepravi u string u DB
				int iban = rs.getInt(10);				//
				int ziroRac = rs.getInt(11);			//
				String slikaPath = rs.getString(12);
				boolean odobren = rs.getBoolean(13);	//popravi u DB (stavi na predzadnje mjesto)
				
				String korisnickoIme = rs.getString(15);
				String lozinka = rs.getString(16);
				String ime = rs.getString(17);
				String prezime = rs.getString(18);
				String brMobitela = rs.getString(19);
				String email = rs.getString(20);
				String uloga = rs.getString(21);
				boolean online = rs.getBoolean(22);
				
				Korisnik vlasnik = new Korisnik(korisnickoIme, lozinka, ime, prezime, email);	// uloga umjesto starost?
				GeoLokacija lokacija = new GeoLokacija(lokacijaSirina, lokacijaDuzina, "Restoran");
				BufferedImage slika = null;
				try {
		        	slika = ImageIO.read(new File(slikaPath)); 			 //dodatno testirat
		        } catch (IOException e){
		            e.printStackTrace();
		        }
				
				Restoran trenRestoran = new Restoran(idRestoran, imeRestoran, vlasnik, lokacija, opis, slika, odobren, telefon, fax, adresa);	// dodan i id restorana u restorane koji vec postoje i stvaraju se iz baze podataka -LM
				restorani.add(trenRestoran);
			}
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
		
		return restorani;
	}
}
