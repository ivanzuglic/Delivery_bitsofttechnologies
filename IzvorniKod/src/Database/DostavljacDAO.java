package Database;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import DataStructure.GeoLokacija;
import DataStructure.Korisnik;
import DataStructure.Restoran;
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
		String sql = "SELECT narudzba.*, zadatak.vrstaZadatka FROM zadatak NATURAL JOIN narudzba WHERE idDostavljac = ? AND zadGotov = false";
		
		try (Connection con = DriverManager.getConnection(host, userDB, passwDB);
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			prepSt.setInt(1, idDostavljaca);
			
			ResultSet rs = prepSt.executeQuery();
			
			while(rs.next()) {
				
				// napisati
				
			}
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
		
		return zadaci;
	}
}
