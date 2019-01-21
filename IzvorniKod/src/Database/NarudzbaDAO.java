package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import DataStructure.Artikl;
import DataStructure.GeoLokacija;
import DataStructure.Korisnik;
import DataStructure.Narudzba;
import DataStructure.Restoran;

public class NarudzbaDAO {
	
	private String userDB;
	private String passwDB;
	private String host;
	
	
	public NarudzbaDAO() {
		
		this.userDB = "myuser";
		this.passwDB = "abcd";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public int azurirajDostavljaca(Narudzba narudzba) {
		
		String sql = "UPDATE narudzba SET idDostavljac = ? WHERE idNar = ?";
		int result = 2;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, narudzba.getDostavljac().getKorisnickiId());
			prepSt.setInt(1, narudzba.getId());
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public int azurirajAktivnost(Narudzba narudzba) {
		String sql = "UPDATE narudzba SET aktivnostNar = ? WHERE idNar = ?";
		int result = 2;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setBoolean(1, narudzba.isAktivna());
			prepSt.setInt(2, narudzba.getId());

			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	
	public int pohraniNarudzbu(Narudzba narudzba) {
		
		String sql = "INSERT INTO narudzba (idArtikl, idKlijent, kolicina, geoSirinaPreuzimanja, geoDuzinaPreuzimanja, geoSirinaDostave, geoDuzinaDostave, aktivnostNar, vrijemeStvaranja, vrijemeZavrsetka, cijena) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO narudzba (idNar, idArtikl, idKlijent, kolicina, geoSirinaPreuzimanja, geoDuzinaPreuzimanja, geoSirinaDostave, geoDuzinaDostave, aktivnostNar, vrijemeStvaranja, vrijemeZavrsetka, cijena) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Map<Artikl, Integer> artikli =  narudzba.getOdabraniProizvodi();
		int idNar = 0;
		boolean prviArtikl = true;
		
		for(Map.Entry<Artikl, Integer> a : artikli.entrySet()) {
			if(prviArtikl == true) {
				try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
					PreparedStatement prepSt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
							
					prepSt.setInt(1, a.getKey().getIdArtikl());
					prepSt.setInt(2, narudzba.getKupac().getKorisnickiId());
					prepSt.setInt(3, a.getValue());
					prepSt.setFloat(4, narudzba.getLokacijaPreuzimanja().getGeoSirina());
					prepSt.setFloat(5, narudzba.getLokacijaPreuzimanja().getGeoDuziina());
					prepSt.setFloat(6, narudzba.getLokacijaDostavljanja().getGeoSirina());
					prepSt.setFloat(7, narudzba.getLokacijaDostavljanja().getGeoDuziina());
					prepSt.setBoolean(8, true);
					prepSt.setTimestamp(9, narudzba.getVrijemeStvaranja());
					prepSt.setTimestamp(10, narudzba.getVrijemeZavrsetka());
					prepSt.setFloat(11, narudzba.getCijena());
							
					prepSt.executeUpdate();
						
					ResultSet rs = prepSt.getGeneratedKeys();  // uzimanje zadnjeg idNar
						
					if(rs.next()) {
						idNar = rs.getInt(1);
						prviArtikl = false;
					}					
							
					} catch (SQLException sqlExc) {										
						System.out.println(sqlExc.getMessage());
					}
				
			} else {
				try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
					PreparedStatement prepSt = con.prepareStatement(sql2)) {
													
					prepSt.setInt(1, idNar);
					prepSt.setInt(2, a.getKey().getIdArtikl());
					prepSt.setInt(3, narudzba.getKupac().getKorisnickiId());
					prepSt.setInt(4, a.getValue());
					prepSt.setFloat(5, narudzba.getLokacijaPreuzimanja().getGeoSirina());
					prepSt.setFloat(6, narudzba.getLokacijaPreuzimanja().getGeoDuziina());
					prepSt.setFloat(7, narudzba.getLokacijaDostavljanja().getGeoSirina());
					prepSt.setFloat(8, narudzba.getLokacijaDostavljanja().getGeoDuziina());
					prepSt.setBoolean(9, true);
					prepSt.setTimestamp(10, narudzba.getVrijemeStvaranja());
					prepSt.setTimestamp(11, narudzba.getVrijemeZavrsetka());
					prepSt.setFloat(12, narudzba.getCijena());
							
					prepSt.executeUpdate();						
									
					} catch (SQLException sqlExc) {										
						System.out.println(sqlExc.getMessage());
					}
			}
			
		}				
		
		return idNar;
	}
	
