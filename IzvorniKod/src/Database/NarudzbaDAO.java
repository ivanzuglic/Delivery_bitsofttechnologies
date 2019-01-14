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
		String sql = "INSERT INTO narudzba (idArtikl, idKlijent, kolicina, lokacijaPreuzimanja, lokacijaDostave, aktivnostNar, vrijemeStvaranja, vrijemeZavrsetka) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 2;
		
		for(Map.Entry<Artikl, Integer> a : narudzba.getOdabraniProizvodi().getEntrySet()) {
			
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


