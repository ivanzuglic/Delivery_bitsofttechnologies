package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PodatkovnaLjuskaDAO {
	
	private DAO dao;
	private String currentUser;
	private String currentUserPassw;
	
	public PodatkovnaLjuskaDAO (String currentUser, String currentUserPassw) {
		
		this.currentUser = currentUser;
		this.currentUserPassw = currentUserPassw;
	}
	
	public String vrstaKorisnika (String korisnickoIme) {
		
		String sql = "SELECT vrsta FROM korisnik WHERE vrsta = ?";
		
		String result = null;
		
		try {
			
			Connection con = dao.openConnection(currentUser, currentUserPassw);
			
			PreparedStatement prepSt = con.prepareStatement(sql);
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
		
		try {
			
			Connection con = dao.openConnection(currentUser, currentUserPassw);
			
			PreparedStatement prepSt = con.prepareStatement(sql);
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
		
		try {
			
			Connection con = dao.openConnection(currentUser, currentUserPassw);
			
			PreparedStatement prepSt = con.prepareStatement(sql);
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
		
		try {
			
			Connection con = dao.openConnection(currentUser, currentUserPassw);
			
			PreparedStatement prepSt = con.prepareStatement(sql);
			prepSt.setBoolean(1, status);
			prepSt.setString(2, korisnickoIme);
			
			prepSt.executeUpdate();
			
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
	}

}
