package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import DataStructure.Artikl;
import DataStructure.Restoran;

public class RestoranDAO {			//ispravljen try-catch i konstruktor
	
	private String userDB;
	private String passwDB;
	private String host;
	
	public RestoranDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public int pohraniRestoran (Restoran restoran) {
		
		String sql = "INSERT INTO restoran (idRestoran, imeRestoran, opis, adresa, geoDuzina, geoSirina, kontaktTelefon, fax, OIB, IBAN, ziroRacun, slika, idVlasnik, restoranOdobren)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, 0);	// ne prihvaca null, stavil sam 0 - LM
			prepSt.setString(2, restoran.getIme());
			prepSt.setString(3, restoran.getOpis());
			prepSt.setString(4, restoran.getAdresa());
			prepSt.setFloat(5, restoran.getLokacija().getGeoDuziina());			// nisam sto posto siguran je li format i naziv tocan - LM
			prepSt.setFloat(6, restoran.getLokacija().getGeoSirina());
			prepSt.setString(7, restoran.getTelefon());
			prepSt.setString(8, restoran.getFax());
			prepSt.setInt(8, restoran.getOIB());
			prepSt.setInt(9, restoran.getIBAN());
			prepSt.setInt(10, restoran.getZiroRacun());
			prepSt.setString(12, restoran.getSlika().toString());
			prepSt.setInt(13, restoran.getVlasnik().getKorisnickiId());
			prepSt.setBoolean(14, restoran.isOdobren());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		return result;
	}
	
	public int azurirajRestoran (Restoran restoran) {
		
		String sql = "UPDATE restoran SET imeRestoran = ?, opis = ?, adresa = ?, geoDuzina = ?, geoSirina = ?, kontaktTelefon = ?, fax = ?, OIB = ?, IBAN = ?, ziroRacun = ?, slika = ?, restoranOdobren = ?)";

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
			prepSt.setBoolean(13, restoran.isOdobren());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		return result;
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
				int vrijemePripravljanjaMin = rs.getInt(7);
				Restoran restoranArtikla = restoran;
				String opis = rs.getString(4);
				    
				Artikl noviArtikl = new Artikl(idArtikl, naziv, cijena, vrijemePripravljanjaMin, restoranArtikla, opis);
				    
				rezultatMeni.add(noviArtikl);
			}
				
		} 
		catch (SQLException sqlExc) {
				
			System.out.println(sqlExc.getMessage());
		}
		
		return rezultatMeni;
	}
	
	public int dodajUMeni (Artikl noviArtikl) {
		
		String sql = "INSERT INTO artikl (idArtikl, idRestoran, nazivArtikla, opis, cijena, vrijemePripreme)"	// maknuli bi sliku sa artikla - LM
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		int result = 2; // za testiranje
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, 0);	// ne prihvaca null, stavil sam 0 - LM
			prepSt.setInt(2, noviArtikl.getRestoran().getId());
			prepSt.setString(3, noviArtikl.getNaziv());
			prepSt.setString(4, noviArtikl.getOpis());
			prepSt.setFloat(5, noviArtikl.getCijena());
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
