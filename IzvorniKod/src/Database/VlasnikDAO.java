package Database;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import DataStructure.GeoLokacija;
import DataStructure.Korisnik;
import DataStructure.Restoran;

public class VlasnikDAO {

	private String userDB;
	private String passwDB;
	private String host;
	
	public VlasnikDAO () {
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public Restoran DohvatiVlastitiRestoran (Korisnik vlasnik) {
		
		String sql = "SELECT restoran.* FROM restoran WHERE idVlasnik = ?";
		
		Restoran restoran = null;
		
		try(Connection con = DriverManager.getConnection(host, userDB, passwDB); 
			PreparedStatement prepSt = con.prepareStatement(sql)) {
			
			prepSt.setInt(1, vlasnik.getKorisnickiId());
			ResultSet rs = prepSt.executeQuery();
			
			if (rs.next()) {
				
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
				
				GeoLokacija lokacija = new GeoLokacija(lokacijaSirina, lokacijaDuzina, "Restoran");
				
				BufferedImage slika = null;
				try {
		        	slika = ImageIO.read(new File(slikaPath)); 			 //dodatno testirat
		        } catch (IOException e){
		            e.printStackTrace();
		        }
				
				restoran = new Restoran(idRestoran, imeRestoran, vlasnik, lokacija, opis, slika, odobren, telefon, fax, oib, iban, ziroRac, adresa);
			}
			
		} 
		catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		
		return restoran;
	}
}
