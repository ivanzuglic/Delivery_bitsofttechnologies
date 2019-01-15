package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KlijentDAO {

	private String userDB;
	private String passwDB;
	private String host;
	
	public KlijentDAO () {
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	// metoda vraca -1 ako nema aktivnih narudzbi za trenutnog korisnika
	public int dohvatiIdAktivneNarudzbe (int  korisnickiId) {
		
		int idNarudzbe = -1;
		String sql = "SELECT idNar FROM narudzba WHERE idKlijent = ? AND aktivna = true";	// bilo bi pametnije da je aktivna boolean
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {	
			prepSt.setInt(1, korisnickiId);
			
			ResultSet rs = prepSt.executeQuery();
			
			if (rs.next()) {
				
				idNarudzbe = rs.getInt(1);
			}
						
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
						
		return idNarudzbe;
	}
}
