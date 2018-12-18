package DataStructure;

import java.util.Set;

public class Vlasnik extends Klijent {

	private Restoran vlastitiRestoran;
	private Set<Narudzba> narudzbeAi2h;
	
	
	public Vlasnik (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		
		// dohvati vlastitiRestoran iz Baze Podataka
	}
	
	public void napuniAi2h () {
		
		// metoda koja ce puniti set Ai2h iz baze podataka
	}
	
	public void urediMeni () {
		
		// metoda za uredjivanje menija
	}

	public Restoran getVlastitiRestoran() {
		
		return vlastitiRestoran;
	}

	public Set<Narudzba> getNarudzbeAi2h() {
		
		return narudzbeAi2h;
	}
}
