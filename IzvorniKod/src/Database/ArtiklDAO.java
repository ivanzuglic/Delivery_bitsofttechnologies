package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataStructure.Artikl;

public class ArtiklDAO {
	

	private DAO dao;
	private String currentUser;
	private String currentUserPassw;
	
	public ArtiklDAO (String currentUser, String currentUserPassw) {
		
		this.currentUser = currentUser;
		this.currentUserPassw = currentUserPassw;
	}
		
	public int pohraniArtikl(Artikl artikl) {
		String sql = "INSERT INTO artikl (idArtikl, idRestoran, nazivArtikla, opis, cijena, slika, vrijemePripreme)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		int result = 2; // za testiranje
		
		try {
			Connection con = dao.openConnection(currentUser, currentUserPassw);
			PreparedStatement prepSt = con.prepareStatement(sql);
			
			prepSt.setInt(1, artikl.getIdArtikl());
			prepSt.setInt(2, artikl.getRestoran().getId());
			prepSt.setString(3, artikl.getNaziv());
			prepSt.setString(4, artikl.getOpis());
			prepSt.setFloat(5, artikl.getCijena());
			// slika ?? kako spremiti u BP
			prepSt.setInt(7, artikl.getVrijemePripravljanja());
			
			result = prepSt.executeUpdate();
		}
		
		catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
		return result;
	}
}
