package DataStructure;

import java.util.Set;

import Database.VlasnikDAO;

public class Vlasnik extends Klijent {

	private Restoran vlastitiRestoran;
	private Set<Narudzba> narudzbeAi2h;
	
	
	public Vlasnik (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.DohvatiVlastitiRestoran();
	}
	
	public void napuniAi2h () {
		
		// metoda koja ce puniti set Ai2h iz baze podataka
	}
	
	public Restoran getVlastitiRestoran () {
		
		return vlastitiRestoran;
	}

	public Set<Narudzba> getNarudzbeAi2h () {
		
		return narudzbeAi2h;
	}
	
	private void DohvatiVlastitiRestoran () {
		
		VlasnikDAO dao = new VlasnikDAO();
		vlastitiRestoran = dao.DohvatiVlastitiRestoran(this);
	}
}
