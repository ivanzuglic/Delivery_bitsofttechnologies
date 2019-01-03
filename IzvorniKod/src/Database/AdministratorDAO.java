package Database;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import DataStructure.Korisnik;
import DataStructure.Restoran;
import DataStructure.VrstaKorisnika;

public class AdministratorDAO {
		
	private DAO adminDAO;
	private String currentUser;
	private String currentUserPassw;
	
	public AdministratorDAO (String currentUser, String currentUserPassw) {
		this.currentUser = currentUser;
		this.currentUserPassw = currentUserPassw;
	}
	
	public int setRestoranOdobren(int idRestoran) {
		String sql = "UPDATE restoran SET restoranOdobren = ? WHERE idRestoran = ?";
		int result = 2; // za testiranje
		try {
			Connection con = adminDAO.openConnection(currentUser, currentUserPassw);
			PreparedStatement prepSt = con.prepareStatement(sql);
			prepSt.setBoolean(1, true);
			prepSt.setInt(2, idRestoran); 		//potrebna metoda u klasi 'Restoran' - ID se dobiva automatski 
												//										ili vlasnik sam odreduje?
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
		
		return result;
	}
	
	public int setRazinaPristupa(int idKor, String novaRazinaPristupa) {
		int result = 2;
		String sql = "UPDATE korisnik SET uloga = ? WHERE idKor = ?";
		try {
			Connection con = adminDAO.openConnection(currentUser, currentUserPassw);
			PreparedStatement prepSt = con.prepareStatement(sql);
			prepSt.setString(1, novaRazinaPristupa);
			prepSt.setInt(2, idKor); 		//potrebna metoda u klasi 'Korisnik' - ID se dobiva automatski 
											//										ili korisnik sam odreduje?
			result = prepSt.executeUpdate();
			
		} catch (SQLException sqlExc) {
			System.out.println(sqlExc.getMessage());
		}
				
		return result;
	}
	
	private List<Restoran> selectOdobreneRestorane(){
		List<Restoran> odobreniRestorani = null;
		
		return odobreniRestorani;
	}

}
