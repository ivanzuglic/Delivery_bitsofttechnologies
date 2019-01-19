package Database;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import DataStructure.Artikl;
import DataStructure.GeoLokacija;
import DataStructure.Korisnik;
import DataStructure.Restoran;

public class RestoranDAO {			
	
	private String userDB;
	private String passwDB;
	private String host;
	
	
	public RestoranDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abcd";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public int pohraniRestoran (Restoran restoran) {
		
		// privremeno uklonjena slika
		
		String sql = "INSERT INTO restoran (imeRestoran, opis, adresa, lokacijaSirina, lokacijaDuzina, kontaktTelefon, fax, OIB, IBAN, ziroRacun, restoranOdobren, idVlasnik)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int idRestoran = 0;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			prepSt.setString(1, restoran.getIme());
			prepSt.setString(2, restoran.getOpis());
			prepSt.setString(3, restoran.getAdresa());
			prepSt.setFloat(4, restoran.getLokacija().getGeoDuziina());			// nisam sto posto siguran je li format i naziv tocan - LM
			prepSt.setFloat(5, restoran.getLokacija().getGeoSirina());
			prepSt.setString(6, restoran.getTelefon());
			prepSt.setString(7, restoran.getFax());
			prepSt.setInt(8, restoran.getOIB());
			prepSt.setInt(9, restoran.getIBAN());
			prepSt.setInt(10, restoran.getZiroRacun());
			//prepSt.setString(11, restoran.getSlika().toString());
			prepSt.setBoolean(11, restoran.isOdobren());
			prepSt.setInt(12, restoran.getVlasnik().getKorisnickiId());
			
			
			prepSt.executeUpdate();
			
			ResultSet rs = prepSt.getGeneratedKeys();
			
			if(rs.next()) {
				idRestoran = rs.getInt(1);
			}
				
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return idRestoran;
	}
	
	public int azurirajRestoran (Restoran restoran) {
		
		String sql = "UPDATE restoran SET imeRestoran = ?, opis = ?, adresa = ?, geoDuzina = ?, geoSirina = ?, kontaktTelefon = ?, fax = ?, OIB = ?, IBAN = ?, ziroRacun = ?, slika = ?, restoranOdobren = ? WHERE idRestoran = ?";

		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setString(1, restoran.getIme());
			prepSt.setString(2, restoran.getOpis());
			prepSt.setString(3, restoran.getAdresa());
			prepSt.setFloat(4, restoran.getLokacija().getGeoDuziina());
			prepSt.setFloat(5, restoran.getLokacija().getGeoSirina());
			prepSt.setString(6, restoran.getTelefon());
			prepSt.setString(7, restoran.getFax());
			prepSt.setInt(8, restoran.getOIB());
			prepSt.setInt(9, restoran.getIBAN());
			prepSt.setInt(10, restoran.getZiroRacun());
			prepSt.setString(11, restoran.getSlika().toString());
			prepSt.setBoolean(12, restoran.isOdobren());
			prepSt.setInt(13, restoran.getId());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public Restoran ucitajRestoran(int idRestoran) {
		
		String sql = "SELECT restoran.* FROM restoran WHERE idRestoran = ?";
		
		Restoran restoran = null;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, idRestoran);
			
			ResultSet rs = prepSt.executeQuery();
			
			if(rs.next()) {
				
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
//				try {
//		        	slika = ImageIO.read(new File(slikaPath)); 			 //dodatno testirat
//		        } catch (IOException e){
//		            e.printStackTrace();
//		        }
				
				Korisnik vlasnik = new Korisnik (idVlasnik);
				restoran = new Restoran(idRestoran, imeRestoran, vlasnik, lokacija, opis, slika, odobren, telefon, fax, oib, iban, ziroRac, adresa);
			
			}	
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return restoran;
	}
	

	public Set<Artikl> dohvatiMeni (Restoran restoran) {
		
		String sql = "SELECT artikl.* FROM artikl WHERE idRestoran = ?";
		
		Set<Artikl> rezultatMeni = new TreeSet<Artikl>();
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
				
			prepSt.setInt(1, restoran.getId());	// dohvacamo sve artikle it tog restorana
				
			ResultSet rs = prepSt.executeQuery();
				
			while (rs.next()) {
					
				int idArtikl = rs.getInt(1);
				String naziv = rs.getString(3);
				float cijena = rs.getFloat(5);
				int vrijemePripravljanjaMin = rs.getInt(6);
				Restoran restoranArtikla = restoran;
				String opis = rs.getString(4);
				    
				Artikl noviArtikl = new Artikl(idArtikl, naziv, cijena, vrijemePripravljanjaMin, restoranArtikla, opis);
				    
				rezultatMeni.add(noviArtikl);
			}	
		} catch (SQLException sqlExc) {
				
			System.out.println(sqlExc.getMessage());
		}
		
		return rezultatMeni;
	}
	
	public int dodajUMeni (Artikl noviArtikl) {
		
		String sql = "INSERT INTO artikl (idRestoran, nazivArtikla, opis, cijena, vrijemePripremeMin)"	// maknuli bi sliku sa artikla - LM
				+ "VALUES (?, ?, ?, ?, ?)";
		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
				
			prepSt.setInt(1, noviArtikl.getRestoran().getId());
			prepSt.setString(2, noviArtikl.getNaziv());
			prepSt.setString(3, noviArtikl.getOpis());
			prepSt.setFloat(4, noviArtikl.getCijena());
			prepSt.setInt(5, noviArtikl.getVrijemePripravljanja());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public int skiniSMenija (Artikl artikl) {
		
		String sql = "DELETE FROM artikl WHERE idArtikl = ?";
		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, artikl.getIdArtikl());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		return result;
	}
}