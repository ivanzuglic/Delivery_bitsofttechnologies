package Database;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import DataStructure.GeoLokacija;
import DataStructure.Korisnik;
import DataStructure.Restoran;


public class AdministratorDAO {
	
	private String userDB;
	private String passwDB;
	private String host;
	
	public AdministratorDAO () {
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public int setRestoranOdobren(int idRestoran) {
		String sql = "UPDATE restoran SET restoranOdobren = ? WHERE idRestoran = ?";
		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setBoolean(1, true);
			prepSt.setInt(2, idRestoran); 		//u klasi 'Restoran' - ID se dobiva automatski ili vlasnik sam odreduje? -- Je li moguce generirati ID direktno u bazi podataka? -LM
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	
	public int setRazinaPristupa(int idKor, String novaRazinaPristupa) {
		int result = 2;
		String sql = "UPDATE korisnik SET uloga = ? WHERE idKor = ?";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, novaRazinaPristupa);
			prepSt.setInt(2, idKor); 		
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
				
		return result;
	}
	
	
	public List<Restoran> selectRestoraniPoOdobrenju(boolean odobrenje) {
		List<Restoran> restorani = new ArrayList<>();
		String sql = "SELECT restoran.*, korisnik.* FROM restoran NATURAL JOIN korisnik WHERE restoranOdobren = ?";
		
		try (Connection con = DriverManager.getConnection(host, userDB, passwDB);
			 PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			if(odobrenje == true) {
				prepSt.setBoolean(1, true);	
			} else {
				prepSt.setBoolean(1, false);
			}
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				int idRestoran = rs.getInt(1);
				String imeRestoran = rs.getString(2);
				String opis = rs.getString(3);
				String adresa = rs.getString(4);
				float lokacijaSirina = rs.getFloat(5);	//popravi u DB : tip Decimal(8,6)
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
				
				Restoran trenRestoran = new Restoran(idRestoran, imeRestoran, vlasnik, lokacija, opis, slika, odobren, telefon, fax, oib, iban, ziroRac, adresa);	// dodan i id restorana u restorane koji vec postoje i stvaraju se iz baze podataka -LM
				restorani.add(trenRestoran);
			}
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
		
		return restorani;
	}
	
	public List<Korisnik> selectKorisnici(){
		List<Korisnik> korisnici = new ArrayList<>();
		String sql = "SELECT * FROM korisnik";
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {	
			
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				String korisnickoIme = rs.getString(2);
				String lozinka = rs.getString(3);
				String ime = rs.getString(4);
				String prezime = rs.getString(5);
				String brMobitela = rs.getString(6);
				String email = rs.getString(7);
				String uloga = rs.getString(8);
				boolean online = rs.getBoolean(9);
				
				Korisnik trenKorisnik = new Korisnik(korisnickoIme, lozinka, ime, prezime, email); //uloga potrebna
				korisnici.add(trenKorisnik);
			}
						
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
						
		return korisnici;
	}

}