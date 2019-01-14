package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import DataStructure.Artikl;
import DataStructure.Narudzba;

public class NarudzbaDAO {
	private String userDB;
	private String passwDB;
	private String host;
	
	public NarudzbaDAO() {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public int pohraniNarudzbu(Narudzba narudzba) {
		String sql = "INSERT INTO narudzba (idArtikl, idKlijent, kolicina, geoSirinaPreuzimanja, geoDuzinaPreuzimanja, geoSirinaDostave, geoDuzinaDostave, aktivnostNar, vrijemeStvaranja, vrijemeZavrsetka) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 2;
		
		for(Map.Entry<Artikl, Integer> a : narudzba.getOdabraniProizvodi().entrySet()) {
			try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
					PreparedStatement prepSt = con.prepareStatement(sql)) {
					
					prepSt.setInt(1, a.getKey().getIdArtikl());
					prepSt.setInt(2, narudzba.getKupac().getKorisnickiId());
					prepSt.setInt(3, a.getValue());
					prepSt.setFloat(4, narudzba.getLokacijaPreuzimanja().getGeoSirina());
					prepSt.setFloat(5, narudzba.getLokacijaPreuzimanja().getGeoDuziina());
					prepSt.setFloat(6, narudzba.getLokacijaDostavljanja().getGeoSirina());
					prepSt.setFloat(7, narudzba.getLokacijaDostavljanja().getGeoDuziina());
					prepSt.setBoolean(8, false);
					prepSt.setTimestamp(9, narudzba.getVrijemeStvaranja());
					prepSt.setTimestamp(10, narudzba.getVrijemeZavrsetka());
					
					result = prepSt.executeUpdate();
					
				} catch (SQLException sqlExc) {
					
					System.out.println(sqlExc.getMessage());
				}	
			
		}
		

		return result;
	}
	
	public int azurirajDostavljaca(Narudzba narudzba) {
		
	}
	
	public int azurirajAktivnost(Narudzba narudzba) {
		
	}
	
	public void ucitajNarudzbu() {
		
	}
}


