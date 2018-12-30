package DataStructure;

import java.util.List;

public class Dostavljac extends Korisnik{

	private List<Zadatak> listaZadataka;
	
	
	public Dostavljac (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.napuniListuZadataka();
	}
	
	public PodaciKarte stvoriRutu () {
		
		PodaciKarte ruta = null;
		this.napuniListuZadataka();
		
		// metoda koja ce iz liste zadataka stvoriti podatke karte potrebene za iscrtavanje rute
		
		return ruta;
	}
	
	public void oznaciGotovim (Zadatak zadatak) {
		
		// metoda koja ce zadatak oznaciti gotovim
	}
	
	public List<Zadatak> getListaZadataka() {
		
		return listaZadataka;
	}
	
	private void napuniListuZadataka () {
		
		// metoda koja ce puniti listu zadataka iz baze podataka
	}
}
