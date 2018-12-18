package DataStructure;

import java.util.List;

public class Administrator extends Korisnik {

	private List<Restoran> listaOdobrenihRestorana;
	private List<Restoran> listaNeodobrenihRestorana;
	private List<Korisnik> listaKorisnika;
	
	
	public Administrator (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.napuniListeRestorana();
		this.napuniListuKorisnika();
	}
	
	public void odobriRestoran (Restoran restoran) {
		
		// metoda koja ce odobravati restoran
	}
	
	public void promijeniRazinuPristupa (Korisnik korisnik, VrstaKorisnika novaRazinaPristupa) {
		
		// metoda koja ce mijenjatirazinu pristupa korisnika
	}

	public List<Restoran> getListaOdobrenihRestorana() {
		
		return listaOdobrenihRestorana;
	}

	public List<Restoran> getListaNeodobrenihRestorana() {
		
		return listaNeodobrenihRestorana;
	}

	public List<Korisnik> getListaKorisnika() {
		
		return listaKorisnika;
	}
	
	private void napuniListeRestorana () {
		
		// metoda koja ce iz baze podataka puniti liste odobrenih i neodobrenih restorana
	}
	
	private void napuniListuKorisnika () {
		
		// metoda koja ce iz baze podataka puniti listu korisnika
	}
}
