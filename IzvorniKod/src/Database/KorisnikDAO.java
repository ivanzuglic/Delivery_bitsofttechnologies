package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataStructure.Korisnik;

public class KorisnikDAO {

	private String userDB;
	private String passwDB;
	private String host;
	
	
	public KorisnikDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public String dohvatiKorisnickoIme(int idKor) {
		
		String sql = "SELECT korisnickoIme FROM korisnik WHERE IdKor = ?";
		String result = null;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
				
			prepSt.setInt(1, idKor);
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
	
	public String dohvatiUlogu (String korisnickoIme) {
		
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
	
	public String dohvatiIme (String korisnickoIme) {
		
		String sql = "SELECT ime FROM korisnik WHERE korisnickoIme = ?";
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
	
	public String dohvatiPrezime (String korisnickoIme) {
		
		String sql = "SELECT prezime FROM korisnik WHERE korisnickoIme = ?";
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
	
	public String dohvatiEMail (String korisnickoIme) {
		
		String sql = "SELECT email FROM korisnik WHERE korisnickoIme = ?";
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
	
	public String dohvatiMobitel (String korisnickoIme) {
		
		String sql = "SELECT brMobitela FROM korisnik WHERE korisnickoIme = ?";
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
	
	// u slucaju neuspjele konekcije, vraca 0
	public int dohvatiId (String korisnickoIme) {
		
		String sql = "SELECT idKor FROM korisnik WHERE korisnickoIme = ?";
		int result = 0;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, korisnickoIme);
			ResultSet rs = prepSt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public int pohraniKorisnika (Korisnik korisnik) {
		
		String sql = "INSERT INTO korisnik (korisnickoIme, lozinka, ime, prezime, brMobitela, email, uloga, online) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 2;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, korisnik.getKorisnickoIme());
			prepSt.setString(2, korisnik.getLozinka());
			prepSt.setString(3, korisnik.getIme());
			prepSt.setString(4, korisnik.getPrezime());
			prepSt.setString(5, korisnik.getBrMobitela());
			prepSt.setString(6, korisnik.getEMail());
			prepSt.setString(7, korisnik.getUloga());
			prepSt.setInt(8, 0);
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public int azurirajKorisnika (Korisnik korisnik) {
		
		String sql = "UPDATE korisnik SET korisnickoIme = ?, lozinka = ?, ime = ?, prezime = ?, brMobitela = ?, email = ?, uloga = ? WHERE idKor = ?";
		int result = 2;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, korisnik.getKorisnickoIme());
			prepSt.setString(2, korisnik.getLozinka());
			prepSt.setString(3, korisnik.getIme());
			prepSt.setString(4, korisnik.getPrezime());
			prepSt.setString(5, korisnik.getBrMobitela());
			prepSt.setString(6, korisnik.getEMail());
			prepSt.setString(7, korisnik.getUloga());
			prepSt.setInt(8, korisnik.getKorisnickiId());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
}