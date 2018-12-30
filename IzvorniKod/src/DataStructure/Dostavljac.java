package DataStructure;

import java.util.LinkedList;
import java.util.List;

public class Dostavljac extends Korisnik{

	private List<Zadatak> listaZadataka;
	
	
	public Dostavljac (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.napuniListuZadataka();
	}
	
	public PodaciKarte stvoriRutu () {
		
		this.napuniListuZadataka();
		PodaciKarte stvorenaRuta = new PodaciKarte(new LinkedList<GeoLokacija>(), true);
		
		for (Zadatak zadatak : listaZadataka) {
			stvorenaRuta.dodajUListuLokacija(zadatak.dohvatiLokaciju());
		}
		return stvorenaRuta;
	}
	
	public void oznaciGotovim (Zadatak zadatak) {
		
		zadatak.setGotov(true);
	}
	
	public List<Zadatak> getListaZadataka() {
		
		return listaZadataka;
	}
	
	private void napuniListuZadataka () {
		
		// metoda koja ce puniti listu zadataka iz baze podataka
	}
}
