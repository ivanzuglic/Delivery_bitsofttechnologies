package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataStructure.Restoran;

public class RestoranDAO {
	
	private DAO dao;
	private String currentUser;
	private String currentUserPassw;
	
	public RestoranDAO (String currentUser, String currentUserPassw) {
		
		this.currentUser = currentUser;
		this.currentUserPassw = currentUserPassw;
	}
	
	public int pohraniRestoran (Restoran restoran) {
		
		String sql = "INSERT INTO restoran (idRestoran, imeRestoran, opis, adresa, lokacijaNaKarti, kontaktTelefon, fax, OIB, IBAN, ziroRacun, slika, idVlasnik, restoranOdobren)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 2; // za testiranje
		
		try {
			
			Connection con = dao.openConnection(currentUser, currentUserPassw);
			PreparedStatement prepSt = con.prepareStatement(sql);
			
			//idRestorana
			prepSt.setString(2, restoran.getIme());
			prepSt.setString(3, restoran.getOpis());
			prepSt.setString(4, restoran.getAdresa());
			// lokacija na karti koja je vjerojatno promjenjena
			prepSt.setString(6, restoran.getTelefon());
			prepSt.setString(7, restoran.getFax());
			// oib
			// iban
			// ziroRacun
			prepSt.setString(11, restoran.getSlika().toString());
			prepSt.setInt(12, restoran.getVlasnik().getKorisnickiId());
			prepSt.setBoolean(13, restoran.isOdobren());
			
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			
			System.out.println(sqlExc.getMessage());
		}
		return result;
	}

}