	public Narudzba ucitajNarudzbu(int idNar) {
		
		String sql = "SELECT geoSirinaPreuzimanja, geoDuzinaPreuzimanja, geoSirinaDostave, geoDuzinaDostave, aktivnostNar, vrijemeStvaranja, vrijemeZavrsetka, cijena FROM narudzba WHERE idNar = ?";
		Narudzba nar = null;
		
		Map<Artikl, Integer> artikliNarudzbe = this.getArtikleNarudzbe(idNar);
		Korisnik kupac = this.getKupacDostavljac(idNar, "kupac");  
		Korisnik dostavljac = this.getKupacDostavljac(idNar, "dostavljac");
		Restoran restoran = this.getRestoran(artikliNarudzbe);
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
					
			prepSt.setInt(1, idNar);
				
			ResultSet rs = prepSt.executeQuery();
			
			if(rs.next()) {
				
				float geoSirinaPreuzimanja = rs.getFloat(1);
				float geoDuzinaPreuzimanja = rs.getFloat(2);
				float geoSirinaDostave = rs.getFloat(3);
				float geoDuzinaDostave = rs.getFloat(4);
				boolean aktivnostNar = rs.getBoolean(5);
				Timestamp vrijemeStvaranja = rs.getTimestamp(6);
				Timestamp vrijemeZavrsetka = rs.getTimestamp(7);
				float cijena = rs.getFloat(8);
				
				GeoLokacija lokacijaPreuzimanja = new GeoLokacija(geoSirinaPreuzimanja, geoDuzinaPreuzimanja, "Preuzmi");
				GeoLokacija lokacijaDostavljanja = new GeoLokacija(geoSirinaDostave, geoDuzinaDostave, "Dostavi");
				
				nar = new Narudzba(cijena, artikliNarudzbe, restoran, lokacijaPreuzimanja, lokacijaDostavljanja, kupac, dostavljac, vrijemeStvaranja, vrijemeZavrsetka, aktivnostNar);
			}
		
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return nar;
	}

	private Map<Artikl, Integer> getArtikleNarudzbe(int idNar){
		
		String sql = "SELECT narudzba.kolicina, artikl.* FROM narudzba JOIN artikl ON (artikl.idArtikl = narudzba.idArtikl) WHERE idNar = ?";
		String sql2 = "SELECT artikl.* FROM narudzba JOIN artikl ON (artikl.idArtikl = narudzba.idArtikl) WHERE idNar = ?";
		Map<Artikl, Integer> artikliNarudzbe = new HashMap<>();		
		Restoran restoran = null;
		int idRestoran = 0;
		
		// dohvat restorana koji ide u objekte tipa Artikl
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
				PreparedStatement prepSt = con.prepareStatement(sql2)) {
					
				prepSt.setInt(1, idNar);				
				ResultSet rs = prepSt.executeQuery();
				
				if(rs.next()) {					
					idRestoran = rs.getInt(2);  
										
				}												
					
			} catch (SQLException sqlExc) {
				
				System.out.println(sqlExc.getMessage());
			}
		
		if(idRestoran != 0) {
			restoran = new Restoran(idRestoran);
		}
						
		// dohvat ostatka artikla
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
				
			prepSt.setInt(1, idNar);
			
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				
				int kolicina = rs.getInt(1);
				int idArtikl = rs.getInt(2);
				int idRestoranArtikla = rs.getInt(3);        
				String nazivArtikla = rs.getString(4);
				String opis = rs.getString(5);
				float cijena = rs.getFloat(6);
				int vrijemePripremeMin = rs.getInt(7);
		
				Artikl artikl = new Artikl(idArtikl, nazivArtikla, cijena, vrijemePripremeMin, restoran, opis);
				artikliNarudzbe.put(artikl, kolicina);
			}												
				
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return artikliNarudzbe;
	}
	
	private Korisnik getKupacDostavljac(int idNar, String uloga) {
		
		String sql;
		if(uloga == "kupac")
			sql = "SELECT idKlijent FROM narudzba WHERE idNar = ?";
		else 
			sql = "SELECT idDostavljac FROM narudzba WHERE idNar = ?";
		
		int idKor = 0;
		Korisnik kupac = null;
		Korisnik dostavljac = null;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
					
			prepSt.setInt(1, idNar);
				
			ResultSet rs = prepSt.executeQuery();
			
			rs.next();
			idKor = rs.getInt(1);																				
					
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		if(idKor != 0 && uloga == "kupac") {
			
			kupac = new Korisnik(idKor);
			return kupac;
		} else if(idKor != 0 && uloga == "dostavljac") {
			
			dostavljac = new Korisnik(idKor);
			return dostavljac;
		} else {
			
			return null;
		}
	}
	
	private Restoran getRestoran(Map<Artikl, Integer> artikliNarudzbe) {
		
		Restoran restoran = null;
		
		if(!artikliNarudzbe.isEmpty()) {
			
			for(Map.Entry<Artikl, Integer> a : artikliNarudzbe.entrySet()) {
				
				restoran = a.getKey().getRestoran();
				break;
			}
		}
		
		return restoran;
	}
}