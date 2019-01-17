package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DataStructure.Korisnik;

public class DispecerDAO {

	private String userDB;
	private String passwDB;
	private String host;
	
	
	public DispecerDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abcd";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public List<Korisnik> dohvatiAktivneDostavljace () {
		
		List<Korisnik> aktivniDostavljaci = new ArrayList<Korisnik>();
		String sql = "SELECT korisnik.* FROM korisnik WHERE uloga = 'DOSTAVLJAC' AND online = true";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {	
			
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				
				int idKorisnika = rs.getInt(1);
				String korisnickoIme = rs.getString(2);
				String lozinka = null;
				String ime = rs.getString(4);
				String prezime = rs.getString(5);
				String brMobitela = rs.getString(6);
				String email = rs.getString(7);
				String uloga = rs.getString(8);
				
				Korisnik trenDostavljac = new Korisnik(idKorisnika, korisnickoIme, lozinka, ime, prezime, brMobitela, email, uloga);
				aktivniDostavljaci.add(trenDostavljac);
			}
						
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
						
		return aktivniDostavljaci;
	}
	
	public List<Integer> dohvatiIdNerasporedjenihNarudzbi () {
		
		List<Integer> idNerasporedjenihNarudzbi = new ArrayList<Integer>();
		String sql = "SELECT UNIQUE idNar FROM narudzba WHERE idDostavljac = null";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {	
			
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				
				int idNar = rs.getInt(1);
				idNerasporedjenihNarudzbi.add(idNar);
			}
						
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
						
		return idNerasporedjenihNarudzbi;
	}
}