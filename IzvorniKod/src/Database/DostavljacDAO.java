package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataStructure.GeoLokacija;
import DataStructure.Narudzba;
import DataStructure.VrsteZadataka;
import DataStructure.Zadatak;

public class DostavljacDAO {

	private String userDB;
	private String passwDB;
	private String host;
	
	public DostavljacDAO () {
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public List<Zadatak> dohvatiListuZadataka (int idDostavljaca) {
		
		List<Zadatak> zadaci = new ArrayList<>();
		String sql = "SELECT narudzba.idNar, narudzba.istaNarudzba, zadatak.vrstaZad FROM zadatak NATURAL JOIN narudzba WHERE idDostavljac = ? AND zadGotov = false";
		
		int[] idNarudzbi = new int[300];			// moze i neka druga struktura
		String[] vrsteZadataka = new String[300];
		
		try (Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, idDostavljaca);			
			ResultSet rs = prepSt.executeQuery();
			
			int i = 0;
			while(rs.next()) {				
				idNarudzbi[i] = rs.getInt(1);
				vrsteZadataka[i] = rs.getString(3);
			}
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
		
		for (int i = 0; i < idNarudzbi.length; i++) {
			Narudzba nar = new Narudzba(idNarudzbi[i]);
			VrsteZadataka vrsta = VrsteZadataka.valueOf(vrsteZadataka[i]);
			GeoLokacija lokacija = null;
			
			if(vrsta.equals(VrsteZadataka.OSTAVI)) {
				lokacija = nar.getLokacijaDostavljanja();
			} else if(vrsta.equals(VrsteZadataka.POKUPI)){
				lokacija = nar.getLokacijaPreuzimanja();
			} else {
				lokacija = nar.getLokacijaDostavljanja();    // ako je IDINALOKACIJU onda je u oba atributa upisana ista lokacija (da se bezveze ne dodaje jos jedan atribut za lokaciju)
			} 
			
			Zadatak zadatak = new Zadatak(nar, vrsta, lokacija);
			zadaci.add(zadatak);			
		}
		
		return zadaci;
	}
}
