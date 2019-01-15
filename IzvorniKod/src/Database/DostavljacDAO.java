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
		String sql2 = "SELECT zadatak.* FROM zadatak NATURAL JOIN narudzba WHERE idDostavljac = ? AND vrstaZad = ?";
		
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
				
			} else {											// zadatak je IDINALOKACIJU -> moramo dobiti lokaciju iz 'Zadatak' za danog dostavljaca
				
				try (Connection con = DriverManager.getConnection(host, userDB, passwDB);
					PreparedStatement prepSt = con.prepareStatement(sql2)) {
					
					prepSt.setInt(1, idDostavljaca);		
					prepSt.setString(2, "IDINALOKACIJU");	
					ResultSet rs = prepSt.executeQuery();
					
					if(rs.next()) {
						float geoSirinaIdiNaLokaciju = rs.getFloat(5);
						float geoDuzinaIdiNaLokaciju= rs.getFloat(6);
						lokacija = new GeoLokacija(geoSirinaIdiNaLokaciju, geoDuzinaIdiNaLokaciju, "Idi na lokaciju");
					}
					
					
				} catch (SQLException sqlExc) {
					System.out.println(sqlExc.getMessage());
				} 
			} 
			
			Zadatak zadatak = new Zadatak(nar, vrsta, lokacija);
			zadaci.add(zadatak);			
		}
		
		return zadaci;
	}
}
